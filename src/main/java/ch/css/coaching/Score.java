package ch.css.coaching;

public enum Score {
  LOVE("0"),
  FIFTEEN("15"),
  THIRTY("30"),
  FORTY("40"),
  ADVANTAGE("40A");

  private final String value;

  Score(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}
