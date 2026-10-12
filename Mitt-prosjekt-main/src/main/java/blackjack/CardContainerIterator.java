package blackjack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CardContainerIterator implements Iterator<Card>{

    private CardContainer cardContainer;
    private int index = 0;

    public CardContainerIterator(CardContainer cardContainer){
        this.cardContainer = cardContainer;
    }

    @Override
    public boolean hasNext(){
        return index < cardContainer.getCardCount();
    }
    
    @Override
    public Card next(){
        if (!hasNext()) {
            throw new NoSuchElementException("har ikke flere elementer");
        }

        return cardContainer.getCard(index++);
    }  
}
