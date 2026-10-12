package blackjack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardContainerImpl implements CardContainer{

    protected List<Card> cards = new ArrayList<>();


    @Override
    public int getCardCount() {
        return cards.size();
    }

    @Override
    public Card getCard(int n) {
        if(n >= this.cards.size()) throw new IllegalArgumentException("index out of range");
        if(n < 0) throw new IllegalArgumentException("index out of range");
        return this.cards.get(n);
    }

    @Override
    public Iterator<Card> iterator() {
        return new CardContainerIterator(this);
    }
    
}
