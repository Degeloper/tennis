package ch.css.coaching;

import org.junit.jupiter.api.Test;

import static ch.css.coaching.Score.ADVANTAGE;
import static ch.css.coaching.Score.FORTY;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class GameTest {

  private final Player player1 = new Player();
  private final Player player2 = new Player();
  private final Game game = new Game(player1, player2);


  @Test
  void initialState_noWinner() {
    assertThat(game.winner()).isEmpty();
  }

  @Test
  void player1ScoreIsForty_player1Scores_player1IsWinner() {
    player1.setScore(FORTY);

    game.scoreBall(player1);

    assertThat(game.winner()).contains(player1);
  }

  @Test
  void bothPlayersScoreIsForty_stateIsDeuce() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    assertThat(game.isDeuce()).isTrue();
  }

  @Test
  void bothPlayersScoreIsForty_player1Scores_player1HasAdvantage() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    game.scoreBall(player1);

    assertThat(player1.score()).isEqualTo(ADVANTAGE);
    assertThat(game.winner()).isEmpty();
  }

  @Test
  void player2HasAdvantage_player1Scores_bothPlayersHaveScoreOfFORTY() {
    player2.setScore(ADVANTAGE);
    player1.setScore(FORTY);

    game.scoreBall(player1);

    assertThat(player1.score()).isEqualTo(FORTY);
    assertThat(player2.score()).isEqualTo(FORTY);
  }


}
