import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    public static int playerCount = 0;
    public static String[] playerNames;

    public static int targetPlayer;

    private static Map<String, ArrayList<String>> playerHands;

    private String name;

    public static void inputPlayerCount() {
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
    }

    public static void inputPlayerNames() {
        Scanner sc = new Scanner(System.in);
        playerNames = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name #" + (i + 1) + ": ");
            playerNames[i] = sc.next();
        }
    }

    public static Card.CardType selectedCard;

    public static void generatePlayerHands(Deck deck) {
        playerHands = new HashMap<>();
        for (int i = 0; i < playerCount; i++) {
            String playerName = playerNames[i];
            ArrayList<String> hand = new ArrayList<>();

            for (int j = i * 2; j < i * 2 + 2; j++) {
                Card card = Deck.drawCard(j);
                if (card != null) {
                    hand.add(card.getType().toString());
                }
            }

            playerHands.put(playerName, hand);
        }
    }

    public static void printALLPlayerHands() {
        System.out.println(playerHands);
    }

    public static void displayPlayerHand(String playerName) {
        if (playerHands.containsKey(playerName)) {
            System.out.println("Cards in " + playerName + "'s hand:");
            ArrayList<String> playerHand = playerHands.get(playerName);
            for (String card : playerHand) {
                System.out.println(card);
            }
        } else {
            System.out.println("Player not found or no cards in hand.");
        }
    }

    public static ArrayList<String> getPlayerHand(int targetPlayer) {
        String playerName = playerNames[targetPlayer];
        if (playerHands.containsKey(playerName)) {
            return playerHands.get(playerName);
        } else {
            return null; // Handle cases where the player's cards are not found
        }
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int chooseTargetPlayer(){
        int targetPlayer = chooseTargetPlayer();
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your target player with a number: ");
        return sc.nextInt();
    }

    public static Card.CardType selectedCard(){
        Scanner scanner = new Scanner(System.in);

        //get current player, checkfor cards

        System.out.println("Choose a card type:");
        System.out.println("1 - GUARD");
        System.out.println("2 - PRIEST");
        System.out.println("3 - BARON");
        System.out.println("4 - HANDMAID");
        System.out.println("5 - PRINCE");
        System.out.println("6 - KING");
        System.out.println("7 - COUNTESS");

        int choice = scanner.nextInt();
        Card.CardType selectedCard = null;
        switch (choice) {
            case 1:
                selectedCard = Card.CardType.GUARD;
                break;
            case 2:
                selectedCard = Card.CardType.PRIEST;
                break;
            case 3:
                selectedCard = Card.CardType.BARON;
                break;
            case 4:
                selectedCard = Card.CardType.HANDMAID;
                break;
            case 5:
                selectedCard = Card.CardType.PRINCE;
                break;
            case 6:
                selectedCard = Card.CardType.KING;
                break;
            case 7:
                selectedCard = Card.CardType.COUNTESS;
                break;
            default:
                System.out.println("Invalid choice.");
        }

        if (selectedCard != null) {
            System.out.println("You selected: " + selectedCard);

        }
        return selectedCard;
    }



}
