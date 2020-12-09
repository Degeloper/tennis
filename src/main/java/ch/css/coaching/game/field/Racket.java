package ch.css.coaching.game.field;

import ch.css.coaching.game.score.Player;

import static ch.css.coaching.game.field.RacketAction.DOWN;
import static ch.css.coaching.game.field.RacketAction.UP;

public class Racket {

  private final int racketHeight = 75;
  private final int racketWidth = 10;

  private final Player player;

  private int y;
  private final int x;
  private final int deltaY = 7;
  private final int fieldHeight;
  public int getY() {
    return y;
  }

  public int getX() {
    return x;
  }

  public int getHeight() {
    return racketHeight;
  }

  public int getWidth() {
    return racketWidth;
  }

  public Player getPlayer() {
    return player;
  }

  public Racket(int x, int fieldHeight, Player player) {
    this.x = x;
    this.fieldHeight = fieldHeight;
    this.y = (fieldHeight - racketHeight)  / 2;
    this.player = player;
  }

  public void move(RacketAction racketAction) {
    if(racketAction == UP)
      moveUp();
    else if(racketAction == DOWN)
      moveDown();
  }

  private void moveDown() {
    if(y < fieldHeight)
      y += deltaY;
  }

  private void moveUp() {
    if(y > 0)
      y -= deltaY;
  }

  public int borderRightX() {
    return x + racketWidth;
  }

  public int borderLeftX() {
    return x;
  }

  public int borderTopY() {
    return y;
  }

  public int borderBottomY() {
    return y + racketHeight;
  }
}
