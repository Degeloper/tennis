package ch.css.coaching;

public class Player {

  private Score score = Score.LOVE;
  private boolean isWinner;

  public void setScore(Score score) {
    this.score = score;
  }

  public Score getScore() {
    return score;
  }

  public boolean isWinner() {
    return isWinner;
  }

  public void wins() {
    isWinner = true;
  }

  public boolean hasHighScore() {
    return score == Score.FORTY || score == Score.ADVANTAGE;
  }
}
