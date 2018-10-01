public class Card {

    public char rank;
    public char suite;
    public String location;

    public Card(char truRank, char truSuite, String truLocation){
        /*
        constructor for card
         */
        rank = truRank;
        suite = truSuite;
        location = truLocation;
    }



    @Override
    public String toString() {
        /*
        build string to print card, signals if card has been matched
         */
        if(location.equals("Gone!")){
            return ("|*(Gone!)*|");
        }
        return ("|*(" + location+")*|");
    }
}
