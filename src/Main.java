import java.util.Arrays;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static int playerCount = 0;
    public static String[] playerNames = null;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String choice;

        Player[] players = new Player[playerCount];


        System.out.println("Welcome to Love Letter!");

        while(true) {
            System.out.println("How many players are there? (2-4)");
            playerCount = sc.nextInt();

            if (playerCount > 4) {
                System.out.println("That is too many players. Please limit yourself to playing with 2-4 players.");
            }
            else if (playerCount < 2) {
                System.out.println("That is too little players. Please limit yourself to playing with 2-4 players.");
            }
            else {
                break;
            }
        }

        playerNames = new String[playerCount];
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player name #" + (i + 1) + ": ");
            playerNames[i] = sc.next();
        }

        System.out.println(Arrays.toString(playerNames));
        System.out.println(Player.currentPlayer);


    }

}





    /*public static void main(String[] args) throws InterruptedException {        //brauche throws interrupted Exception weil sonst sleep nicht akzeptiert wird
        Scanner sc = new Scanner(System.in);
        String choice;
        int playerCount;


            System.out.println("Welcome to Love Letter!");

                while(true) {
                    System.out.println("How many players are there? (2-4)");
                    playerCount = sc.nextInt();

                    if (playerCount > 4) {
                        System.out.println("That is too many players. Please limit yourself to playing with 2-4 players.");
                    }
                    else if (playerCount < 2) {
                        System.out.println("That is too little players. Please limit yourself to playing with 2-4 players.");
                    }
                    else {
                        break;
                    }
                }


                String[] playerNames = new String[playerCount + 1];     //ACHTUNG ARRAY SIZE  HIER playerCount +1! weil ich mit i = 1 anfange und array sonst out of bounds geht
                for (int i = 1; i <= playerCount; i++) {
                    System.out.print("Enter player name #" + i + ": ");
                    playerNames[i] = sc.next();
                }


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
*/
