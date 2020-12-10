package ch.css.coaching.game.field;

public class Ball {

  private int x;
  private int y;
  private final int radius = 10;

  public Ball(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getRadius() {
    return radius;
  }

  public boolean collidesWithBorders(int borderHeight, int deltaY) {
    return y + deltaY > borderHeight - radius ||
      y + deltaY < radius;
  }

  public void move(int deltaX, int deltaY) {
    this.x += deltaX;
    this.y += deltaY;
  }

  public boolean collidesWithRacket(Racket racket, int dx, int dy) {
    return x + dx <= racket.borderRightX() &&
      x + dx >= racket.borderLeftX() &&
      y + dy >= racket.borderTopY() &&
      y + dy <= racket.borderBottomY();
  }

}
