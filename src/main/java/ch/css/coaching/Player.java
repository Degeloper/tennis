package ch.css.coaching;

public class Player {

  private int score;

  public void score() {
    score++;
  }

  public boolean isWinner() {
    return score >= 4;
  }
}
