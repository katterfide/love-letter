import java.lang.reflect.Array;
import java.util.Scanner;

public class GameState {

    static int currentPlayerIndex = 0; // Define the index of the current player

    static String currentPlayer = Player.playerNames[currentPlayerIndex];

    static boolean[] playersProtected;
    public static boolean[] playersEliminated;


    public static void initializePlayersProtection() {
        int numberOfPlayers = Player.playerCount;
        playersProtected = new boolean[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersProtected[i] = false;
        }
    }

    public static void setProtection(int playerIndex, boolean isProtected) {
            playersProtected[playerIndex] = isProtected;
        //lose protection after new round
        //deck empty Win condition
    }


    public static void initializeEliminationArray() {
        int numberOfPlayers = Player.playerCount;
        playersEliminated = new boolean[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playersEliminated[i] = false;
        }
    }


    public static void eliminatePlayer(int Index, boolean isEliminated){

        playersEliminated[Index] = isEliminated;

    }


    public static void endTurn(){
        int startingIndex = currentPlayerIndex;

        System.out.print(currentPlayer + "'s turn ended");

        currentPlayerIndex++;
        while (playersEliminated[currentPlayerIndex]){
            currentPlayerIndex++;
            if (currentPlayerIndex >= Player.playerCount){
                currentPlayerIndex = 0;
            } else if (currentPlayerIndex == startingIndex) {
                //WINCONDITION
                break;
            } else {
                currentPlayer = Player.playerNames[currentPlayerIndex]; //updating currentPlayer String
                System.out.println(", it is now " + currentPlayer + "'s turn.");
            }
        }
    }





//tokens

}

