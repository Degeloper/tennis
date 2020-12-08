package ch.css.coaching;

public class Field {

  private final int height;
  private final int width;
  private int dx = 2;
  private int dy = -2;
  private final int ballRadius = 10;

  private Ball ball;

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

  public Ball moveBall() {
    checkCollitions();
    ball.move(dx, dy);
    return ball;
  }

  private void checkCollitions() {
    if(ball.getX() + dx > width - ballRadius || ball.getX() + dx < ballRadius) {
      dx = -dx;
    }
    if(ball.collideWithBorders(height, dy)) {
      dy = -dy;
    }
  }
}
