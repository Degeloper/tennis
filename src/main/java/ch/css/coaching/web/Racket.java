package ch.css.coaching.web;

import static ch.css.coaching.web.RacketAction.DOWN;
import static ch.css.coaching.web.RacketAction.UP;

public class Racket {

  private final int racketHeight = 75;
  private final int racketWidth = 10;
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

  public Racket(int x, int fieldHeight) {
    this.x = x;
    this.fieldHeight = fieldHeight;
    this.y = fieldHeight / 2;
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
    return x + racketWidth / 2;
  }

  public int borderLeftX() {
    return x - racketWidth / 2;
  }

  public int borderTopY() {
    return y + racketHeight / 2;
  }

  public int borderBottomY() {
    return y - racketHeight / 2;
  }
}
