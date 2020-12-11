package ch.css.coaching.game;

import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.score.Player;

public class GameEventsMock implements GameEvents {

  public Field updatedField;

  public Player winner;

  @Override
  public void updateField(Field field) {
    updatedField = field;
  }

  @Override
  public void electWinner(Player winner) {
    this.winner = winner;
  }
}
