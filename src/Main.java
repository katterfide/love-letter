import java.security.Guard;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to Love Letter!");

        Deck deck = new Deck();
        deck.fillDeck();
        Deck.shuffleDeck();

        System.out.println("\nShuffled deck:");

        deck.showDeck();

        Player.inputPlayerCount();
        Player.inputPlayerNames();
        //Shuffle these 16 cards to form a face-down draw deck. Remove the top card of the deck from the game without
        //looking at it and place it aside.

        //f you are playing a 2-player game, take 3 more cards from the top of the deck and place them to the side, face up.
        //They will not be used during this round, but are available for all players to examine during the game.

        //tokens?!


        GameState.initializePlayersProtection(); //initialize protectionarray with everyone NOT protected

        GameState.initializeEliminationArray();




        Player.generatePlayerHands(deck);

        Player.printALLPlayerHands();


        deck.showDeck();

        Card.playCard();
        GameState.endTurn();
        Player.printALLPlayerHands();
        //only one card to draw on initialize ( depending on playercount)
        //draw on start of round
        //check for card



        System.out.println("type " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands:");


        while (true) {
            choice = sc.next();

            if (choice.equals("\\" + "start")) {
                break;
            } else if (choice.equals("\\" + "help")) {
                System.out.println("Your only available commands right now are " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands.");
            } else {
                System.out.println("Command not recognized.");
                System.out.println("For a list of available commands type: " + "\"" + "\\" + "help" + "\"");
            }
        }


        System.out.println("We're all set!");
        Thread.sleep(1000);
        System.out.println("Get ready to start playing in 3...");
        System.out.println("Get ready to start playing in 2...");
        System.out.println("Get ready to start playing in 1...");
        System.out.println("Let's go!");

        System.out.println("For a list of available commands type: " + "\\" + "help");

        label:
        while (true) {
            choice = sc.next();

            switch (choice) {
                case "\\" + "help":
                    System.out.println("\\" + "help to display a list of available commands.");
                    System.out.println("\\" + "playCard to play a card from your Hand");
                    System.out.println("\\" + "showHand to show your current cards");
                    System.out.println("\\" + "showScore to show the scoreboard");
                    break;

                case "\\" + "showHand":
                    System.out.println("you have a <card1> and a <card 2> on hand.");
                    break;

                case "\\" + "showScore":
                    //Score array
                    System.out.println("<scoreArray> with playerNames");
                    break;

                case "\\" + "playCard":
                    //which card?
                    //against who?
                    System.out.println("you have played <playedCard> against <playerName i>");
                    System.out.println("your turn is over.");
                    break label;

                default:
                    System.out.println("Command not recognized.");
                    System.out.println("For a list of available commands type: " + "\"" + "\\" + "help" + "\"");
                    break;
            }


        }

        }
    }
