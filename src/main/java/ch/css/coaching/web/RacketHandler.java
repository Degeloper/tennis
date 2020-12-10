package ch.css.coaching.web;

import ch.css.coaching.game.field.Racket;

import javax.websocket.MessageHandler;

import static ch.css.coaching.web.RacketHandler.RacketAction.valueOf;

public class RacketHandler implements MessageHandler.Partial<String> {

  private final Racket racket;

  public RacketHandler(Racket racket) {
    this.racket = racket;
  }

  @Override
  public void onMessage(String racketAction, boolean last) {
    switch (valueOf(racketAction)) {
      case UP:
        racket.moveUp();
        break;
      case DOWN:
        racket.moveDown();
        break;
    }
  }

  enum RacketAction {
    UP, DOWN
  }

}
