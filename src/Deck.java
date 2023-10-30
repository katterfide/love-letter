import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    public int cardsInDeck;
    private Card cards[];

    public Deck(){
        cards = new Card[16];
        fillDeck();
    }

    public void fillDeck(){
        cardsInDeck = 0;

        Card.CardType[] types = new Card.CardType[] {Card.CardType.GUARD};
        for (Card.CardType type : types){
            for (int i = 0; i < 5; i++){
                cards[cardsInDeck++] = new Card(type);
            }
        }

        types = new Card.CardType[]{Card.CardType.BARON, Card.CardType.PRIEST, Card.CardType.HANDMAID, Card.CardType.PRINCE};
        for (Card.CardType type : types){
            for (int i = 0; i < 2; i++){
                cards[cardsInDeck++] = new Card(type);
            }
        }

        types = new Card.CardType[]{Card.CardType.KING,Card.CardType.COUNTESS,Card.CardType.PRINCESS};
        for (Card.CardType type : types){
            for (int i = 0; i < 1; i++){
                cards[cardsInDeck++] = new Card(type);
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(List.of(cards));
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

//drawcards?
}
