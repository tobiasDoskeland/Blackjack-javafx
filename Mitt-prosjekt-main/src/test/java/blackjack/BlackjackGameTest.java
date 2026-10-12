package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    
    BlackjackGame game;

    @BeforeEach
    public void setup(){
        this.game = new BlackjackGame(50, 100);
    }
    
    @Test
    @org.junit.jupiter.api.DisplayName("tester constructor")
    void BlackjackGame(){
        
        assertEquals(50, game.getBet());
        assertEquals(100, game.getSaldo());

        BlackjackGame game2 = new BlackjackGame(5, 100);
        
        assertEquals(5, game2.getBet());
        assertEquals(100, game2.getSaldo());

        assertThrows(IllegalArgumentException.class, () -> new BlackjackGame(-10, 0));
        assertThrows(IllegalStateException.class, () -> new BlackjackGame(10, 0));

    }

    
    @Test
    void testDealerHit() {
        CardHand dealer = this.game.getDealer();
        int handsize = dealer.getCardCount();

        this.game.dealerHit();

        assertNotEquals(handsize, dealer.getCardCount());
        

    }

    @Test
    void testGetDealer() {
        //hvordan teste dette?
    }

    @Test
    void testGetGameString() {
        //hvordan teste dette?
        
    }

    @Test
    void testGetResult() {
        //hvordan teste dette?
    }

    @Test
    void testGetSpiller() {
        //hvordan teste dette?
    }

    @Test
    void testGetBet(){
        assertEquals(50, game.getBet());
    }

    @Test
    void testGetSaldo(){
        assertEquals(100, game.getSaldo());
    }

    @Test
    @DisplayName("sjekker hit funksjonen")
    void testHit() {

        int handsize = this.game.getSpiller().getCardCount();
        this.game.hit();
        assertEquals(handsize + 1, this.game.getSpiller().getCardCount(),
        "the handsize should be one larger");

        assertThrows(IllegalStateException.class,() -> {
            for(int i = 0; i < 10; i++){
                this.game.hit();
            }
        }, "player should bust");

    }

    @Test
    void testSpillOver() {
        //hvordan teste dette?
    }
}
