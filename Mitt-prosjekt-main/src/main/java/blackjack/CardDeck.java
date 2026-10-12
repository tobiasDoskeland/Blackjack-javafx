package blackjack;

import java.util.Collections;
import java.util.Iterator;

public class CardDeck extends CardContainerImpl {
    
    private char suit;

    public CardDeck(){
        //initialiserer kortstokk. legger til alle kort, alltid 52

        for (int i = 0; i < 4; i++){
            if(i == 0) this.suit = 'S';
            if(i == 1) this.suit = 'H';
            if(i == 2) this.suit = 'D';
            if(i == 3) this.suit = 'C';

            for (int j = 0; j < 13; j++){
                this.cards.add(new Card(this.suit,(j+1)));
            }
        }
    }

    @Override
    public int getCardCount() {
        return super.getCardCount();
    }

    @Override
    public Card getCard(int n){
        return super.getCard(n);
    }

    public void shuffle() {
        //stokker kortstokken
        Collections.shuffle(this.cards);

    }

    public void deal(CardHand cardHand){
        //fjerner øverste kort og gir til spiller
        if (cardHand == null){
            throw new IllegalArgumentException("cant be null");
        }
        Card card = this.cards.removeLast();
        cardHand.addCard(card); 

    }


    @Override
    public Iterator<Card> iterator() {
        return new CardContainerIterator(this);
    }

}
