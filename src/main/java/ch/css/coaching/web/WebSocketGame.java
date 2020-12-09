package ch.css.coaching.web;

import ch.css.coaching.playground.Field;
import ch.css.coaching.playground.Racket;
import ch.css.coaching.scoring.GameScore;
import ch.css.coaching.scoring.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

public class WebSocketGame {

  private static final Logger LOGGER = Logger.getLogger(WebSocketGame.class.getName());
  private final Field field;
  private final Map<Session, Player> playerSessions = new HashMap<>();
  private GameScore gameScore;
  private Timer timer;

  public WebSocketGame() {
    field = new Field(480, 320);
  }

  public void addPlayerSession(Session playerSession) {
    Player newPlayer = new Player();
    playerSessions.put(playerSession, newPlayer);
    Racket racket = field.newRacket(newPlayer);
    playerSession.addMessageHandler(new RacketHandler(racket));
    if (playerSessions.size() == 2)
      startGame();
  }

  private void startGame() {
    gameScore = new GameScore(playerSessions.values().toArray(new Player[0]));
    timer = new Timer();
    timer.schedule(runnableGame(), 0, 10);
  }

  private TimerTask runnableGame() {
    return new TimerTask() {
      public void run() {
        if (gameScore.winner().isPresent())
          closeSessionsAndTellWinner(gameScore.winner().get());
        Optional<Racket> missedRacket = field.missedRacket();
        if (missedRacket.isPresent())
          givePointToOpponentOf(missedRacket.get().getPlayer());
        else
          field.moveBall();
        playerSessions.forEach((s, p) -> {
          try {
            s.getBasicRemote().sendText(toJson(field));
          } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
          }
        });
      }
    };
  }

  private void givePointToOpponentOf(Player player) {
    Player opponent = gameScore.opponentOf(player);
    gameScore.scoreBall(opponent);
    field.initBall();
  }

  private void closeSessionsAndTellWinner(Player winningPlayer) {
    playerSessions.forEach((session, player) -> {
        try {
          if (player == winningPlayer)
            session.close(new CloseReason(NORMAL_CLOSURE, "YOU WON!"));
          else
            session.close(new CloseReason(NORMAL_CLOSURE, "YOU LOST!"));
        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, e.getMessage());
        }
      }
    );
    playerSessions.clear();
    field.resetRackets();
    timer.cancel();
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
