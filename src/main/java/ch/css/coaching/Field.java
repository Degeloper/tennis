package ch.css.coaching;

import ch.css.coaching.web.Racket;

public class Field {

  private final int height;
  private final int width;
  private int dx = -2;
  private int dy = -2;
  private final int ballRadius = 10;

  private Ball ball;
  private Racket racket;

  public Field(int width, int height) {
    this.width = width;
    this.height = height;
    initBall();
  }

  private void initBall() {
    int middleX = width / 2;
    int middleY = height / 2;
    ball = new Ball(middleX, middleY, ballRadius);
  }

  public void moveBall() {
    checkCollitions();
    ball.move(dx, dy);
  }

  public Ball getBall() {
    return ball;
  }

  private void checkCollitions() {
    if(ball.collideWithBorders(height, dy))
      dy = -dy;
    if(ball.collideWithRacket(racket, dx, dy))
      dx = -dx;
  }

  public Racket newRacket() {
    racket = new Racket(0, height);
    return racket;
  }

  public Racket getRacket() {
    return racket;
  }
}
