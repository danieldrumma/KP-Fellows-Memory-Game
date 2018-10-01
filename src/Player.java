import java.util.ArrayList;

public class Player {

    public String name;
    public ArrayList<Card[]> wonPairs;

    public Player(String playerName ) {
        /**
         * Player constructor, takes in a name and initializes a list of won cards
         */
        name = playerName;
        wonPairs = new ArrayList<>();
    }

    public void addToWon(Card[] won){
        /**
         * adds a card to a players won list
         */
        wonPairs.add(won);
    }

    public int winCount(){
        /**
         * exposes the number of won pairs to Runner
         */
        return wonPairs.size();
    }

    @Override
    public String toString() {
        /**
         * returns the player's name
         */
        return name;
    }


}
