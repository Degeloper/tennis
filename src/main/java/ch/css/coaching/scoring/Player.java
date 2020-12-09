package ch.css.coaching.scoring;

import static ch.css.coaching.scoring.Score.*;

public class Player {

  private Score score;

  public Player() {
    resetScore();
  }

  public boolean hasMatchball(Score opponentScore) {
    if (score == FORTY && opponentScore.isLessThan(FORTY))
      return true;
    return score == ADVANTAGE;
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;
  }

  public Score score() {
    return score;
  }

  public void increaseScore() {
    score = score.next();
  }

  public void decreaseScore() {
    score = score.previous();
  }

  public void resetScore() {
    score = LOVE;
  }
}
