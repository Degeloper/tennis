package ch.css.coaching;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.css.coaching.Score.ADVANTAGE;
import static ch.css.coaching.Score.FORTY;

public class Game {

  private final List<Player> players = new ArrayList<>();
  private Player winner;

  public Game(Player player1, Player player2) {
    players.add(player1);
    players.add(player2);
  }

  public Optional<Player> winner() {
    return Optional.ofNullable(winner);
  }

  public void scoreBall(Player player) {
    Player opponent = opponent(player);
    if (player.hasMatchball(opponent.score())) {
      winner = player;
      players.forEach(Player::resetScore);
    } else if (opponent.score() == ADVANTAGE) {
      opponent.decreaseScore();
    } else {
      player.increaseScore();
    }
  }

  public boolean isDeuce() {
    return players.stream()
      .allMatch(p -> p.score() == FORTY);
  }

  private Player opponent(Player player) {
    return players.stream()
      .filter(p -> p != player)
      .findFirst()
      .orElseThrow(IllegalStateException::new);
  }

}
