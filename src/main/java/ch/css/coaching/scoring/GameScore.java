package ch.css.coaching.scoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ch.css.coaching.scoring.Score.ADVANTAGE;
import static ch.css.coaching.scoring.Score.FORTY;

public class GameScore {

  private final List<Player> players = new ArrayList<>();
  private Player winner;

  public GameScore(Player... players) {
    this.players.addAll(Arrays.asList(players));
  }

  public Optional<Player> winner() {
    return Optional.ofNullable(winner);
  }

  public void scoreBall(Player scoringPlayer) {
    Player opponent = opponentOf(scoringPlayer);
    if (scoringPlayer.hasMatchball(opponent.score()))
      winner = scoringPlayer;
    else if (opponent.score() == ADVANTAGE)
      opponent.decreaseScore();
    else
      scoringPlayer.increaseScore();
  }


  public boolean isDeuce() {
    return players.stream()
      .allMatch(p -> p.score() == FORTY);
  }

  public Player opponentOf(Player player) {
    return players.stream()
      .filter(p -> p != player)
      .findFirst()
      .orElseThrow(IllegalStateException::new);
  }

}
