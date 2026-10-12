package blackjack;

public class Card {
    private char suit;
    private int face;

    public Card(char suit, int face) {
        if ("SHDC".indexOf(suit) == -1) throw new IllegalArgumentException("Invalid suit");
        if (face < 1 || face > 13) throw new IllegalArgumentException("Invalid face");

        this.suit = suit;
        this.face = face;
    }

    public char getSuit() {
        return this.suit;
    }

    public int getFace() {
        return this.face;
    }

    //verdi til blackjack spillet
    public int getFaceValue(){
        if(this.getFace() == 1){
            return 11;
        }
        else if(this.getFace() > 10) {
            return 10;
        }
        else {
            return this.getFace();
        }
        
    }

    public String formatCard(){
        String[] symbols = {"♠", "♥", "♦", "♣"};
        String cardFormatted = "";
        
        //gir symbol til suit
        switch (this.getSuit()) {
            case 'S':
                cardFormatted += symbols[0];
                break;
        
            case 'H':
                cardFormatted += symbols[1];
                break;
            case 'D':
                cardFormatted += symbols[2];
                break;
            case 'C':
                cardFormatted += symbols[3];
                break;
        }

        //gir bokstav til bildekort
        switch (this.getFace()) {
            case 11:
                cardFormatted += "J";
                break;
            case 12:
                cardFormatted += "Q";
                break;
            case 13:
                cardFormatted += "K";
                break;
            case 1:
                cardFormatted += "A";
                break;
        
            default:
                cardFormatted += this.getFace();
                break;
        }
        return cardFormatted;

    }

    @Override
    public String toString() {
        return this.suit + "" + this.face;
    }

}
