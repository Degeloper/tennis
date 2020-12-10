package ch.css.coaching.game.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.css.coaching.game.score.Score.ADVANTAGE;
import static ch.css.coaching.game.score.Score.FORTY;

public class Scorer {

  private final List<Player> players = new ArrayList<>();
  private Player winner;

  public Optional<Player> winner() {
    return Optional.ofNullable(winner);
  }

  public void scoreBall(Player scoringPlayer) {
    Player opponent = opponentOf(scoringPlayer);
    if (scoringPlayer.hasMatchball(opponent.getScore()))
      winner = scoringPlayer;
    else if (opponent.getScore() == ADVANTAGE)
      opponent.decreaseScore();
    else
      scoringPlayer.increaseScore();
  }


  public boolean isDeuce() {
    return players.stream()
      .allMatch(p -> p.getScore() == FORTY);
  }

  public Player opponentOf(Player player) {
    return players.stream()
      .filter(p -> p != player)
      .findFirst()
      .orElseThrow(IllegalStateException::new);
  }

  public void addPlayer(Player newPlayer) {
    players.add(newPlayer);
  }

  public void resetPlayers() {
    winner = null;
    players.clear();
  }
}
