package ch.css.coaching.game.field;

import ch.css.coaching.game.score.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparingInt;

public class Field {

  private int dx = 2;
  private int dy = -2;
  private Ball ball;
  private final int height;
  private final int width;
  private final List<Racket> rackets = new ArrayList<>();

  public Field(int width, int height) {
    this.width = width;
    this.height = height;
    resetBall();
  }

  public void resetBall() {
    dx = -dx;
    this.ball = new Ball(width / 2, height / 2);
  }

  public void moveBall() {
    checkCollisions();
    ball.move(dx, dy);
  }

  public List<Racket> getRackets() {
    return rackets;
  }

  public Ball getBall() {
    return ball;
  }

  public Racket newRacket(Player player) {
    int x = 0;
    if (rackets.size() == 1)
      x = width - 10;
    Racket newRacket = new Racket(x, height, player);
    rackets.add(newRacket);
    return newRacket;
  }

  public Optional<Racket> racketThatMissedTheBall() {
    if (ball.getX() < 0)
      return rackets.stream().min(comparingInt(Racket::getX));
    if (ball.getX() > width)
      return rackets.stream().max(comparingInt(Racket::getX));
    return Optional.empty();
  }

  public void resetRackets() {
    rackets.clear();
  }

  private void checkCollisions() {
    if (ball.collidesWithBorders(height, dy))
      dy = -dy;
    if (ballCollidesWithAnyRacket())
      dx = -dx;
  }

  private boolean ballCollidesWithAnyRacket() {
    return rackets.stream()
      .anyMatch(r -> ball.collidesWithRacket(r, dx, dy));
  }
}
