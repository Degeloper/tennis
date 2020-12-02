package ch.css.coaching;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class GameTest {

    @Test
    void player1IsWinner_ifScored4Times() {
      Player player1 = new Player();
      player1.score();
      player1.score();
      player1.score();
      player1.score();
      assertThat(player1.isWinner()).isTrue();
    }

    @Test
    void player1IsNotWinner_ifNeverScored() {
      Player player1 = new Player();
      assertThat(player1.isWinner()).isFalse();
    }

  @Test
  void player1IsNotWinner_ifScored3Times() {
    Player player1 = new Player();
    player1.score();
    player1.score();
    player1.score();
    assertThat(player1.isWinner()).isFalse();
  }

}
