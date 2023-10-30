public class Card {
    private static CardType type;

    enum CardType {
        GUARD, PRIEST, BARON, HANDMAID, PRINCE, KING, COUNTESS, PRINCESS
    }

    public Card(CardType type) {
        this.type = type;
    }

    public CardType getType() {
        return type;
    }

    public static String getCardType() {
        return type.toString();
    }


/*
    public void playCard(Player currentPlayer, Player targetPlayer) {
        // Implement specific card logic here
        switch (type) {
            case 1:
                guardAction(currentPlayer, targetPlayer);
                break;
            case 2:
                priestAction(targetPlayer);
                break;
            case 3:
                baronAction(currentPlayer, targetPlayer);
                break;
            // Add cases for other cards...
            default:
                // Default action if no specific logic is defined for the card
                System.out.println("No specific action for this card.");
                break;
        }
    }



    private void guardAction(Player currentPlayer, Player targetPlayer) {
        // Implement logic for the Guard card
        // Example: Guess a player's hand card.
        // This is just a placeholder; you can implement the actual logic as per the game's rules.
        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
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

*/


}
