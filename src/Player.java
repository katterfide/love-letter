import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {
    public static int playerCount = 0;

    public static String[] playerNames;

    public static Map<String, ArrayList<String>> playerHands;

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
        int numberOfPlayers = Player.playerCount;

        if (numberOfPlayers == 2) {

            System.out.println("You are playing the 2 player variant.");
            System.out.println("3 cards will be removed from the top of the deck.");
            System.out.println("The following 3 cards will not be in your game:");


            Card topCard1 = Deck.drawCard();
            System.out.println(topCard1.getType().toString());

            Card topCard2 = Deck.drawCard();
            System.out.println(topCard2.getType().toString());

            Card topCard3 = Deck.drawCard();
            System.out.println(topCard3.getType().toString());

            System.out.println();

        }



        for (int i = 0; i < playerCount; i++) {

            String playerName = playerNames[i];
            ArrayList<String> hand = new ArrayList<>();

            for (int j = i; j < i + 1; j++) {

                Card card = Deck.drawCard();
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



    //print Card instead of cardS if just one
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
    } // for \showCards

    public static ArrayList<String> getPlayerHand(int targetPlayer) {

        String playerName = playerNames[targetPlayer];

        return playerHands.getOrDefault(playerName, null); // Handle cases where the player's cards are not found

    }

    public static int chooseTargetPlayer() {

        Scanner sc = new Scanner(System.in);
        int targetPlayer;

        do {

            System.out.println("Choose your target player with a number: ");

            for (int i = 0; i < playerCount; i++) {
                System.out.println("Press [" + (i + 1) + "] to select player \"" + playerNames[i] + "\"");

            }
            targetPlayer = sc.nextInt() - 1;

            if (targetPlayer < 0 || targetPlayer >= playerCount) {

                System.out.println("Invalid player choice. Please try again.");
                continue;
            }

            if (GameState.playersEliminated[targetPlayer]) {

                System.out.println("This player is already eliminated and cannot be targeted.");

            } else if (GameState.playersProtected[targetPlayer]) {

                System.out.println("Player is protected by the handmaid until the end of this round and cannot be targeted.");
                System.out.println("Please guess again.");

            }
        } while (targetPlayer < 0 || targetPlayer >= playerCount || GameState.playersEliminated[targetPlayer] || GameState.playersProtected[targetPlayer]);

        return targetPlayer;
    }

    public static Card.CardType selectCard(){

        Scanner scanner = new Scanner(System.in);

        //get current player, check for cards

        System.out.println("Choose a card with the corresponding number:");
        System.out.println("[1] - GUARD");
        System.out.println("[2] - PRIEST");
        System.out.println("[3] - BARON");
        System.out.println("[4] - HANDMAID");
        System.out.println("[5] - PRINCE");
        System.out.println("[6] - KING");
        System.out.println("[7] - COUNTESS");
        System.out.println("=Cannot discard Princess=");


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
                System.out.println("Invalid choice, guess again.");
        }

        if (selectedCard != null) {

            System.out.println("You selected: " + selectedCard);



        } else if (selectedCard == null){


        }

        return selectedCard;
    }

    public static Card.CardType makeGuess() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Make a guess, press: ");
        System.out.println("[1] to guess Guard");
        System.out.println("[2] to guess Priest");
        System.out.println("[3] to guess Baron");
        System.out.println("[4] to guess Handmaid");
        System.out.println("[5] to guess Prince");
        System.out.println("[6] to guess King");
        System.out.println("[7] to guess Countess");
        System.out.println("[8] to guess Princess");

        int guess = sc.nextInt();

        switch (guess) {
            case 1:
                return Card.CardType.GUARD;
            case 2:
                return Card.CardType.PRIEST;
            case 3:
                return Card.CardType.BARON;
            case 4:
                return Card.CardType.HANDMAID;
            case 5:
                return Card.CardType.PRINCE;
            case 6:
                return Card.CardType.KING;
            case 7:
                return Card.CardType.COUNTESS;
            case 8:
                return Card.CardType.PRINCESS;
            default:
                System.out.println("Invalid guess. Guess again.");
                return null;
               // selectedCard = Player.selectCard();
        }
    }

    static boolean hasRoyalsInHand() {
        ArrayList<String> hand = Player.getPlayerHand(GameState.currentPlayerIndex);
        return hand.contains("King") || hand.contains("Prince") || hand.contains("Princess");
    }

    static void discardCard(Card.CardType selectedCard, int targetPlayer) {
        ArrayList<String> currentPlayerHand = playerHands.get(playerNames[GameState.currentPlayerIndex]);
        currentPlayerHand.remove(selectedCard.toString()); // Remove by the card type string
        playerHands.put(playerNames[GameState.currentPlayerIndex], currentPlayerHand);
    }



}
