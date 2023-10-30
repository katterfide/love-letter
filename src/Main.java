import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to Love Letter!");

        Deck deck = new Deck();

        // Show the initial state of the deck
        System.out.println("Initial state of the deck:");
        deck.showDeck();

        // Shuffle the deck
        Deck.shuffleDeck();

        // Show the shuffled deck
        System.out.println("\nShuffled deck:");
        deck.showDeck();

        System.out.println("\nshow3card:");
        deck.show3card();

        Player.inputPlayerCount();
        Player.inputPlayerNames();


        System.out.println("type " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands:");


        while(true) {
            choice = sc.next();

            if (choice.equals("\\" + "start")) {
                break;
            } else if (choice.equals("\\" + "help")) {
                System.out.println("Your only available commands right now are " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands.");
            } else {
                System.out.println("Command not recognized.");
                System.out.println("For a list of available commands type: " +"\"" + "\\" + "help" + "\"");
            }
        }



        System.out.println("We're all set!");
        Thread.sleep(1000);
        System.out.println("Get ready to start playing in 3...");
        Thread.sleep(1000);
        System.out.println("Get ready to start playing in 2...");
        Thread.sleep(1000);
        System.out.println("Get ready to start playing in 1...");
        Thread.sleep(1000);
        System.out.println("Let's go!");

        System.out.println("For a list of available commands type: " + "\\" + "help");

        label:
        while(true) {
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
        }                                               //gamemoves "Grundgerüst"
    }

}