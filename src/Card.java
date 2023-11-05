import java.util.ArrayList;
import java.util.Objects;

public class Card {
    private CardType type;

    enum CardType {
        GUARD, PRIEST, BARON, HANDMAID, PRINCE, KING, COUNTESS, PRINCESS
    }

    /***
     *  assigns each card string a value
     * @param card string of card to be evaluated
     * @return int value of card
     */
    static int cardValue(String card) {
        switch (card) {
            case "GUARD":
                return 1;
            case "PRIEST":
                return 2;
            case "BARON":
                return 3;
            case "HANDMAID":
                return 4;
            case "PRINCE":
                return 5;
            case "KING":
                return 6;
            case "COUNTESS":
                return 7;
            case "PRINCESS":
                return 8;
            default:
                return 0;
        }
    }

    /***
     *constructs new card type with the specified CardType
     *
     * @param type type of card like King or priest etc.
     */
    public Card(CardType type) {

        this.type = type;
    }

    /***
     * get Type of card
     * @return CardType of card
     */
    public CardType getType() {
        return type;
    }

    /***
     * Plays a card from the current players hand and performs the corresponding game action
     *
     * resets Handmaid protection for the current player. (since if they get to play a card again and are protected, a rund must have passed.
     * draws a card from the deck and adds it to the current players hand
     * if player has royal cards in their hand and has a Countess, Countess is selected and played automatically
     */
    public static void playCard() {

        //set Handmaid protection to false, because if theyre playing a card a round mustve passed
        GameState.playersProtected[GameState.currentPlayerIndex] = false;

        //draw card
        Card newCard = Deck.drawCard();
        Player.getPlayerHand(GameState.currentPlayerIndex).add(newCard.getType().toString());

        //if royals in hand, COUNTESS is picked and played automatically
        ArrayList<String> hand = Player.getPlayerHand(GameState.currentPlayerIndex);

        if (Player.hasRoyalsInHand() && hand.contains("COUNTESS")) {
            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " was forced to play Countess, as royals were in their hand.");


            Player.displayPlayerHand(Player.playerNames[GameState.currentPlayerIndex]);


            countessAction(CardType.COUNTESS);
        }

        else {
            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " it is your turn!");
            System.out.println();


            CardType selectedCard = Player.selectCard(); //reinit because had issue selectedCard staying null


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

    /**
     * performs guard card action, allowing the current player to guess another players hand card
     *
     * allows the current player to make a guess about the hand card of another player and, if the guess is correct, eliminate that player from the game
     *
     * @param selectedCard cardType representing the "Guard" card played by the current player
     */
    private static void guardAction(CardType selectedCard) {

        int targetPlayer = Player.chooseTargetPlayer();

        if (GameState.currentPlayerIndex != targetPlayer){

            System.out.println("Which card do you think player " + "\"" + Player.playerNames[targetPlayer] + "\"" + " has?");
            CardType guess = Player.makeGuess();

            ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);
            //do i have those cards
            //currentplayerindex
            //guess cant exceed current players
            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " plays Guard and guesses Player " + Player.playerNames[targetPlayer] + "'s hand card as " + guess + ".");

            if (targetCards != null) {

                if (targetCards.contains(guess.toString())) {
                    System.out.println("Correct guess! Player " + Player.playerNames[targetPlayer] + " is eliminated. \uD83D\uDC80");
                    GameState.eliminatePlayer(targetPlayer, true);

                } else {
                    System.out.println("Incorrect guess. Nothing happens.");
                }
            } else {
                System.out.println("Player " + Player.playerNames[targetPlayer] + " not found or has no cards in hand.");
            }
        } else if (GameState.currentPlayerIndex == targetPlayer) {
            System.out.println("Discarding card without applying effect.");

        }


        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }

    /***
     * performs priest card action, allowing the current player to see another player's hand cards.
     *
     *  allows the current player to choose a target player and view the card of that player
     *  @param selectedCard cardType representing the card played by the current player
     */
    private static void priestAction(CardType selectedCard) {
        System.out.println("Which players card do you want to see?");
        int targetPlayer = Player.chooseTargetPlayer();

        if (GameState.currentPlayerIndex != targetPlayer){

            if (Player.getPlayerHand(targetPlayer) != null) {
                System.out.println("Player " + "\"" + Player.playerNames[targetPlayer] + "\"" + " has these cards in their hand at the moment: ");
                for (String card : Objects.requireNonNull(Player.getPlayerHand(targetPlayer))) {
                    System.out.println(card);
                }
            } else {
                System.out.println("No hand found for player: " + Player.playerNames[targetPlayer]);
            }

        } else if (GameState.currentPlayerIndex == targetPlayer) {
            System.out.println("Discarding card without applying effect.");

        }
        //cant be eliminated player
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }

    /**
     * performs baron card action
     * allowing the current player to compare their cards value with another players cards value
     * the one with the lower card gets eliminated
     * if both are the same nothign happens
     *
     * baron is discarded at start of action so he is not compared with rest of cards
     * @param selectedCard cardType representing the card played by the current player
     */
    private static void baronAction(CardType selectedCard) {

        Player.discardCard(selectedCard, GameState.currentPlayerIndex);

        int targetPlayer = Player.chooseTargetPlayer();
        int currentPlayerIndex = GameState.currentPlayerIndex;

        if (GameState.currentPlayerIndex != targetPlayer) {

            ArrayList<String> targetPlayerCards = Player.getPlayerHand(targetPlayer);
            ArrayList<String> currentPlayerCard = Player.getPlayerHand(currentPlayerIndex);

            int currentPlayerValue = cardValue(currentPlayerCard.get(0));
            if (currentPlayerValue == 0){
                currentPlayerValue = cardValue(currentPlayerCard.get(1));
            }

            int targetPlayerValue = cardValue(targetPlayerCards.get(0));
            if (targetPlayerValue == 0){
                targetPlayerValue = cardValue(targetPlayerCards.get(1));
            }

            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " plays Baron.");
            System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " has " + currentPlayerCard);
            System.out.println(Player.playerNames[targetPlayer] + " has " + targetPlayerCards);

            if (currentPlayerValue > targetPlayerValue) {
                System.out.println(Player.playerNames[targetPlayer] + " is eliminated. \uD83D\uDC80");
                GameState.eliminatePlayer(targetPlayer, true);

            } else if (currentPlayerValue < targetPlayerValue) {
                System.out.println(Player.playerNames[GameState.currentPlayerIndex] + " is eliminated. \uD83D\uDC80");
                GameState.eliminatePlayer(currentPlayerIndex, true);

            } else {
                System.out.println("No player is eliminated. Both players have the same card value.");
            }
        }
        else if (GameState.currentPlayerIndex == targetPlayer) {
            System.out.println("Discarding card without applying effect.");
        }
    }

    /***
     * performs the hamdmaid card action
     * sets the protectionarray to true for the current playerindex
     *
     * @param selectedCard cardType representing the card played by the current player
     */
    private static void handmaidAction(CardType selectedCard) {
        System.out.println(GameState.currentPlayer + " plays Handmaid.");
        GameState.setProtection(GameState.currentPlayerIndex, true);
        System.out.println(GameState.currentPlayer + " is protected until the next turn.");
        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }

    /**
     * Performs the prince card action
     * allowing the current player to target another player
     * any selected player discards their hand and draws a new card
     *
     * if discarded hand contained princess, that player is eliminated except if it was the person that played the prince
     *
     * @param selectedCard The CardType representing the prince card played by the current player.
     */
    private static void princeAction(CardType selectedCard) {
        //includes yourself, draws card that was removed at the start of the round if no cards??
        //doesnt get red of princess in your own hand

        int targetPlayer = Player.chooseTargetPlayer();
        ArrayList<String> targetCards = Player.getPlayerHand(targetPlayer);

        if (targetPlayer != GameState.currentPlayerIndex) {

            if (!targetCards.isEmpty()) {




                if (targetCards.contains("Princess")) {
                    System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand which contained a princess and was eliminated. \uD83D\uDC80");
                    GameState.eliminatePlayer(targetPlayer, true);
                }

                targetCards.clear();
                Card newCard = Deck.drawCard();

                if (newCard != null) {

                        targetCards.add(newCard.getType().toString());
                        System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand and draws a new card.");

                }
                else {
                    System.out.println("No more cards in the deck to draw.");
                }
            } else {
                System.out.println("Player " + Player.playerNames[targetPlayer] + " has no cards in hand.");
            }
        }
        else if((targetPlayer == GameState.currentPlayerIndex && !Player.getPlayerHand(GameState.currentPlayerIndex).contains("PRINCESS"))){

            targetCards.clear();
            Card newCard = Deck.drawCard();


            if (newCard != null) {

                    targetCards.add(newCard.getType().toString());
                    System.out.println("Player " + Player.playerNames[targetPlayer] + " discards their hand and draws a new card.");

            } else {
                System.out.println("No more cards in the deck to draw.");
            }
        }

        Player.discardCard(selectedCard, GameState.currentPlayerIndex);
    }

    /***
     * switches the cards of a a target player with current players hand
     * @param selectedCard the CardType representing the King card played by the current player.
     */
    private static void kingAction(CardType selectedCard) {

        int targetPlayer = Player.chooseTargetPlayer();

        if (targetPlayer != GameState.currentPlayerIndex) {

            System.out.println(GameState.currentPlayer + " plays King and exchanges hands with " + Player.playerNames[targetPlayer]);
            Player.discardCard(selectedCard, GameState.currentPlayerIndex);

            ArrayList<String> temp = new ArrayList<>(Player.getPlayerHand(GameState.currentPlayerIndex));
            ArrayList<String> currentPlayerHand = new ArrayList<>(Player.getPlayerHand(GameState.currentPlayerIndex));
            ArrayList<String> targetPlayerHand = new ArrayList<>(Player.getPlayerHand(targetPlayer));

            currentPlayerHand.clear();
            currentPlayerHand.addAll(targetPlayerHand);
            targetPlayerHand.clear();
            targetPlayerHand.addAll(temp);

            Player.playerHands.put(GameState.currentPlayer, currentPlayerHand);
            Player.playerHands.put(Player.playerNames[targetPlayer], targetPlayerHand);



        }
        else if (GameState.currentPlayerIndex == targetPlayer) {
            System.out.println("Discarding card without applying effect.");
            Player.discardCard(selectedCard, GameState.currentPlayerIndex);
        }
    }

    /***
     * performs the countess action
     * which is nothign
     * the countess is merely discarded
     *
     * @param selectedCard
     */
    private static void countessAction(CardType selectedCard){
        System.out.println(GameState.currentPlayer + " plays Countess.");
        Player.discardCard(CardType.COUNTESS, GameState.currentPlayerIndex);
    }

}










