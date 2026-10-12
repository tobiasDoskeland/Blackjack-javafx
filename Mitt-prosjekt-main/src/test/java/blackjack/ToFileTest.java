package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToFileTest {
    ToFile filehandler;
    List<ArrayList<String>> l;
    
    @BeforeEach
    public void setup(){
        this.filehandler = new ToFile("mjau");
        ArrayList<String> a1 = new ArrayList<>(List.of("Dealer: 17", "Spiller: 19", "Bet: 50", "Result: 100"));
        ArrayList<String> a2 = new ArrayList<>(List.of("Dealer: 21", "Spiller: 15", "Bet: 50", "Result: 0"));

        this.l = List.of(a1,a2);
    }

    @Test
    void ToFile(){
        assertThrows(IllegalArgumentException.class, () -> new ToFile(null));
    }
    
    @Test
    void testWriteToFile() {
        this.filehandler.writeToFile(l);

        assertEquals(this.l, this.filehandler.loadFile());

    }
    
    @Test
    void testLoadFile() {
        assertEquals(this.l, this.filehandler.loadFile());
    }


}
