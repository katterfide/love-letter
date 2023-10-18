import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String choice;

            System.out.println("type " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands:");

                choice = sc.nextLine();
                if (choice.equals("\\" + "start")) {
                    System.out.println("How many players are there?");
                    //scan name
                    //how many players
                    //playernames
                    //ready?
                    //go!
                }

                else if (choice.equals("\\" + "help")) {
                    System.out.println("Your only available commands right now are " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands.");
                }

                else {
                    System.out.println("Command not recognized.");
                }
    }

}



        /*
        scan for start
        playerCount?
        playerNames?
        (ready?)
        start of game confirmation
            help always available from here
        gamemoves
            playCard
            showHand
            showScore
         */


