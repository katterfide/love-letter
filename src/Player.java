import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Player {

    public static int playerCount = 0;

    public static String[] playerNames;

    public static Map<String, ArrayList<String>> playerHands;


    /***
     * reads in the amount of players with int scanner
     * only accepts 2 - 4
     * saves the amount of players in int playerCount
     */
    public static void inputPlayerCount() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("How many players are there? (2-4)");

            while (true) {
                try {
                    playerCount = Integer.parseInt(sc.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }

            if (playerCount > 4) {
                System.out.println("That is too many players. Please limit yourself to playing with 2-4 players.");
            } else if (playerCount < 2) {
                System.out.println("That is too little players. Please limit yourself to playing with 2-4 players.");
            } else {
                break;
            }
        }
    }


    /***
     * goes through the playercount and saves the names as string to array playerNames
     */
    public static void inputPlayerNames() {
        Scanner sc = new Scanner(System.in);
        playerNames = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name #" + (i + 1) + ": ");
            playerNames[i] = sc.next();
        }
    }

    /***
     *
     * @param deck represents card deck with which PlayerHands will be generated with
     *
     * method initializes playerHands as HeshMap to store every players cards with their name as a string
     *
     *             if there is only 2 players, the 2 palyer variant is enabled which means that 3 cards
     *             have to be drawn from the deck and displayed for everyone to see.
     *
     *             goes through each players hand and draws a card each
     *
     */
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

    /***
     * leftover method to troubleshoot deck generation
     */
    public static void printALLPlayerHands() {

        System.out.println(playerHands);

    }


    //print Card instead of cardS if just one

    /***
     * Displays the cards of one player called by string
     *
     * @param playerName player call by string, whose cards are to be displayed
     *                   string because i was stupid enough to save cards in a hashmap
     */
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

    /***
     * returns the cards of one player called by int
     *
     * @param targetPlayer Index int of the Player whose cards are to be returned
     * @return returns playerHand or null if the players cards are not found/ null
     */
    public static ArrayList<String> getPlayerHand(int targetPlayer) {

        String playerName = playerNames[targetPlayer];

        return playerHands.getOrDefault(playerName, null); // Handle cases where the player's cards are not found

    }

    /***
     * Chose target player for card action with number
     * selection of players shows whether player[i] is eliminated or protected by handmaid with emojis
     *
     * forced to pick again if player is protected, eliminated, or if if target isnt available (input outside the playercount)
     *
     * @return the selected target
     */
    public static int chooseTargetPlayer() {

        Scanner sc = new Scanner(System.in);
        int targetPlayer;

        do {

            System.out.println("Choose your target player with a number: ");

            for (int i = 0; i < playerCount; i++) {
                System.out.print("Press [" + (i + 1) + "] to select player \"" + playerNames[i] + "\"");
                if (GameState.playersEliminated[i]) {
                    System.out.println("\uD83D\uDC80");
                } else if (GameState.currentPlayerIndex == i) {
                    System.out.println("⬅\uFE0F apply card to self or discard");

                } else if (GameState.playersProtected[i]) {
                    System.out.println("❤\uFE0F" + " protected by handmaid");
                } else {
                    System.out.println();
                }


            }
            targetPlayer = GameState.nextInt(sc) - 1;

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

    /***
     * Select the card to be played with a number
     *
     * selections outside of scope return "0", forced to choose until selecition != 0
     *
     * @return the selected card
     */
    public static Card.CardType selectCard() {

        Scanner sc = new Scanner(System.in);

        //get current player, check for cards


        Card.CardType selectedCard = null;
        ArrayList<String> currentPlayerHand = Player.getPlayerHand(GameState.currentPlayerIndex);

        while (selectedCard == null) {

            Player.displayPlayerHand(Player.playerNames[GameState.currentPlayerIndex]);
            System.out.println();
            System.out.println("Please pick a card to play.");


            System.out.println("Choose a card with the corresponding number:");
            System.out.println("[1] - GUARD    - guess another players card, guess right; they die          - discard on self selection");
            System.out.println("[2] - PRIEST   - show other players card                                    - discard on self selection");
            System.out.println("[3] - BARON    - compare cards, lower cardholder dies                       - discard on self selection");
            System.out.println("[4] - HANDMAID - protect self for cycle                                     - only self selectable");
            System.out.println("[5] - PRINCE   - discard somebodies hand, if contains princess; death       - self selectable (wont affect princess)");
            System.out.println("[6] - KING     - switch cards with someone (princess loss not punished)     - discard on self selection");
            System.out.println("[7] - COUNTESS - plays automatically if royals in hand (play for deception) - has no effect");
            System.out.println("    -=Princess=- cannot play/discard princess - lose her; you die");

            int choice = GameState.nextInt(sc);


            switch (choice) {

                case 1:
                    if (currentPlayerHand.contains("GUARD")) {
                        selectedCard = Card.CardType.GUARD;
                    } else {
                        System.out.println("You don't have the Guard card. Choose again.");
                        System.out.println();
                    }
                    break;

                case 2:
                    if (currentPlayerHand.contains("PRIEST")) {
                        selectedCard = Card.CardType.PRIEST;
                    } else {
                        System.out.println("You don't have the Priest card. Choose again.");
                    }
                    break;

                case 3:
                    if (currentPlayerHand.contains("BARON")) {
                        selectedCard = Card.CardType.BARON;
                    } else {
                        System.out.println("You don't have the Baron card. Choose again.");
                    }
                    break;

                case 4:
                    if (currentPlayerHand.contains("HANDMAID")) {
                        selectedCard = Card.CardType.HANDMAID;
                    } else {
                        System.out.println("You don't have the HANDMAID card. Choose again.");
                    }
                    break;

                case 5:
                    if (currentPlayerHand.contains("PRINCE")) {
                        selectedCard = Card.CardType.PRINCE;
                    } else {
                        System.out.println("You don't have the Prince card. Choose again.");
                    }
                    break;
                case 6:
                    if (currentPlayerHand.contains("KING")) {
                        selectedCard = Card.CardType.KING;
                    } else {
                        System.out.println("You don't have the KING card. Choose again.");
                    }
                    break;

                case 7:
                    if (currentPlayerHand.contains("COUNTESS")) {
                        selectedCard = Card.CardType.COUNTESS;
                    } else {
                        System.out.println("You don't have the Countess card. Choose again.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 7.");
                    break;
            }
        }

        if (selectedCard != null) {

            System.out.println("You selected: " + selectedCard);
        }

        return selectedCard;
    }

    /***
     * helper method for guard card
     * guess which card selected player might have
     *
     * @return card type that has been guessed or guess again if outside of scope of possibilities
     */
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

        int guess = 0;
        while ((guess < 1) || (8 < guess)) {
            guess = GameState.nextInt(sc);
        }

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

    /***
     * checks if any royals in currentplayer hand
     * helper method for Countess
     *
     * @return boolean, whether royals have been found on hand
     */
    static boolean hasRoyalsInHand() {
        ArrayList<String> hand = Player.getPlayerHand(GameState.currentPlayerIndex);
        if (hand.contains("KING") || hand.contains("PRINCE") || hand.contains("PRINCESS")){
            return true;
        }
        else {
            return false;
        }
    }

    /***
     *
     * @param selectedCard card to be discarded (in this case always the played card)
     * @param targetPlayer who is to remove a card, unused because its only used for card discarding after playing
     */
    static void discardCard(Card.CardType selectedCard, int targetPlayer) {
        ArrayList<String> currentPlayerHand = playerHands.get(playerNames[GameState.currentPlayerIndex]);
        currentPlayerHand.remove(selectedCard.toString()); // Remove by the card type string
        playerHands.put(playerNames[GameState.currentPlayerIndex], currentPlayerHand);
    }



}
