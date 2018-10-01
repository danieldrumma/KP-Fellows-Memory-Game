import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private Card board[][] = new Card[4][];
    private int size;
    private int winCheck;

    public Board(int sizze){
        /**
         * takes in a size of board
         * initializes board and counter for win checking
         * shuffles board
         */
        size = sizze;
        if (size == 1){
            buildBoard();
            winCheck = 52;
            shuffle(board);
        } else {

        }
    }

    public void buildBoard(){
        /**
         * Creates each set of suites for board
         */
        for(int i = 0; i <= 3; i++) {
            board[i] = new Card[13];
            if (i == 0) {
                buildSuite('C', board[i]);
            }

            if (i == 1) {
                buildSuite('D', board[i]);
            }

            if (i == 2) {
                buildSuite('H', board[i]);
            }

            if (i == 3) {
                buildSuite('S', board[i]);
            }
        }

    }

    public void buildSuite(char suite, Card[] curr){
        /**
         * Creates card objects for a single suit, takes in current suite and Card array with suite in order
         */

        Card ace = new Card('A', suite,"");
        curr[0] = ace;

        for(int i = 1; i <= 10; i++){
            char currCard = (char) (i+1);
            final int RADIX = 10;
            char ch = Character.forDigit(i+1, RADIX);
            if(i == 10){
                ch = 't';
            }
            Card n = new Card(ch, suite,"");
            curr[i] = n;
        }

        Card jack = new Card('J', suite,"");
        curr[10] = jack;

        Card queen = new Card('Q', suite,"");
        curr[11] = queen;

        Card king = new Card('K', suite,"");
        curr[12] = king;
    }

    public void shuffle(Card[][] board){
        /**
         * shuffles board
         */
        ArrayList<Card> shuffleDeck = new ArrayList<>();

        for(int i = 0; i< board.length;i++){
            for(int j = 0; j < board[i].length;j++){
                shuffleDeck.add(board[i][j]);
            }
        }
        Collections.shuffle(shuffleDeck);
        int rm = 51;
        for(int i = 0; i< board.length;i++){
            for(int j = 0; j < board[i].length;j++){
                board[i][j] = shuffleDeck.remove(rm);
                String loc = Integer.toString(i) + " " + Integer.toString(j);
                board[i][j].location = loc;
                rm--;
            }
        }
    }

    public Card getCard(int[] arr){
        /**
         * returns card based upon location, which is provided as an integer array
         */
        int loc1 = arr[0];
        int loc2 = arr[1];

        Card toReturn = board[loc1][loc2];

        return toReturn;
    }


    public void removeCard(int[] arr){
        /**
         * updates wincheck value
         * changes attirbutes of card to signal that it has been matched
         */
        int loc1 = arr[0];
        int loc2 = arr[1];
        String loc = "Gone!";
        Card nullCard = new Card('n','n', loc);
        board[loc1][loc2] = nullCard;
        winCheck--;
    }


    public String toString(){
        /**
         * prints out board in 4x13 layout
         */
        String boardPrint = "";
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                boardPrint = boardPrint + " " + board[i][j].toString();
            }
            boardPrint+="\n";
        }
        return boardPrint;
    }


    public String showPrint(ArrayList<int[]> arr){
        /**
         * prints out board and shows cards that have been selected by a player
         * takes in an ArrayList of two int arrays, each representing a card location
         */
        String boardPrint = "";
        int loc1 = arr.get(0)[0];
        int loc2 = arr.get(0)[1];
        int loc3 = arr.get(1)[0];
        int loc4 = arr.get(1)[1];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(i == loc1 && j == loc2){
                    boardPrint = boardPrint + " " + "|~(" + board[i][j].suite + " "+ board[i][j].rank +")~|";
                }
                else
                if(i == loc3 && j == loc4){
                    boardPrint = boardPrint + " " + "|~(" + board[i][j].suite + " "+ board[i][j].rank +")~|";
                } else {
                    boardPrint = boardPrint + " " + board[i][j].toString();
                }
            }
            boardPrint+="\n";
        }
        return boardPrint;
    }

    public boolean doneGame(){
        /**
         * exposes winCheck to outside of class
         * check if the game is complete
         */
        if(winCheck == 0){
            return true;
        }
        return false;
    }

}
