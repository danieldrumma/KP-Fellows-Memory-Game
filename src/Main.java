import java.util.Scanner;

public class Main {
    public static final String WELC_BANNER = "*******************************\n"+
                                             "*       Welcome to Memory!    *\n"+
                                             "*                             *\n"+
                                             "* A game of chance and skill! *\n"+
                                             "*******************************\n";
    public static final String GAME_INFO = "Select 2 random cards by entering the two numbers in the parentheses when prompted to do so.\n" +
            "The goal is to match up cards with equal rank, such as a King and a King or a 5 and a 5!";
    public static final String key = "Heart = H | Club = C | Diamond = D | Spades = S | Joker = J | Queen = Q | King = K | Ace = A";


    public static boolean numCheck(String str){
        /**
         * ensure that first character entered for a location is a number in the range of 0 - 3
         */
        String[] loc1 = str.split(" ");

        int num = Integer.parseInt(loc1[0]);
        if(num > 3){
            return false;
        }
        return true;
    }

    public static boolean stringCheck(String str) {
        /**
         * Contains several checks to ensure that a location entered is valid
         */
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;
        boolean retVal = false;
        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i))  ) {
                if(i == 1 || i == 2){
                    if( str.charAt(i) != ' '){
                        return false;
                    }
                    else{
                        retVal = true;
                    }
                } else {
                    retVal = false;
                }
            }
        }
        if( !numCheck(str)){
            retVal = false;
        }

        return retVal;
    }



    public static void main(String[] args) {
        /**
         * Handles several aspects of running a live game
         *  -create players
         *  -take in new moves
         *  -show board
         *  -track winState with help of Runner class
         */
        System.out.println(WELC_BANNER);
        //intialize game
        Scanner readin = new Scanner(System.in);

        System.out.println("Enter the number of players (1-4): ");
        int plNum = readin.nextInt();
        while(plNum > 4 || plNum < 1){
            System.out.println("Enter the number of players (1-4): ");
            plNum = readin.nextInt();
        }
        Player[] players = new Player[plNum];
        for(int i= 1; i <= (plNum);i++) {
            System.out.println("Enter name for Player " + Integer.toString(i) + ": ");
            String player = readin.next();
            Player aPlayer = new Player(player);
            players[i - 1] = aPlayer;
        }
        Board currBoard = new Board(1);
        Runner currGame = new Runner(currBoard, players);

        System.out.println(currBoard);
        System.out.println(GAME_INFO);



        boolean win;
        boolean activeGame = true;
        while(activeGame) {
            System.out.println("\n");
            readin.nextLine();
            for(Player p: currGame.currPlayers) {
                win = true;
                while(win) {
                    System.out.println("It's your turn " + p.name + "!");
                    System.out.println("Enter a location with this format, having one space between the two digits \"5 6\". Then press enter. ");
                    System.out.println("Enter first card:");
                    String carda = readin.nextLine();
                    while(carda.length() > 4 || carda.length() < 1 || !stringCheck(carda)){
                        System.out.println("Your input was incorrect. Please try again!\n");
                        System.out.println("Enter first card:");
                        carda = readin.nextLine();
                    }
                    System.out.println("Enter second card:");
                    String cardb = readin.nextLine();
                    while(cardb.length() > 4 || cardb.length() < 3 || !stringCheck(cardb)){
                        System.out.println("Your input was incorrect. Please try again!\n");
                        System.out.println("Enter second card:");
                        cardb = readin.nextLine();
                    }
                    boolean ans = currGame.verifyMove(carda, cardb, p);
                    System.out.println(key);
                    if (ans) {
                        System.out.println("You've got a match! You get another turn!"+ " You have " + Integer.toString(p.winCount()) +" won pairs! \n");
                        System.out.println(currBoard);
                        System.out.println(key);
                        boolean check = currGame.winCheck();
                        if(check == true){
                            if(currGame.winners.size() > 1){
                                String f_string = "";
                                for(Player curr : currGame.winners){
                                    f_string += curr.name;
                                    f_string+= " ";
                                }
                                System.out.println("The winners are" + f_string + "!");
                                activeGame = false;
                            } else {
                                System.out.println("You completed the game! Great work!");
                                activeGame = false;
                            }
                        }
                        System.out.println("Press enter to continue.");
                    } else {
                        System.out.println("That wasn't a match! Remember where these cards are, though!\n");
                        System.out.println(GAME_INFO);
                        System.out.println("Press enter to continue.");
                        win = false;
                    }
                }
            }
        }

        System.out.println("Thanks for playing!");
        return;

    }
}
