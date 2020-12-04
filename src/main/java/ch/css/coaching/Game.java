package ch.css.coaching;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ch.css.coaching.Score.*;

public class Game {

  private final List<Player> players = new ArrayList<>();

  public Game(Player player1, Player player2) {
    players.add(player1);
    players.add(player2);
  }

  public Optional<Player> winner() {
    return players.stream()
      .filter(Player::isWinner)
      .findAny();
  }

  public void scoreBall(Player player) {
    switch (player.getScore()) {
      case LOVE:
        player.setScore(FIFTEEN);
        break;
      case FIFTEEN:
        player.setScore(THIRTY);
        break;
      case THIRTY:
        player.setScore(FORTY);
        break;
      case ADVANTAGE:
        player.wins();
        break;
      case FORTY:
        if (isDeuce()) {
          Player opponent = opponent(player);
          if (opponent.getScore() == ADVANTAGE) {
            opponent.setScore(FORTY);
          } else {
            player.setScore(ADVANTAGE);
          }
        } else {
          player.wins();
        }
        break;
    }
  }

  public boolean isDeuce() {
    return players.stream().allMatch(Player::hasHighScore);
  }


  private Player opponent(Player player) {
    return players.stream()
      .filter(p -> p != player)
      .findFirst()
      .orElseThrow(IllegalStateException::new);
  }

}
