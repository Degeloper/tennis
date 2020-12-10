package ch.css.coaching.game;

import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.score.Player;

public interface GameEvents {

  void updateField(Field field);

  void electWinner(Player winner);

}
