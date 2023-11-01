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

            case PRIEST:
                priestAction();
                break;
                 /*
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
            for (String card : Player.getPlayerHand(targetPlayer)) {
                System.out.println(card);
            }
        } else {
            System.out.println("No hand found for player: " + Player.playerNames[targetPlayer]);
        }
            //cant be eliminated player
    }

    private void baronAction(Player currentPlayer, Player targetPlayer) {

        System.out.println(currentPlayer.getName() + " plays Baron and compares hands with " + targetPlayer.getName() + ".");
    }

    private void handmaidAction(Player currentPlayer, Player targetPlayer) {

        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void princeAction(Player currentPlayer, Player targetPlayer) {

        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void kingAction(Player currentPlayer, Player targetPlayer) {

        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }

    private void countessAction(Player currentPlayer, Player targetPlayer) {

        System.out.println(currentPlayer.getName() + " plays Guard and guesses " + targetPlayer.getName() + "'s hand card.");
    }







}
