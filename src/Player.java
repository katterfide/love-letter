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
