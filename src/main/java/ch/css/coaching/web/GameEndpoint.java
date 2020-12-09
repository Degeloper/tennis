package ch.css.coaching.web;

import ch.css.coaching.playground.Field;
import ch.css.coaching.playground.Game;
import ch.css.coaching.playground.Racket;
import ch.css.coaching.scoring.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

public class GameEndpoint extends Endpoint {

  private static final Logger LOGGER = Logger.getLogger(GameEndpoint.class.getName());

  private static final Game game = new Game();
  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

  @Override
  public void onOpen(Session session, EndpointConfig config) {
    Player newPlayer = new Player();
    Racket racket = game.newRacketFor(newPlayer);
    session.addMessageHandler(new RacketHandler(racket));
    game.subscribeFieldUpdate(field -> {
      try {
        session.getBasicRemote().sendText(toJson(field));
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, e.getMessage());
      }
    });
    game.subscribeWinnerUpdate(winner -> {
        try {
          if (newPlayer == winner)
            session.close(new CloseReason(NORMAL_CLOSURE, "YOU WON!"));
          else
            session.close(new CloseReason(NORMAL_CLOSURE, "YOU LOST!"));
        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, e.getMessage());
        }
      }
    );

    if (game.isReady()) {
      scheduler.scheduleAtFixedRate(game, 0, 10, MILLISECONDS);
    }
  }

  @Override
  public void onClose(Session session, CloseReason closeReason) {
    scheduler.shutdown();
  }

  @Override
  public void onError(Session session, Throwable thr) {
    scheduler.shutdown();
  }

  private String toJson(Field field) {
    try {
      return new ObjectMapper().writeValueAsString(field);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "{}";
    }

  }

}
