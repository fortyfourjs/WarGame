import java.util.ArrayList;
import java.util.List;

public class Cards implements Comparable<Cards>{
    private Suit suit;
    private Value value;
    private int rank;
    private static ArrayList<Cards> cardsDeck = new ArrayList<>();

    public Cards(Suit suit, Value value, int rank) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    static List<Cards> createDeck(){
        cardsDeck.clear(); //stergem pachetul existent
        for (Suit suit : Suit.values()){
            for(Value value : Value.values()){
                int rank = value.ordinal() + 2;
                cardsDeck.add(new Cards(suit, value, rank));
            }
        }
        return cardsDeck;
    }
    @Override
    public String toString(){return value + " of " + suit + "(Rank: " + rank + ")";}
    public static void main(String[] args) {
        System.out.println(Cards.createDeck());
   }

   public int compareTo(Cards opponentCardRank) {
       return Integer.compare(this.rank, opponentCardRank.rank);
   }
}
