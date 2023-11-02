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

    public static void playCard() { //SCAN IF KING OR PRINCE IN HAND FOR COUNTESS FIRST, BEFORE CHOICE
        if (Player.hasRoyalsInHand()) {
            System.out.println(GameState.currentPlayer + " plays Countess.");
            Player.selectedCard = CardType.COUNTESS;}
        else {
            CardType selectedCard = Player.selectCard();
            switch (selectedCard) {
                case GUARD:
                    guardAction(selectedCard);
                    //cannot choose yourself
                    break;

                case PRIEST:
                    priestAction(selectedCard);
                    //can choose yourself?? to discard??
                    break;

                case BARON:
                    //baronAction();
                    System.out.println("to be implemented");
                    break;

                case HANDMAID:
                    handmaidAction(selectedCard);
                    break;

                case PRINCE:
                    princeAction(selectedCard);
                    break;

                case KING:
                    kingAction(selectedCard);
                    break;

                case COUNTESS:
                    countessAction(selectedCard);
                    break;

                default:
                    System.out.println("No specific action for this card.");
                    break;
            }
        }
    }

    ///////////////NEED TO DISCARD CARDS AND END TURN ALSO
    ///Handmaid protection
    //eliminated players need to be exempt from targeting
    //princess also needs to be implemented
    private static void guardAction(CardType selectedCard) {
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
                GameState.eliminatePlayer(targetPlayer, true);

            } else {
                System.out.println("Incorrect guess. Nothing happens.");
            }
        } else {
            System.out.println("Player " + Player.playerNames[targetPlayer] + " not found or has no cards in hand.");
        }
        Player.discardCard(selectedCard);
    }

    private static void priestAction(CardType selectedCard) {
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
        Player.discardCard(selectedCard);
    }

    /*
    private void baronAction() { //Baron needs to be left out from comparison
    Player.discardCard(selectedCard);

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

    private static void handmaidAction(CardType selectedCard) {
        System.out.println(GameState.currentPlayer + " plays Handmaid.");
        GameState.setProtection(GameState.currentPlayerIndex, true);
        System.out.println(GameState.currentPlayer + " is protected until the next turn.");
        Player.discardCard(selectedCard);
    }

    private static void princeAction(CardType selectedCard) {
        //includes yourself, draws card that was removed at the start of the round if no cards??
        //doesnt get red of princess in your own hand

        int targetPlayer = Player.chooseTargetPlayer();
        ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);


        if (targetCards.size() > 0) {

            targetCards.clear();
            Card newCard = Deck.drawCard();


            if (newCard != null) {

                if (targetCards.contains("Princess")){
                    System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand which contained a princess and was eliminated.");
                    GameState.eliminatePlayer(targetPlayer, true);
                }
                else {
                    targetCards.add(newCard.getType().toString());
                    System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand and draws a new card.");
                }
            }

            else {
                System.out.println("No more cards in the deck to draw.");
            }
        }

        else {
            System.out.println("Player " + Player.playerNames[targetPlayer] + " has no cards in hand.");
        }

        Player.discardCard(selectedCard);
    }

    private static void kingAction(CardType selectedCard) {

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

        Player.discardCard(selectedCard);
    }

    private static void countessAction(CardType selectedCard){

        System.out.println(GameState.currentPlayer + " plays Countess.");
        Player.discardCard(selectedCard);
    }



}










