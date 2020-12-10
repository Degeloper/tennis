package ch.css.coaching.game.score;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ch.css.coaching.game.score.Score.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScorerTest {

  private final Player player1 = new Player();
  private final Player player2 = new Player();
  private Scorer scorer;

  @BeforeEach
  void setUp() {
    scorer = new Scorer();
    scorer.addPlayer(player1);
    scorer.addPlayer(player2);
  }

  @Test
  void acceptanceTest() {
    scorer.scoreBall(player1);
    scorer.scoreBall(player1);
    scorer.scoreBall(player1);
    assertThat(player1.getScore()).isEqualTo(FORTY);

    scorer.scoreBall(player2);
    scorer.scoreBall(player2);
    scorer.scoreBall(player2);
    scorer.scoreBall(player2);
    assertThat(player2.getScore()).isEqualTo(ADVANTAGE);

    scorer.scoreBall(player1);
    scorer.scoreBall(player1);
    scorer.scoreBall(player1);
    assertThat(scorer.winner()).contains(player1);
  }

  @Test
  void initialState_noWinner() {
    assertThat(scorer.winner()).isEmpty();
  }

  @Test
  void player1ScoreIsForty_player1Scores_player1IsWinner() {
    player1.setScore(FORTY);

    scorer.scoreBall(player1);

    assertThat(scorer.winner()).contains(player1);
  }

  @Test
  void bothPlayersScoreIsForty_stateIsDeuce() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    assertThat(scorer.isDeuce()).isTrue();
  }

  @Test
  void bothPlayersScoreIsForty_player1Scores_player1HasAdvantage() {
    player1.setScore(FORTY);
    player2.setScore(FORTY);

    scorer.scoreBall(player1);

    assertThat(player1.getScore()).isEqualTo(ADVANTAGE);
    assertThat(scorer.winner()).isEmpty();
  }

  @Test
  void player2HasAdvantage_player1Scores_bothPlayersHaveScoreOfFORTY() {
    player2.setScore(ADVANTAGE);
    player1.setScore(FORTY);

    scorer.scoreBall(player1);

    assertThat(player1.getScore()).isEqualTo(FORTY);
    assertThat(player2.getScore()).isEqualTo(FORTY);
  }

  @Test
  void player1Won_resetPlayers_winnerResets() {
    player1.setScore(THIRTY);

    scorer.scoreBall(player1);
    scorer.resetPlayers();

    assertThat(scorer.winner()).isEmpty();
  }

}
