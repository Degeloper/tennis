package ch.css.coaching.web;

import ch.css.coaching.game.GameEvents;
import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.score.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;
import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

public class GameEventsHandler implements GameEvents {

  private static final Logger LOGGER = Logger.getLogger(GameEventsHandler.class.getName());

  private final Player player;
  private final Session session;

  public GameEventsHandler(Session session, Player player) {
    this.session = session;
    this.player = player;
  }

  @Override
  public void updateField(Field field) {
    try {
      session.getBasicRemote().sendText(toJson(field));
    } catch (IOException e) {
      LOGGER.log(SEVERE, "Update fields failed", e);
    }
  }

  @Override
  public void electWinner(Player winner) {
    try {
      if (player == winner)
        session.close(new CloseReason(NORMAL_CLOSURE, "YOU WON!"));
      else
        session.close(new CloseReason(NORMAL_CLOSURE, "YOU LOST!"));
    } catch (IOException e) {
      LOGGER.log(SEVERE, "Close session failed", e);
    }
  }

  private String toJson(Field field) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(field);
  }

}
