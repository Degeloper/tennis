package ch.css.coaching;

import ch.css.coaching.web.Racket;

import java.util.ArrayList;
import java.util.List;

public class Field {

  private final int height;
  private final int width;
  private int dx = -2;
  private int dy = -2;

  private Ball ball;
  private final List<Racket> rackets = new ArrayList<>();

  public Field(int width, int height) {
    this.width = width;
    this.height = height;
    initBall();
  }

  public void initBall() {
    this.ball = new Ball(width / 2, height / 2);
  }

  public void moveBall() {
    checkCollitions();
    ball.move(dx, dy);
  }

  public Ball getBall() {
    return ball;
  }

  private void checkCollitions() {
    if (ball.collidesWithBorders(height, dy))
      dy = -dy;
    if (ballCollidesAnyRacket())
      dx = -dx;
  }

  private boolean ballCollidesAnyRacket() {
    return rackets.stream()
      .anyMatch(r -> ball.collideWithRacket(r, dx, dy));
  }

  public Racket newRacket() {
    int x = 0;
    if (rackets.size() == 1)
      x = width - 10;
    Racket newRacket = new Racket(x, height);
    rackets.add(newRacket);
    return newRacket;
  }

  public List<Racket> getRackets() {
    return rackets;
  }

  public boolean ballIsOutside() {
    return ball.getX() < 0 || ball.getX() > width;
  }

}
