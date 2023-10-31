import java.util.Scanner;

public class GameState {
    public GameState(String[] args){

        Scanner sc = new Scanner(System.in);
        //int PlayerCount = Player.inputPlayerCount(sc);
            // System.out.print("Number of players chosen: " + PlayerCount);

    }

    static int currentPlayerIndex = 0; // Define the index of the current player

    static String currentPlayer = Player.playerNames[currentPlayerIndex];

    private void getCurrentPlayerIndex() {

        System.out.println(currentPlayerIndex);
    }


}