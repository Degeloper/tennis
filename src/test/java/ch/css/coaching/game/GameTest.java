package ch.css.coaching.game;

import ch.css.coaching.game.field.Field;
import ch.css.coaching.game.score.Player;
import ch.css.coaching.game.score.Scorer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameTest {

  @Mock
  private Field field;

  @Mock
  private Scorer scorer;

  @InjectMocks
  private Game cut;

  @Test
  void initialState_gameIsNotReady() {
    assertThat(cut.isReady()).isFalse();
  }

  @Test
  void subscribe1Player_gameIsNotReady() {
    cut.subscribeToGameEvents(new GameEventsMock());

    assertThat(cut.isReady()).isFalse();
  }

  @Test
  void subscribe2Players_gameIsReady() {
    cut.subscribeToGameEvents(new GameEventsMock());
    cut.subscribeToGameEvents(new GameEventsMock());

    assertThat(cut.isReady()).isTrue();
  }

  @Test
  void gameEventsAreSubscribed_runTheGame_subscribersReceiveUpdatedField() {
    when(scorer.winner()).thenReturn(Optional.empty());
    when(field.racketThatMissedTheBall()).thenReturn(Optional.empty());

    GameEventsMock gameEventsMock1 = new GameEventsMock();
    cut.subscribeToGameEvents(gameEventsMock1);

    GameEventsMock gameEventsMock2 = new GameEventsMock();
    cut.subscribeToGameEvents(gameEventsMock2);

    cut.run();

    assertThat(gameEventsMock1.updatedField).isEqualTo(field);
    assertThat(gameEventsMock2.updatedField).isEqualTo(field);
  }

  @Test
  void gameEventsAreSubscribedWinnerIsElected_runTheGame_subscribersReceiveWinningPlayer() {
    Player winner = new Player();
    when(scorer.winner()).thenReturn(Optional.of(winner));
    when(field.racketThatMissedTheBall()).thenReturn(Optional.empty());

    GameEventsMock gameEventsMock1 = new GameEventsMock();
    cut.subscribeToGameEvents(gameEventsMock1);

    GameEventsMock gameEventsMock2 = new GameEventsMock();
    cut.subscribeToGameEvents(gameEventsMock2);

    cut.run();

    assertThat(gameEventsMock1.winner).isEqualTo(winner);
    assertThat(gameEventsMock2.winner).isEqualTo(winner);
  }

}
