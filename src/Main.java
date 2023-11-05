import java.security.Guard;
import java.util.Scanner;

public class Main {

    /***
     * with new game have to input playercount, playernames and establish starting polayer first
     * after that game loop is entered
     *
     *
     * @param args
     * @throws InterruptedException required to input as i have a wait amt of seconds function somewhere
     */
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Welcome to Love Letter!");

        Player.inputPlayerCount();
        Player.inputPlayerNames();
        GameState.establishStartingPlayer();

        GameState.newGame();

    }
}






