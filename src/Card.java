import java.util.ArrayList;
import java.util.Objects;

public class Card {
    private CardType type;

    enum CardType {
        GUARD, PRIEST, BARON, HANDMAID, PRINCE, KING, COUNTESS, PRINCESS
    }

    private static int cardValue(String card) {
        switch (card) {
            case "GUARD":
                return 1;
            case "PRIEST":
                return 2;
            case "Baron":
                return 3;
            case "Handmaid":
                return 4;
            case "Prince":
                return 5;
            case "King":
                return 6;
            case "Countess":
                return 7;
            case "Princess":
                return 8;
            default:
                return 0;
        }
    }

    public Card(CardType type) {

        this.type = type;
    }

    public CardType getType() {

        return type;
    }

    public static void playCard() {

        //drawCard with turn
        Card newCard = Deck.drawCard();
        Player.getPlayerHand(GameState.currentPlayerIndex).add(newCard.getType().toString());

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
                    baronAction(selectedCard);
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

    ///Handmaid protection
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
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
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
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }


    private static void baronAction(CardType selectedCard) {

        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
        int targetPlayer = Player.chooseTargetPlayer();
        int currentPlayerIndex = GameState.currentPlayerIndex;

        ArrayList<String> targetPlayerCards = Player.getPlayerHand(targetPlayer);
        ArrayList<String> currentPlayerCard = Player.getPlayerHand(currentPlayerIndex);

        // Assuming you've defined a method cardValue as shown above
        int currentPlayerValue = cardValue(currentPlayerCard.get(0));
        int targetPlayerValue = cardValue(targetPlayerCards.get(0));

        System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " plays Baron.");
        System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " has " + currentPlayerCard);
        System.out.println(Player.playerNames[targetPlayer] + " has " + targetPlayerCards);

        if (currentPlayerValue > targetPlayerValue) {
            System.out.println(Player.playerNames[targetPlayer] + " is eliminated.");
            GameState.eliminatePlayer(targetPlayer, true);

        } else if (currentPlayerValue < targetPlayerValue) {
            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " is eliminated.");
            GameState.eliminatePlayer(currentPlayerIndex, true);

        } else {
            System.out.println("No player is eliminated. Both players have the same card value.");
        }
    }




    private static void handmaidAction(CardType selectedCard) {
        System.out.println(GameState.currentPlayer + " plays Handmaid.");
        GameState.setProtection(GameState.currentPlayerIndex, true);
        System.out.println(GameState.currentPlayer + " is protected until the next turn.");
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
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

        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
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

        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }

    private static void countessAction(CardType selectedCard){
        System.out.println(GameState.currentPlayer + " plays Countess.");
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }



}










