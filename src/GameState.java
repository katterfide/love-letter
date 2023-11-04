import java.util.Scanner;

public class GameState {

    static int currentPlayerIndex = 0; // Define the index of the current player

    static String currentPlayer = Player.playerNames[currentPlayerIndex];

    static boolean[] playersProtected;
    public static boolean[] playersEliminated;

    static boolean weHaveAChampion = false;

    static boolean roundOngoing = true;

    public static int[] playerTokens;

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

    public static void initializePlayerTokens(){
        for (int i = 0; i > Player.playerCount; i++) {
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


            Card.playCard();
            //checkWin, if no cards / only player alive
            GameState.endTurn();
        }

        if (weHaveAChampion){
            roundOngoing = false;
        }
    }

    public static void establishStartingPlayer() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < Player.playerNames.length; i++) {
            System.out.println(Player.playerNames[i] + " how many days ago was your last date with the princess?");
            daysSinceLastDate[i] = sc.nextInt();
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

    public static void endTurn() throws InterruptedException {
        int startingIndex = currentPlayerIndex;

        if (Deck.cards.length == 0) {                    ///WINCONDITION
            weHaveAWinner();

        }

        System.out.println(Player.playerNames[currentPlayerIndex] + "'s turn ended");
        System.out.println();

        if (currentPlayerIndex >= Player.playerCount) {
            currentPlayerIndex = 0;

            resetWaitAndResetScreen();

        } else {
            currentPlayerIndex++;
            resetWaitAndResetScreen();
        }

        while (playersEliminated[currentPlayerIndex]) {
            currentPlayerIndex++;
            if (currentPlayerIndex >= Player.playerCount) {
                currentPlayerIndex = 0;
            }
            resetWaitAndResetScreen();
        }

        if (currentPlayerIndex == startingIndex) {
            weHaveAWinner();

        }

        currentPlayer = Player.playerNames[currentPlayerIndex]; //updating currentPlayer String
        System.out.println("it is now " + currentPlayer + "'s turn.");

    }

    public static void weHaveAWinner(){

        roundOngoing = false;
        System.out.println("Player " + currentPlayer +" wins this round!");
        System.out.println("Player " + currentPlayer +" wins this round!");

        playerTokens[currentPlayerIndex]++ ;
        checkTokenChampion();

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

        System.out.println("Please pass the laptop along now.");
        Thread.sleep(5000);

        for (int i = 0; i < 50; i++){
            System.out.println();
        }

    }











    //specialCommands??
    //invalid inputs




}

