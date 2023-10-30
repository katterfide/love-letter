import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    /* public boolean isProtectedByHandmaid() {
         return protectedByHandmaid;
     }
     */

    Scanner sc = new Scanner(System.in);
    static int currentPlayerIndex = 0; // Define the index of the current player
    static String currentPlayer = Main.playerNames[currentPlayerIndex];

    public static int inputPlayerNumber(Scanner sc) {
        int playerCount;

        while (true) {
            System.out.println("How many players are there? (2-4)");
            playerCount = sc.nextInt();

            if (playerCount > 4) {
                System.out.println("That is too many players. Please limit yourself to playing with 2-4 players.");
            } else if (playerCount < 2) {
                System.out.println("That is too little players. Please limit yourself to playing with 2-4 players.");
            } else {
                break;
            }
        }

        return playerCount;
    }



    public void playerHands(){
        Map<String, ArrayList<String>> playerHands = new HashMap<>();
        for (int i = 0; i < Main.playerCount; i++){
            String playerName = Main.playerNames[i];
        }
    }



    /*
    public String getPlayerName(){

        return
    }
         // getplayername for cardtarget
    */
}
