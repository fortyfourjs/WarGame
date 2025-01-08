import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;//gui

public class Game {

    private List<Cards> player1Deck = new ArrayList<>();
    private List<Cards> player2Deck = new ArrayList<>();

    public Game(List<Cards> player1Deck, List<Cards> player2Deck) {
        this.player1Deck = player1Deck;
        this.player2Deck = player2Deck;
    }
    public Game(){
        this.player1Deck = new ArrayList<>();
        this.player2Deck = new ArrayList<>();
    }
    public List<Cards> getPlayer1Deck() {return player1Deck;}
    public List<Cards> getPlayer2Deck() {return player2Deck;}


    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame("War Game");

        WarGameGUI gui = new WarGameGUI(game);
        frame.setContentPane(gui.getContentPane());


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);

    }

    private void start() {
        System.out.println("Welcome to the War Game!");

        //cream pachetul de joc si il amestecam
        List<Cards> fullDeck = Cards.createDeck();
        shuffleDeck(fullDeck);

        //il impartim jucatorilor
        int halfDeck = fullDeck.size() / 2;
        player1Deck = new ArrayList<>(fullDeck.subList(0, halfDeck));
        player2Deck = new ArrayList<>(fullDeck.subList(halfDeck, fullDeck.size()));
        //start the game
        playGame("player 1", "player 2");
    }
    private void playGame(String namePlayer1, String namePlayer2) {
        while(!player1Deck.isEmpty() && !player2Deck.isEmpty())
        {
            Cards player1TopCard = player1Deck.remove(0);
            Cards player2TopCard = player2Deck.remove(0);

            System.out.println(player1TopCard + " vs " + player2TopCard);

            int comparable = player1TopCard.compareTo(player2TopCard);

            if(comparable > 0){
                System.out.println(namePlayer1 + " wins!");
                player1Deck.add(player1TopCard);
                player1Deck.add(player2TopCard);
            }
            else if(comparable < 0){
                System.out.println(namePlayer2 + " wins!");
                player2Deck.add(player1TopCard);
                player2Deck.add(player2TopCard);
            }
            else
            {
                System.out.println("War!");
                List<Cards> warPile = new ArrayList<>();
                warPile.add(player1TopCard);
                warPile.add(player2TopCard);

                handleWar(namePlayer1, namePlayer2, warPile);
            }
            System.out.println("Player 1 Deck: " + player1Deck.size() + " cards");
            System.out.println("Player 2 Deck: " + player2Deck.size() + " cards");
            }

        System.out.println(player1Deck.isEmpty() ? namePlayer2 + "wins!" : namePlayer1 + " wins!");
        System.out.println("Total cards in play after war: " + (player1Deck.size() + player2Deck.size()));

    }

    //amestecam pachetul
    static void shuffleDeck(List<Cards> deck) {
        Collections.shuffle(deck);
    }
    //comapram cartile si determinam un castigator
    static String determineWinner(int comparable, String playerOneName, String playerTwoName) {
        if (comparable == -1) {
            return playerTwoName;
        }
        if (comparable == 1) {
            return playerOneName;
        } else {
            return "War";
        }

    }
    private void handleWarFix(String namePlayer1, String namePlayer2){
        List<Cards> warPile = new ArrayList<>();
        handleWar(namePlayer1, namePlayer2, warPile);
    }
    private void handleWar(String namePlayer1, String namePlayer2, List<Cards>warPile){
        System.out.println("War!");

        if (player1Deck.size() < 4 || player2Deck.size() < 4) {
            if(player1Deck.size() < 4) {
                System.out.println(namePlayer2 + " wins the game" + namePlayer1 + " doesn't have enough cards left.");

                player2Deck.addAll(player1Deck);
                player2Deck.addAll(warPile);
                player1Deck.clear();
            }
            else
            {
                System.out.println(namePlayer1 + " wins the game " + namePlayer2 + " doesn't have enough cards left.");

                player1Deck.addAll(player2Deck);
                player1Deck.addAll(warPile);
                player2Deck.clear();
            }
            warPile.clear();
            return;
        }

        for (int i = 0; i < 3; i++){
            warPile.add(player1Deck.remove(0));
            warPile.add(player2Deck.remove(0));
        }
        Cards player1WarCard = player1Deck.remove(0);
        Cards player2WarCard = player2Deck.remove(0);
        warPile.add(player1WarCard);
        warPile.add(player2WarCard);

        System.out.println("war cards are: " + player1WarCard + " and " + player2WarCard);

        int comparable = player1WarCard.compareTo(player2WarCard);
        if(comparable > 0)
        {
            System.out.println(namePlayer1 + " wins the war!");
            player1Deck.addAll(warPile);
        }
        else if(comparable < 0)
        {
            System.out.println(namePlayer2 + " wins the war!");
            player2Deck.addAll(warPile);
        }
        else
        {
            System.out.println("War!");
            handleWar(namePlayer1, namePlayer2, warPile);
        }
        warPile.clear();
    }

}
