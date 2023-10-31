import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    public static int playerCount = 0;
    public static String[] playerNames;

    private static Map<String, ArrayList<String>> playerHands;


    public static int inputPlayerCount() {
        Scanner sc = new Scanner(System.in);
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

    public static void inputPlayerNames() {
        Scanner sc = new Scanner(System.in);
        playerNames = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name #" + (i + 1) + ": ");
            playerNames[i] = sc.next();
        }
    }


    public static void generatePlayerHands(Deck deck) {
        playerHands = new HashMap<>();
        int totalCards = playerCount * 2; // Total cards required for all players

        for (int i = 0; i < playerCount; i++) {
            String playerName = playerNames[i];
            ArrayList<String> hand = new ArrayList<>();

            for (int j = i * 2; j < i * 2 + 2; j++) {
                Card card = deck.drawCard(j);
                if (card != null) {
                    hand.add(card.getType().toString());
                }
            }

            playerHands.put(playerName, hand);
        }
    }


    public static void getPlayerHands() {
        System.out.println(playerHands);

        // Method to retrieve a player's hand by name
    }
}
