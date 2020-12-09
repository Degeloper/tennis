package ch.css.coaching;

import ch.css.coaching.web.Racket;

import java.util.ArrayList;
import java.util.List;

public class Field {

  private final int height;
  private final int width;
  private int dx = -2;
  private int dy = -2;
  private final int ballRadius = 10;

  private final Ball ball;
  private final List<Racket> rackets = new ArrayList<>();

  public Field(int width, int height) {
    this.width = width;
    this.height = height;
    this.ball = new Ball(width / 2, height / 2, ballRadius);
  }

  public void moveBall() {
    checkCollitions();
    ball.move(dx, dy);
  }

  public Ball getBall() {
    return ball;
  }

  private void checkCollitions() {
    if (ball.collideWithBorders(height, dy))
      dy = -dy;
    if (rackets.stream().anyMatch(r -> ball.collideWithRacket(r, dx, dy)))
      dx = -dx;
  }

  public Racket newRacket() {
    int x = 0;
    if (rackets.size() == 1) {
      x = width - 10;
    }
    Racket newRacket = new Racket(x, height);
    rackets.add(newRacket);
    return newRacket;
  }

  public List<Racket> getRackets() {
    return rackets;
  }
}
