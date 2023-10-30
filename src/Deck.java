import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    public int cardsInDeck;
    private static Card[] cards;

    public Deck() {
        cards = new Card[16];
        fillDeck();
    }

    public void fillDeck() {
        cardsInDeck = 0;

        // Create a separate loop for each card type
        addCards(Card.CardType.GUARD, 5);
        addCards(Card.CardType.PRIEST, 2);
        addCards(Card.CardType.BARON, 2);
        addCards(Card.CardType.HANDMAID, 2);
        addCards(Card.CardType.PRINCE, 2);
        addCards(Card.CardType.KING, 1);
        addCards(Card.CardType.COUNTESS, 1);
        addCards(Card.CardType.PRINCESS, 1);
    }

    // Helper method to add cards of a specific type to the deck
    private void addCards(Card.CardType type, int count) {
        for (int i = 0; i < count; i++) {
            if (cardsInDeck < cards.length) {
                cards[cardsInDeck++] = new Card(type);
            } else {
                break; // Avoid adding cards beyond the deck size
            }
        }
    }

    public static void shuffleDeck() {
        List<Card> cardList = Arrays.asList(cards);
        Collections.shuffle(cardList);
        cardList.toArray(cards);
    }

    public static void drawCards(ArrayList<String> deck, int numberOfCardsToDraw) {
        if (deck.size() >= numberOfCardsToDraw) {
            System.out.println("Drawing " + numberOfCardsToDraw + " card(s):");
            for (int i = 0; i < numberOfCardsToDraw; i++) {
                String drawnCard = deck.remove(0); // Remove the top card from the deck
                System.out.println("Drawn card: " + drawnCard);
            }
        } else {
            System.out.println("Deck is empty.");
        }
    }

    public void showDeck() {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                System.out.println(cards[i].getType());
            }
        }
    }
    public void show3card() {
                System.out.println(cards[3].getType());
    }
}