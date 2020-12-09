package ch.css.coaching.web;

import javax.websocket.MessageHandler;

public class RacketHandler implements MessageHandler.Partial<String> {

  private final Racket racket;

  public RacketHandler(Racket racket) {
    this.racket = racket;
  }

  @Override
  public void onMessage(String racketActionString, boolean last) {
    RacketAction racketAction = RacketAction.valueOf(racketActionString);
    racket.move(racketAction);
  }
}