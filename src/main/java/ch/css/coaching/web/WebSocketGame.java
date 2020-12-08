package ch.css.coaching.web;

import ch.css.coaching.Ball;
import ch.css.coaching.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSocketGame {

  private static final Logger LOGGER = Logger.getLogger(WebSocketGame.class.getName());
  private final Field field;
  private Session playerSession;

  public WebSocketGame() {
    field = new Field(480, 320);
  }

  public void addPlayerSession(Session playerSession) {
    playerSession.addMessageHandler(new RacketHandler());
    this.playerSession = playerSession;
    startGame();
  }

  void startGame() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      public void run() {
        try {
          Ball ball = field.moveBall();
          playerSession.getBasicRemote().sendText(toJson(ball));
        } catch (IOException e) {
          LOGGER.log(Level.SEVERE, e.getMessage());
        }
      }
    }, 0, 10);
  }

  private String toJson(Ball ball) {
    try {
      return new ObjectMapper().writeValueAsString(ball);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "{}";
    }

  }

}
