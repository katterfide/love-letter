import java.util.ArrayList;
import java.util.Objects;

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

            case PRIEST:
                priestAction();
                //can choose yourself?? to discard??
                break;

            case BARON:
                //baronAction();
                System.out.println("to be implemented");
                break;

            case HANDMAID:
                handmaidAction();
                break;

            case PRINCE:
                princeAction();
                break;

            case KING:
                kingAction();
                break;
                 /*
            case COUNTESS:
                countessAction(currentPlayer, targetPlayer);
                break;
            */

            default:
                System.out.println("No specific action for this card.");
                break;
        }
    }


    ///////////////NEED TO DISCARD CARDS AND END TURN ALSO
    ///Handmaid protection
    //eliminated players need to be exempt from targeting
    //princess also needs to be implemented
    private static void guardAction() {
        int targetPlayer = Player.chooseTargetPlayer();

        System.out.println("Which card do you think player " + "\"" + Player.playerNames[targetPlayer] + "\"" + " has?");
        CardType guess = Player.makeGuess();

        ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);
        //do i have those cards
        //currentplayerindex
        //guess cant exceed current players
        System.out.println(GameState.currentPlayer + " plays Guard and guesses Player " + Player.playerNames[targetPlayer] + "'s hand card as " + guess + ".");

        if (targetCards != null) {

            if (targetCards.contains(guess.toString())) {
                System.out.println("Correct guess! Player " + Player.playerNames[targetPlayer] + " is eliminated.");

            } else {
                System.out.println("Incorrect guess. Nothing happens.");
            }
        } else {
            System.out.println("Player " + Player.playerNames[targetPlayer] + " not found or has no cards in hand.");
        }
    }

    private static void priestAction() {
        System.out.println("Which player do you want to compare hands with??");
        int targetPlayer = Player.chooseTargetPlayer();


        if (Player.getPlayerHand(targetPlayer) != null) {
            System.out.println("Player " + "\"" + Player.playerNames[targetPlayer] + "\"" + " has these cards in their hand at the moment: ");
            for (String card : Objects.requireNonNull(Player.getPlayerHand(targetPlayer))) {
                System.out.println(card);
            }
        } else {
            System.out.println("No hand found for player: " + Player.playerNames[targetPlayer]);
        }
        //cant be eliminated player
    }

    /*
    private void baronAction() { //Baron needs to be left out from comparison

        // Compare the cards and eliminate the player with the lower-valued card
        if (currentPlayerCard.getValue() > targetPlayerCard.getValue()) {
            System.out.println(targetPlayer.getName() + " is eliminated.");
            // Implement logic to eliminate the targetPlayer, for example: targetPlayer.eliminate();
        } else if (currentPlayerCard.getValue() < targetPlayerCard.getValue()) {
            System.out.println(currentPlayer.getName() + " is eliminated.");
        } else {
            System.out.println("No player is eliminated. Both players have the same card value.");
        }
    }

     */ //baron

    private static void handmaidAction() {
        System.out.println(GameState.currentPlayer + " plays Handmaid.");
        GameState.setProtection(GameState.currentPlayerIndex, true);
        System.out.println(GameState.currentPlayer + " is protected until the next turn.");
    }

    private static void princeAction() {
        //includes yourself, draws card that was removed at the start of the round if no cards??
        //doesnt get red of princess!!

        int targetPlayer = Player.chooseTargetPlayer();
        ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);


        if (targetCards.size() > 0) {

            targetCards.clear();
            Card newCard = Deck.drawCard();


            if (newCard != null) {
                targetCards.add(newCard.getType().toString());
                System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand and draws a new card.");
            } else {
                System.out.println("No more cards in the deck to draw.");
            }
        } else {
            System.out.println("Player " + Player.playerNames[targetPlayer] + " has no cards in hand.");


        }
    }

    private static void kingAction() {

        int targetPlayer = Player.chooseTargetPlayer();


        System.out.println(GameState.currentPlayer + " plays King and exchanges hands with " + Player.playerNames[targetPlayer]);

        ArrayList<String> temp = Player.getPlayerHand(GameState.currentPlayerIndex);
        ArrayList<String> currentPlayerHand = Player.getPlayerHand(GameState.currentPlayerIndex);
        ArrayList<String> targetPlayerHand = Player.getPlayerHand(targetPlayer);

        currentPlayerHand.clear();
        currentPlayerHand.addAll(targetPlayerHand);
        targetPlayerHand.clear();
        targetPlayerHand.addAll(temp);

        System.out.println(GameState.currentPlayer + " plays Guard and guesses " + Player.playerNames[targetPlayer] + "'s hand card.");

    }
}

        /*

        private void countessAction (Player currentPlayer, Player targetPlayer){

            System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
        }
    }

 */




