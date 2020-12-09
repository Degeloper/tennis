package ch.css.coaching.playground;

import ch.css.coaching.scoring.Player;
import ch.css.coaching.scoring.Scorer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;
import java.util.function.Consumer;

public class Game extends TimerTask {

  private final Scorer scorer = new Scorer();
  private final Field field;
  private final List<Consumer<Field>> fieldConsumers = new ArrayList<>();
  private final List<Consumer<Player>> winnerConsumers = new ArrayList<>();

  public Game() {
    field = new Field(480, 320);
  }

  @Override
  public void run() {
    if (scorer.winner().isPresent())
      informWinnerConsumersAndResetGame(scorer.winner().get());
    Optional<Racket> missedRacket = field.missedRacket();
    if (missedRacket.isPresent())
      givePointToOpponentOf(missedRacket.get().getPlayer());
    else
      field.moveBall();
    fieldConsumers.forEach(c -> c.accept(field));
  }

  public void subscribeFieldUpdate(Consumer<Field> fieldConsumer) {
    this.fieldConsumers.add(fieldConsumer);
  }

  public void subscribeWinnerUpdate(Consumer<Player> winnerConsumer) {
    this.winnerConsumers.add(winnerConsumer);
  }

  public Racket newRacketFor(Player newPlayer) {
    scorer.addPlayer(newPlayer);
    return field.newRacket(newPlayer);
  }

  private void givePointToOpponentOf(Player player) {
    Player opponent = scorer.opponentOf(player);
    scorer.scoreBall(opponent);
    field.initBall();
  }

  private void informWinnerConsumersAndResetGame(Player winningPlayer) {
    winnerConsumers.forEach(c -> c.accept(winningPlayer));
    fieldConsumers.clear();
    winnerConsumers.clear();
    field.resetRackets();
    scorer.resetPlayers();
  }

  public boolean isReady() {
    return winnerConsumers.size() == 2;
  }
}
