package ch.css.coaching.game.field;

import ch.css.coaching.game.score.Player;

public class Racket {

  private int y;
  private final int x;
  private final int fieldHeight;
  private final Player player;

  private final int racketHeight = 75;
  private final int racketWidth = 10;
  private final int deltaY = 7;

  public Racket(int x, int fieldHeight, Player player) {
    this.x = x;
    this.fieldHeight = fieldHeight;
    this.y = (fieldHeight - racketHeight) / 2;
    this.player = player;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
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

  public void moveDown() {
    if (y < fieldHeight - racketHeight)
      y += deltaY;
  }

  public void moveUp() {
    if (y > 0)
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
