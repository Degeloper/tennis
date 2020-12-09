package ch.css.coaching.web;

import ch.css.coaching.Ball;
import ch.css.coaching.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSocketGame {

  private static final Logger LOGGER = Logger.getLogger(WebSocketGame.class.getName());
  private final Field field;
  private final List<Session> playerSessions = new ArrayList<>();


  public WebSocketGame() {
    field = new Field(480, 320);
  }

  public void addPlayerSession(Session playerSession) {
    Racket racket = field.newRacket();
    playerSession.addMessageHandler(new RacketHandler(racket));
    playerSessions.add(playerSession);
    startGame();

  }

  void startGame() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      public void run() {
        field.moveBall();
        playerSessions.forEach(s -> {
          try {
            s.getBasicRemote().sendText(toJson(field));
          } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
          }
        });
      }
    }, 0, 10);
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
