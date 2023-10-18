import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String choice;
        int playerCount;


            System.out.println("Welcome to Love Letter!");
            System.out.println("type " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands:");

                while(true) {
                    choice = sc.nextLine();


                    if (choice.equals("\\" + "start")) {
                        break;
                    } else if (choice.equals("\\" + "help")) {
                        System.out.println("Your only available commands right now are " + "\\" + "start to begin the game, or " + "\\" + "help for a list of available commands.");
                    } else {
                        System.out.println("Command not recognized.");
                        System.out.println("For a list of available commands type: " +"\"" + "\\" + "help" + "\"");
                    }
                }

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


                String[] playerNames = new String[playerCount + 1];
                for (int i = 1; i <= playerCount; i++) {
                    System.out.print("Enter player name #" + i + ": ");
                    playerNames[i] = sc.next();
                }


    }




        //scan name
        //how many players
        //playernames
        //ready?
        //go!
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


