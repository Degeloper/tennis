package ch.css.coaching.web;

import javax.websocket.MessageHandler;
import java.util.logging.Logger;

public class RacketHandler implements MessageHandler.Partial<String> {

  private static final Logger LOGGER = Logger.getLogger(RacketHandler.class.getName());

  @Override
  public void onMessage(String racketActionString, boolean last) {
    RacketAction racketAction = RacketAction.valueOf(racketActionString);
    LOGGER.info(racketAction + ", last: " + last);
  }
}
