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
        //return ("S(" + suite + ")" + " R(" + rank + ")" +  " Loc=(" + location+")");
        if(location.equals("Matched!")){
            return ("|*(Gone!)*|");
        }
        return ("|*(" + location+")*|");
    }
}
