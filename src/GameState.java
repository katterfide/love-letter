import java.util.Arrays;
import java.util.Scanner;

public class GameState {

    static int currentPlayerIndex = 0; // Define the index of the current player

    static String currentPlayer = Player.playerNames[currentPlayerIndex];


    static boolean[] playersProtected;
    public static boolean[] playersEliminated;

    static boolean weHaveAChampion = false;

    static boolean roundOngoing = true;

    public static int[] playerTokens = new int[Player.playerCount];

    static int[] daysSinceLastDate = new int[Player.playerNames.length];

    public static void newGame() throws InterruptedException {
        weHaveAChampion = false;

        initializePlayerTokens();

        while(!weHaveAChampion) {
            GameState.initializeNewRound();
            GameState.startRound();

        }

        System.out.println("The game has been won by " + Player.playerNames[currentPlayerIndex]);

    }

    public static void initializePlayerTokens() {
        for (int i = 0; i < Player.playerCount; i++) {
            playerTokens[i] = 0;
        }
    }

    public static void initializeNewRound(){

        Deck deck = new Deck();
        deck.fillDeck();
        Deck.shuffleDeck();
        //Deck.showDeck();

        GameState.initializePlayersProtection(); //initialize protectionarray with everyone NOT protected

        GameState.initializeEliminationArray();

        Player.generatePlayerHands(deck);
        //Player.printALLPlayerHands();

    }

    public static void startRound() throws InterruptedException {

        //only one card to draw on initialze (even with more players)
        // input startcommand
        startCommands();

        while (roundOngoing) {

            inputCommand();

            GameState.endTurn();
        }

        if (weHaveAChampion){
            roundOngoing = false;
        }
    }

    public static void inputCommand() {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();

            switch (input) {
                case "\\playCard":
                    Card.playCard();
                    break;

                case "\\showHand":
                    Player.displayPlayerHand(Player.playerNames[currentPlayerIndex]);
                    break;

                case "\\showScore":
                    for (int j = 0; j < Player.playerCount; j++) {
                        System.out.print("Player " + Player.playerNames[j] + " has " + playerTokens[j] + " tokens");
                    }
                    break;

                default:
                    System.out.println("Unknown command");
            }
        } while (!input.equals("\\playCard"));
    }

    public static void establishStartingPlayer() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < Player.playerNames.length; i++) {
            System.out.println(Player.playerNames[i] + " how many days ago was your last date with the princess?");
            daysSinceLastDate[i] = GameState.nextInt(sc);
        }

        int minDays = daysSinceLastDate[0];

        for (int i = 1; i < Player.playerNames.length; i++) {

            if (daysSinceLastDate[i] < minDays) {
                minDays = daysSinceLastDate[i];
                currentPlayerIndex = i;
            }
        }

        System.out.println("Player " + Player.playerNames[currentPlayerIndex] + " will start, as the princess favours them.");
        System.out.println();
    }

    public static void initializePlayersProtection() {
        int numberOfPlayers = Player.playerCount;
            playersProtected = new boolean[numberOfPlayers];

            for (int i = 0; i < numberOfPlayers; i++) {
                playersProtected[i] = false;

            }
    }

    public static void setProtection(int playerIndex, boolean isProtected) {
            playersProtected[playerIndex] = isProtected;
    }

    public static void initializeEliminationArray() {
        int numberOfPlayers = Player.playerCount;
        playersEliminated = new boolean[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            playersEliminated[i] = false;
        }
    }

    public static void eliminatePlayer(int Index, boolean isEliminated){
        playersEliminated[Index] = isEliminated;

    }

    private static void checkSurvivors() {
        int remainingPlayers = 0;
        int survivorIndex = 0;

        for (int i = 0; i < Player.playerCount; i++) {
            if (!playersEliminated[i]) {
                remainingPlayers++;
                survivorIndex = i;
            }
        }

        if (remainingPlayers == 1) {
            currentPlayerIndex = survivorIndex;
            currentPlayer = Player.playerNames[currentPlayerIndex];
            System.out.println(currentPlayer + " is the only non-eliminated player left and wins the round!");
            weHaveAWinner();
        }
    }

    public static void endTurn() throws InterruptedException {

        checkSurvivors();
        int startingIndex = currentPlayerIndex;

        if (roundOngoing) {

            if (Deck.cards.length == 0) {                    ///WINCONDITION
                weHaveAWinner();

            }

            System.out.println(Player.playerNames[currentPlayerIndex] + "'s turn ended");
            System.out.println();


            if (currentPlayerIndex >= Player.playerCount - 1) { //"-1" because index starts at 0 at count doesnt

                currentPlayerIndex = 0;

                while (playersEliminated[currentPlayerIndex]) { //if we land on an already eliminated player, we pick the next one
                    currentPlayerIndex++;
                }

                resetWaitAndResetScreen();

            } else {
                currentPlayerIndex++;

                while (playersEliminated[currentPlayerIndex]) {
                    currentPlayerIndex++;
                    if (currentPlayerIndex >= Player.playerCount - 1) { //"-1" because index starts at 0 at count doesnt
                        currentPlayerIndex = 0;
                    }
                }

                resetWaitAndResetScreen();
            }

            while (playersEliminated[currentPlayerIndex]) {
                currentPlayerIndex++;

                resetWaitAndResetScreen();
            }

            if (currentPlayerIndex == startingIndex) {
                weHaveAWinner();

            }
        }

        currentPlayer = Player.playerNames[currentPlayerIndex]; //updating currentPlayer String

    }

    public static void weHaveAWinner(){


        System.out.print("Player " + currentPlayer +" wins this round!");

        int i = playerTokens[currentPlayerIndex] + 1;
        playerTokens[currentPlayerIndex] = i;

        checkTokenChampion();

        System.out.println("Player " + currentPlayer + " gains 1 token.");

        for (int j = 0 ; j < Player.playerCount ; j++){
            System.out.println("Player " + Player.playerNames[j] + " has " + playerTokens[j] + " tokens");

        }


        roundOngoing = false;

    }

    private static void checkTokenChampion() {
        weHaveAChampion = false;

        for (int token : playerTokens) {
            if (Player.playerCount == 2 && token == 7) {
                weHaveAChampion = true;
                break;
            }

            else if (Player.playerCount == 3 && token == 5) {
                weHaveAChampion = true;
                break;
            }

            else if (Player.playerCount == 4 && token == 4){
                weHaveAChampion = true;
                break;

            }
        }
    }

    private static void startCommands() throws InterruptedException {

    Scanner sc = new Scanner(System.in);
    String choice;

    System.out.println("type " + "\\" + "start to begin the game:");


    while (true) {
        choice = sc.next();

        if (choice.equals("\\" + "start")) {
            break;
        } else {
            System.out.println("Command not recognized.");
        }
    }


    System.out.println("We're all set!");
    Thread.sleep(1000);

}

    private static void resetWaitAndResetScreen() throws InterruptedException {

        if (roundOngoing) {
            System.out.println("Please pass the laptop along now.");
            Thread.sleep(5000);


            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }

    }



    public static int nextInt(Scanner sc) {

        int value;
        while (true) {
            try {
                value = Integer.parseInt(sc.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return value;
    } //catches stringinputs for int inputs







    //specialCommands??
    //invalid inputs




}

