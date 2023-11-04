import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    public int cardsInDeck;
    static Card[] cards;

    public Deck() {
        cards = new Card[16];
        fillDeck();
    }

    public void fillDeck() {
        cardsInDeck = 0;

        // Create a separate loop for each card type
        addCardsDeck(Card.CardType.GUARD, 5);
        addCardsDeck(Card.CardType.PRIEST, 2);
        addCardsDeck(Card.CardType.BARON, 2);
        addCardsDeck(Card.CardType.HANDMAID, 2);
        addCardsDeck(Card.CardType.PRINCE, 2);
        addCardsDeck(Card.CardType.KING, 1);
        addCardsDeck(Card.CardType.COUNTESS, 1);
        addCardsDeck(Card.CardType.PRINCESS, 1);
    }

    // method to add cards of type to deck
    private void addCardsDeck(Card.CardType type, int count) {
        for (int i = 0; i < count; i++) {
            if (cardsInDeck < cards.length) {
                cards[cardsInDeck++] = new Card(type);
            } else {
                break; // avoids adding cards beyond the deck size
            }
        }
    }

    public static void shuffleDeck() {
        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
        cardList.toArray(cards);
    }

    public static Card drawCard() {
        if (cards.length > 0 && cards[0] != null) {
            Card drawnCard = cards[0];

            //shift remaining cards left
            for (int i = 1; i < cards.length; i++) {
                cards[i - 1] = cards[i];
            }

            cards[cards.length - 1] = null; /////set the last position to null as the card has been removed
            cards = Arrays.copyOf(cards, cards.length - 1); // reduce array length
            return drawnCard;

        } else {
            System.out.println("No cards left in deck.");
            return null; // No cards left in the deck
        }
    }


    //just a errorhunting method
    public static void showDeck() {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                System.out.println(cards[i].getType());
            }
        }
    }
}