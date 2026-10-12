package blackjack;


public class BlackjackGame {

    private CardHand spiller = new CardHand();
    private CardHand dealer = new CardHand();
    private CardDeck deck = new CardDeck();

    private boolean spillerVant;
    private boolean spillerBlackjack;
    private boolean spillerBust;
    private boolean dealerVant;
    private boolean dealerBust;
    private boolean dealerBlackjack;
    private boolean draw;

    private double bet;   
    private double saldo; 
    
    public BlackjackGame(double innsats, double saldo){

        if(innsats < 0 || saldo < 0) throw new IllegalArgumentException("cant have negative bet or balance");
        if(innsats > saldo) throw new IllegalStateException("cant bet more than you have bruh");

        //hvor mye man better
        this.bet = innsats;

        this.saldo = saldo;

        //Starttilstanden i spillet
        this.deck.shuffle();

        this.deck.deal(spiller);
        this.deck.deal(dealer);
        this.deck.deal(spiller);
        this.deck.deal(dealer);

        //hvis blackjack funnet
        if(sjekkBlackjack(dealer) || sjekkBlackjack(spiller)){
            blackJackFunnet(sjekkBlackjack(dealer), sjekkBlackjack(spiller));
        }

        
    }

    public CardHand getSpiller(){
        return this.spiller;
    }  
    
    public CardHand getDealer(){
        return this.dealer;
    }

    public double getBet(){
        return this.bet;
    }

    public double getSaldo(){
        return this.saldo;
    }

    //resultatet av runden i pengesum
    public double getResult(){
        if(this.spillerBlackjack){
            return this.bet * 2.5;
        }
        else if(this.draw){
            return this.bet;
        }
        else if(this.spillerVant){
            return this.bet * 2;
        }
        else{
            return 0;
        }
    }

    //beskrivende melding på hvordan runden gikk
    public String getGameString(){
        if(this.spillerBlackjack) return "Spiller fikk blackjack! ";
        else if(this.dealerBlackjack) return "Dealer fikk blackjack! ";
        else if(this.draw) return "Draw, pengene tilbake ";
        else if(this.spillerBust) return "Spiller bust ";
        else if(this.dealerBust) return "Dealer bust ";
        else if(this.spillerVant) return "Spiller vant ";
        else if(this.dealerVant) return "Dealer vant ";
        else return "";
    }

    //gir scoren på hand og justerer hvis man har ess + bust
    public int getAdjustedScore(CardHand hand) {
        int score = hand.getHandScore();
            if (hand.hasAce() && score > 21) {
                return score - 10;
            }
        return score;
    }

    //gir spiller nytt kort
    public void hit(){
        if(this.spillerBust) throw new IllegalStateException("player has busted");
        this.deck.deal(this.spiller);
        sjekkBust(this.spiller, false);
    }

    //på slutten av runden: dealer til dealer helt til dealer når 17
    public void dealerHit(){
        
        while(getAdjustedScore(this.dealer) < 17){
            this.deck.deal(this.dealer);
        }
        sjekkBust(this.dealer, true);

    }

    public void spillOver(){

        //henter score og justerer for Ace
        int spillerScore = getAdjustedScore(this.spiller);
        int dealerScore = getAdjustedScore(this.dealer);

        //hvis spiller har busta
        if(spillerScore > 21){
            sjekkBust(this.spiller, false);
        }
        else{
            if(dealerScore == spillerScore){
                System.out.println("lik score: pengene tilbake");
                this.draw = true;

            }
            else if(dealerScore > spillerScore){
                System.out.println("Dealer vant");
                this.dealerVant = true;
            }
            else {
                System.out.println("Spiller vant");
                this.spillerVant = true;
            }
        }
    
    }

    //sjekker om dealer eller spiller starter runden med blackjack
    private boolean sjekkBlackjack(CardHand cardHand){
        if(cardHand == null) throw new IllegalArgumentException("cardhand cant be null");
        if(getAdjustedScore(cardHand) == 21){
            return true;
        }
        return false;
    }

    //hvos blackjack er funnet
    private void blackJackFunnet(boolean dealer, boolean spiller){
         //hvis dealer og/eller spiller har blackjack
        if(dealer && spiller){
            System.out.println("Pengene tilbake");
            this.draw = true;
        }
        else if(dealer){
            System.out.println("Dealer vant: BLACKJACK");
            this.dealerBlackjack = true;
        }
        else if(spiller){
            System.out.println("Spiller vant: BLACKJACK");
            this.spillerBlackjack = true;
        }
    }

    //sjekker om spiller eller dealer buster
    private void sjekkBust(CardHand cardHand, boolean dealer){

        if(cardHand == null) throw new IllegalArgumentException("cardhand cant be null");

        int score = getAdjustedScore(cardHand);
        
        if(score > 21 && dealer == true){
            System.out.println("Dealer bust. Spiller vant");
            this.spillerVant = true;
            this.dealerBust = true;
            
        }
        else if(score > 21 && dealer == false){
            System.out.println("Spiller bust og dealer vant");
            this.spillerBust = true;
            this.dealerVant = true;
        }
    } 

}
