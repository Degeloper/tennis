package ch.css.coaching;

import ch.css.coaching.web.Racket;

public class Ball {
  private int x;
  private int y;
  private final int radius = 10;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getRadius() {
    return radius;
  }

  public Ball(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean collidesWithBorders(int borderHeight, int deltaY) {
    return y + deltaY > borderHeight - radius ||
      y + deltaY < radius;
  }

  public void move(int deltaX, int deltaY) {
    this.x += deltaX;
    this.y += deltaY;
  }

  public boolean collideWithRacket(Racket racket, int dx, int dy) {
    return x + dx - radius <= racket.borderRightX() &&
      x + dx + radius >= racket.borderLeftX() &&
      y + dy - radius <= racket.borderTopY() &&
      y + dy + radius >= racket.borderBottomY();
  }
}
