import java.util.ArrayList;

public class Card {
    private CardType type;

    enum CardType {
        GUARD, PRIEST, BARON, HANDMAID, PRINCE, KING, COUNTESS, PRINCESS
    }

    public Card(CardType type) {

        this.type = type;
    }

    public CardType getType() {

        return type;
    }


    public static void playCard() {
        switch (Player.selectCard()) {
            case GUARD:
                guardAction();
                //cannot choose yourself
                break;
                /*
            case PRIEST:
                priestAction(targetPlayer);
                break;
            case BARON:
                baronAction(currentPlayer, targetPlayer);
                break;
            case HANDMAID:
                handmaidAction(currentPlayer, targetPlayer);
                break;
            case PRINCE:
                princeAction(currentPlayer, targetPlayer);
                break;
            case KING:
                kingAction(currentPlayer, targetPlayer);
                break;
            case COUNTESS:
                countessAction(currentPlayer, targetPlayer);
                break;
            */

            default:
                System.out.println("No specific action for this card.");
                break;
        }
    }



    private static void guardAction() {
        int targetPlayer = Player.chooseTargetPlayer();

        System.out.println("Which card do you think player #" + targetPlayer + " has?");
        CardType guess = Player.makeGuess();

        ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);
        //do i have those cards
        //currentplayerindex
        //guess cant exceed current players

        System.out.println(GameState.currentPlayer + " plays Guard and guesses Player " + targetPlayer + "'s hand card as " + guess + ".");

        if (targetCards != null) {
            // Here, targetCards holds the cards of the targetPlayer
            // Perform the comparison or other actions as required
            if (targetCards.contains(guess.toString())) {
                System.out.println("Correct guess! Player " + targetPlayer + " is eliminated.");
                // Implement logic to eliminate the targetPlayer
                // For example: targetPlayer.eliminate();
            } else {
                System.out.println("Incorrect guess. Nothing happens.");
            }
        } else {
            System.out.println("Player " + targetPlayer + " not found or has no cards in hand.");
        }
    }


    private void priestAction(Player targetPlayer) {
        // Implement logic for the Priest card
        // Example: Look at another player's hand.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println("Priest looks at " + targetPlayer.getName() + "'s hand.");
    }

    private void baronAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Baron card
        // Example: Compare hands; lower hand is out.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Baron and compares hands with " + targetPlayer.getName() + ".");
    }

    private void handmaidAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Guard card
        // Example: Guess a player's hand card.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void princeAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Guard card
        // Example: Guess a player's hand card.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void kingAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Guard card
        // Example: Guess a player's hand card.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void countessAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Guard card
        // Example: Guess a player's hand card.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }







}
