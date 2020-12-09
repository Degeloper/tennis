package ch.css.coaching.web;

import ch.css.coaching.game.Game;
import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.field.Racket;
import ch.css.coaching.game.score.Player;

import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class GameEndpoint extends Endpoint {

  private static final Game game = new Game(new Field(480, 320));
  private final ScheduledExecutorService gameScheduler = Executors.newScheduledThreadPool(1);

  @Override
  public void onOpen(Session session, EndpointConfig config) {
    Player newPlayer = new Player();
    game.subscribeToGameEvents(new GameEventsHandler(session, newPlayer));
    addRacketHandlerToSession(session, newPlayer);

    if (game.isReady()) {
      gameScheduler.scheduleAtFixedRate(game, 0, 12, MILLISECONDS);
    }
  }

  @Override
  public void onClose(Session session, CloseReason closeReason) {
    gameScheduler.shutdown();
  }

  @Override
  public void onError(Session session, Throwable thr) {
    gameScheduler.shutdown();
  }

  private void addRacketHandlerToSession(Session session, Player newPlayer) {
    Racket racket = game.newRacketFor(newPlayer);
    session.addMessageHandler(new RacketHandler(racket));
  }

}
