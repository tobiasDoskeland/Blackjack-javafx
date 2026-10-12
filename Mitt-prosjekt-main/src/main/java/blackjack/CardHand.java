package blackjack;

import java.util.Iterator;

public class CardHand extends CardContainerImpl{
    
    private boolean hasAce = false;

    @Override
    public int getCardCount(){
        return super.getCardCount();
    }

    @Override
    public Card getCard(int i){
        return super.getCard(i);
    }

    public int getHandScore(){
        int sum = 0;

        for (Card card : this.cards){
            if(card.getFace() == 1){
                sum += 11;
                this.hasAce = true;
            }
            else if(card.getFace() >= 10){
                sum += 10;
            }
            else{
                sum += card.getFace();
            }
        }

        return sum;
    }

    public boolean hasAce(){
        return this.hasAce;
    }

    public void setAce(boolean x){
        this.hasAce = x;
    }

    public void addCard(Card card){
        if(card == null){
            throw new IllegalArgumentException("card cant be empty");
        }

        this.cards.add(card);
        super.cards = this.cards;
    }


    @Override
    public Iterator<Card> iterator() {
        return new CardContainerIterator(this);
    }

}
