import java.util.ArrayList;

public class Runner {
    /**
     * This class handles aspects of running a game.
     * Its main responsibilies:
     *      - verifying if a move is a match or not
     *      - checking if the game has been won
     */

    public Board currGame;
    public Player[] currPlayers;
    public ArrayList<Player> winners;

    public Runner(Board currBoard, Player[] playerList){
        /**
         * constructor for Runner
         */
        currGame = currBoard;
        currPlayers = playerList;
    }

    public boolean verifyMove(String firstGiven, String secondGiven, Player currPlayer) {
        String[] loc1 = firstGiven.split(" ");
        int[] arr= new int[2];
        String[] loc2 = secondGiven.split(" ");
        int[] arr2 = new int[2];
        for(int i = 0; i < loc1.length;i++){
            arr[i] = Integer.parseInt(loc1[i]);
            arr2[i] = Integer.parseInt(loc2[i]);
        }

        Card one = currGame.getCard(arr);
        Card two = currGame.getCard(arr2);
        ArrayList<int[]> newBoard = new ArrayList<>();
        newBoard.add(arr);
        newBoard.add(arr2);
        System.out.println(currGame.showPrint(newBoard));
        if(one.rank == two.rank){
            Card[] won = {one, two};
            currPlayer.addToWon(won);
            currGame.removeCard(arr);
            currGame.removeCard(arr2);
            return true;
        }
        return false;
    }

    public boolean winCheck(){
        if(currGame.doneGame()) {
            int w = 0;
            for (Player curr : currPlayers) {
                if (curr.winCount() > w) {
                    w = curr.winCount();
                }
            }
            for (Player curr : currPlayers) {
                if (currPlayers.length == w) {
                    winners.add(curr);
                }
            }
            return true;
        }

        return false;
    }

}
