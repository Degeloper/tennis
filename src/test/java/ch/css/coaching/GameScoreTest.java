package ch.css.coaching;

import ch.css.coaching.scoring.GameScore;
import ch.css.coaching.scoring.Player;
import org.junit.jupiter.api.Test;

import static ch.css.coaching.scoring.Score.ADVANTAGE;
import static ch.css.coaching.scoring.Score.FORTY;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class GameScoreTest {

  private final Player player1 = new Player();
  private final Player player2 = new Player();
  private final GameScore gameScore = new GameScore(player1, player2);


  @Test
  void initialState_noWinner() {
    assertThat(gameScore.winner()).isEmpty();
  }

  @Test
  void player1ScoreIsForty_player1Scores_player1IsWinner() {
    player1.setScore(FORTY);

    gameScore.scoreBall(player1);

    assertThat(gameScore.winner()).contains(player1);
  }

  @Test
  void bothPlayersScoreIsForty_stateIsDeuce() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    assertThat(gameScore.isDeuce()).isTrue();
  }

  @Test
  void bothPlayersScoreIsForty_player1Scores_player1HasAdvantage() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    gameScore.scoreBall(player1);

    assertThat(player1.score()).isEqualTo(ADVANTAGE);
    assertThat(gameScore.winner()).isEmpty();
  }

  @Test
  void player2HasAdvantage_player1Scores_bothPlayersHaveScoreOfFORTY() {
    player2.setScore(ADVANTAGE);
    player1.setScore(FORTY);

    gameScore.scoreBall(player1);

    assertThat(player1.score()).isEqualTo(FORTY);
    assertThat(player2.score()).isEqualTo(FORTY);
  }


}
