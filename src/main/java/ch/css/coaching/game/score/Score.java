package ch.css.coaching.game.score;

public enum Score {

  LOVE(0) {
    public Score previous() {
      return this;
    }
  },
  FIFTEEN(15),
  THIRTY(30),
  FORTY(40),
  ADVANTAGE(41) {
    public Score next() {
      return this;
    }
  };

  private final int value;

  Score(int value) {
    this.value = value;
  }

  public boolean isLessThan(Score score) {
    return value < score.value;
  }

  public Score next() {
    return values()[ordinal() + 1];
  }

  public Score previous() {
    return values()[ordinal() - 1];
  }

}
