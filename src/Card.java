import java.util.Scanner;

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


    public void playCard(Player currentPlayer, Player targetPlayer) {
        // Implement specific card logic here
        switch (type) {
            case GUARD:
                guardAction(GameState.currentPlayerIndex);
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


    private void guardAction(int currentPlayer) {
            // Implement logic for the Guard card
            Card.CardType guess = makeGuess();
            int targetPlayer = chooseTargetPlayer();
            Card.CardType targetCard = targetPlayer.getHandCard().getType(); // Assuming getHandCard() gets the current card in player's hand

            System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card as " + guess + ".");

            if (targetCard == guess) {
                System.out.println("Correct guess! " + targetPlayer.getName() + " is eliminated.");
                // Implement logic to eliminate the targetPlayer
                // For example: targetPlayer.eliminate();
            } else {
                System.out.println("Incorrect guess. Nothing happens.");
            }
            // int i = GameState.currentPlayerIndex + 1;
    }

/*
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



     */
    public CardType makeGuess() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Make a guess (Enter a number from 1 to 8): ");
        int guess = sc.nextInt();

        // Map the number input to the corresponding CardType (adjust according to your enumeration)
        switch (guess) {
            case 1:
                return Card.CardType.GUARD;
            case 2:
                return Card.CardType.PRIEST;
            case 3:
                return Card.CardType.BARON;
            // Add cases for other CardTypes as needed
            default:
                System.out.println("Invalid guess. Guess again.");
                return null; // Or handle the invalid guess in your specific way
        }
    }

    public int chooseTargetPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose your target player with a number: ");
        return sc.nextInt();
    }



}
