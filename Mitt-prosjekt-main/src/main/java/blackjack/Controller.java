package blackjack;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    private TextField bet;

    @FXML
    private Label result, your_cards, dealer_cards, saldo, your_score, dealer_score, resultString, fileText;

    private BlackjackGame blackjackGame;
    private boolean isOver = true; //game-state variabel

    private CardHand spiller;
    private CardHand dealer;
    private ToFile filehandler = new ToFile("test.txt");
    private List<ArrayList<String>> gameresult = new ArrayList<>();



    //initater ny runde blackjack
    private void initBlackjackGame(double bet) {
        this.blackjackGame = new BlackjackGame(bet, Double.parseDouble(this.saldo.getText()));
        this.isOver = false;
    }

    @FXML
    private void doubleBet(){
        if(!this.isOver){            
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("cant increase bet mid round");
            alert.setHeaderText("Warning");
            alert.showAndWait();

            throw new IllegalStateException("cant increase bet mid round");
        }
        if(Double.parseDouble(this.bet.getText()) < 0){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("cant bet negative moneh");
            alert.setHeaderText("Warning");
            alert.showAndWait();
            throw new IllegalArgumentException("cant bet negative moneh");
        }

        double bet = Double.parseDouble(this.bet.getText());
        bet *= 2;

        if(bet > Double.parseDouble(this.saldo.getText())){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Too little money fam");
            alert.setHeaderText("Warning");
            alert.showAndWait();
            throw new IllegalStateException("To little money fam");
        }
        
        this.bet.setText(String.valueOf(bet));
    }

    @FXML
    private void handleButtonClick() {
        //hindre spiller for å starte ny runde før nåværende er over
        if(!this.isOver){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("cant start new game before game is over");
            alert.setHeaderText("Warning");
            alert.showAndWait();
            throw new IllegalStateException("cant start new game before game is over");
        }
        try{
            //nytt spill og spiller/dealer
            initBlackjackGame(Double.parseDouble(bet.getText()));
            this.spiller = this.blackjackGame.getSpiller();
            this.dealer = this.blackjackGame.getDealer();

            //oppdaterer saldo
            double gammelSaldo = Double.parseDouble(this.saldo.getText());
            gammelSaldo -= Double.parseDouble(this.bet.getText());
            this.saldo.setText(String.valueOf(gammelSaldo));

            //resetter resultat mellom runder
            this.result.setText(null);
            this.resultString.setText(null);

            //spillers kort
            this.your_cards.setText(this.spiller.getCard(0).formatCard() + "\t" + this.spiller.getCard(1).formatCard());
            //om ess
            if(this.spiller.hasAce()){
                this.your_score.setText(String.valueOf(this.spiller.getHandScore()) + " / " + String.valueOf(this.spiller.getHandScore() - 10));
            }else{
                this.your_score.setText(String.valueOf(this.spiller.getHandScore()));
            }

            //dealers kort
            this.dealer_cards.setText("Skjult \t" + this.dealer.getCard(1).formatCard());
            //om ess
            if(this.dealer.getCard(1).getFace() == 1){
                this.dealer_score.setText("11 / 1");
            }else{
                this.dealer_score.setText(String.valueOf(this.dealer.getCard(1).getFaceValue()));
            }

            //hvis spiller eller dealer har fått blackjack, slutt runde tidlig
            if(this.blackjackGame.getGameString().contains("blackjack")){
                endState();
                return;
            }
            
        } catch (NumberFormatException e) {
            result.setText("Your bet is invalid");
            result.setTextFill(Color.RED);
        }
    }

    @FXML
    private void hit(){
        //hindre bruker for å hitte etter runde er over: stand, bust, blackjack
        if(this.isOver){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("cant hit when round is over");
            alert.setHeaderText("Warning");
            alert.showAndWait();
            throw new IllegalStateException("round is over");
        }
        this.blackjackGame.hit();

        //oppdatere dine kort
        String oldtext = this.your_cards.getText();
        String newtext = "\t" + this.spiller.getCard(this.spiller.getCardCount() -1).formatCard();
        this.your_cards.setText(oldtext + newtext);

        //hvis man har ess, skriv begge scores
        if(this.spiller.hasAce()){
                this.your_score.setText(String.valueOf(this.spiller.getHandScore()) + " / " + String.valueOf(this.spiller.getHandScore() - 10));
        }
        else{
            this.your_score.setText(String.valueOf(this.spiller.getHandScore()));
        }

        //hvis spiller har bust -- end spill
        if(this.blackjackGame.getAdjustedScore(this.spiller) > 21){
            endState();
        }

    }

    @FXML
    private void stand(){
        //kan ikke stande flere ganger, etter spill er over
        if(this.isOver){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("cant stand multiple times in same round");
            alert.setHeaderText("Warning");
            alert.showAndWait();
            throw new IllegalStateException("round is over");
        }

        //lar dealer hitte og ender spill
        this.blackjackGame.dealerHit();
        this.blackjackGame.spillOver();
        

        //oppdaterer kortene til dealer
        String dealerkort = "";
        for (int i = 0; i < this.dealer.getCardCount(); i++){
            dealerkort += this.dealer.getCard(i).formatCard() + "\t";
        }
        this.dealer_cards.setText(dealerkort);

        //hvis dealer har ess, endre score
        if(this.dealer.hasAce()){
                this.dealer_score.setText(String.valueOf(this.dealer.getHandScore()) + " / " + String.valueOf(this.dealer.getHandScore() - 10));
        }
        else{
            this.dealer_score.setText(String.valueOf(this.dealer.getHandScore()));
        }

        //ender spill
        endState();

        
    }

    @FXML
    private void writeFile(){
        this.filehandler.writeToFile(this.gameresult);

    }

    @FXML
    private void loadFile(){

        String txt = "";
        int i = 1;
        for (ArrayList<String> s : this.filehandler.loadFile()) {
            txt += "runde " + i + " " + s + "\n";
            i++;
        }
        this.fileText.setText(txt);

    }

    private void endState(){
        //hent pengeresultatet
        double penger = blackjackGame.getResult();

        //oppdater resultat-labelene
        this.result.setText("You won " + String.valueOf(penger));
        String txt = this.blackjackGame.getGameString();
        this.resultString.setText(txt);

        //oppdater saldo
        double gamlePenger = Double.parseDouble(this.saldo.getText());
        this.saldo.setText(String.valueOf(gamlePenger + penger));

        //state
        this.isOver = true;

        ArrayList<String> al = new ArrayList<>(List.of("Dealer: " + this.dealer_score.getText(), "Spiller: " + this.your_score.getText(), "Bet: " + this.bet.getText(), "Result: " + this.resultString.getText() + " " + this.result.getText()));
        gameresult.add(al);

    }



}
