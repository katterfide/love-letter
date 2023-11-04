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

        //which cards could eliminate with princess??

        //does countess work?

        GameState.newGame();



        //starting player by token/previousRound

        // winner by tokens


        // displayscore
        //special commands implementation

        //select self with cards / no effect /
        //handmaid protection on enemy what select then


        //Shuffle these 16 cards to form a face-down draw deck. Remove the top card of the deck from the game without
        //looking at it and place it aside.

        //tokens?!


    }
}






