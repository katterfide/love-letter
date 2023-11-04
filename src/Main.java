import java.security.Guard;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to Love Letter!");

        Player.inputPlayerCount();
        Player.inputPlayerNames();
        GameState.establishStartingPlayer();

        //these have to be outside because otherwise playerName = null and error and explosion
//which cards could eliminate with princess??

        //Baron work?

        //does countess work?

        GameState.newGame();

        //we have a champion

        //target eliminated player


        //starting player by token/previousRound


        // winner by tokens

        // displayscore
        //special commands implementation

        //select self with cards / no effect /
        //handmaid protection on enemy what select then


        //Shuffle these 16 cards to form a face-down draw deck. Remove the top card of the deck from the game without
        //looking at it and place it aside.

        //f you are playing a 2-player game, take 3 more cards from the top of the deck and place them to the side, face up.
        //They will not be used during this round, but are available for all players to examine during the game.

        //tokens?!

        //cards when only 2 players and one protected?!


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






