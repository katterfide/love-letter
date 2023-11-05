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


        // has 1 tokens, bad english
        //block text when winning
        //doesnt say whos turn on new game

        //baron apparently doesnt work, maybe cuz it eliminates baro

        //reestablish player after win

        //remove cardvalue print for baron
        //remove displayHand when


        GameState.newGame();



        //starting player by token/previousRound

        // winner by tokens


        // displayscore
        //special commands implementation

        //select self with cards / no effect /
        //handmaid protection on enemy what select then

        //hasRoyalsInHand determined false print


        //Shuffle these 16 cards to form a face-down draw deck. Remove the top card of the deck from the game without
        //looking at it and place it aside.

        //tokens?!

        //King doesnt clear cards proepr?
        //duplicate cards..

    }
}






