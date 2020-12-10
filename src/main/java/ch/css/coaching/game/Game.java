package ch.css.coaching.game;

import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.field.Racket;
import ch.css.coaching.game.score.Player;
import ch.css.coaching.game.score.Scorer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;

public class Game extends TimerTask {

  private final Field field;
  private final Scorer scorer = new Scorer();
  private final List<GameEvents> gameEventsConsumers = new ArrayList<>();

  public Game(Field field) {
    this.field = field;
  }

  @Override
  public void run() {
    if (scorer.winner().isPresent())
      electWinnerAndResetGame(scorer.winner().get());

    Optional<Racket> missedRacket = field.racketThatMissedTheBall();
    if (missedRacket.isPresent())
      givePointToOpponentAndResetBall(missedRacket.get().getPlayer());
    else
      field.moveBall();

    gameEventsConsumers.forEach(c -> c.updateField(field));
  }

  public void subscribeToGameEvents(GameEvents gameEventConsumer) {
    this.gameEventsConsumers.add(gameEventConsumer);
  }

  public Racket newRacketFor(Player newPlayer) {
    scorer.addPlayer(newPlayer);
    return field.newRacket(newPlayer);
  }

  public boolean isReady() {
    return gameEventsConsumers.size() == 2;
  }

  private void givePointToOpponentAndResetBall(Player player) {
    Player opponent = scorer.opponentOf(player);
    scorer.scoreBall(opponent);
    field.resetBall();
  }

  private void electWinnerAndResetGame(Player winningPlayer) {
    gameEventsConsumers.forEach(c -> c.electWinner(winningPlayer));
    gameEventsConsumers.clear();
    field.resetRackets();
    scorer.resetPlayers();
  }

}
