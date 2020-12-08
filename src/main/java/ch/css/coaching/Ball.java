package ch.css.coaching;

public class Ball {
  private int x;
  private int y;
  private final int radius;


  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getRadius() {
    return radius;
  }

  public Ball(int x, int y, int radius) {
    this.x = x;
    this.y = y;
    this.radius = radius;
  }

  public boolean collideWithBorders(int borderHeight, int deltaY) {
    return y + deltaY > borderHeight-radius || y + deltaY < radius;
  }

  public void move(int deltaX, int deltaY) {
    this.x += deltaX;
    this.y += deltaY;
  }
}
