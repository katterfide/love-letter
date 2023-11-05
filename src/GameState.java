import java.util.ArrayList;
import java.util.Scanner;

public class GameState {

    static int currentPlayerIndex = 0; // Define the index of the current player

    static String currentPlayer = Player.playerNames[currentPlayerIndex];

    static boolean[] playersProtected;

    public static boolean[] playersEliminated;

    static boolean weHaveAChampion = false;

    static boolean roundOngoing = true;

    static int previousWinnerIndex = 0;

    public static int[] playerTokens = new int[Player.playerCount];

    static int[] daysSinceLastDate = new int[Player.playerNames.length];

    public static void newGame() throws InterruptedException {
        weHaveAChampion = false;

        initializePlayerTokens();
        int counter = 0;

        while(!weHaveAChampion) {

            if (counter >= 1){
                currentPlayerIndex = previousWinner();
                System.out.println(Player.playerNames[currentPlayerIndex] + " will go first because they won the last round");
            }
            counter++;


            GameState.initializeNewRound();
            GameState.startRound();

        }

        System.out.println("\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6The game has been won by " + Player.playerNames[currentPlayerIndex] + "\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6");

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
        roundOngoing = true;
        //only one card to draw on initialze (even with more players)
        // input startcommand
        checkTokenChampion();
        startCommands();

        while (roundOngoing) {

            inputCommand();

            endTurn();
        }

        if (weHaveAChampion){
            roundOngoing = false;
        }
    }

    public static void inputCommand() {
        Scanner sc = new Scanner(System.in);
        String input;


        label:
        while(true){
            System.out.println("type " + "\\" + "playCard or 1 to have your cards displayed and proceed to play your hand.");
            System.out.println("type " + "\\" + "help to display a list of available commands.");
            System.out.println();

            System.out.println("Waiting for input by player #" + (currentPlayerIndex + 1) + " " + Player.playerNames[GameState.currentPlayerIndex] + ":");

            input = sc.nextLine();

            switch (input) {
                case "\\playCard", "1":
                    Card.playCard();
                    break label;

                case "\\showHand":
                    Player.displayPlayerHand(Player.playerNames[currentPlayerIndex]);
                    System.out.println();
                    break;

                case "\\showScore":
                    showScore();
                    break;

                case "\\" + "help":
                    System.out.println("type " + "\\" + "help to display a list of available commands.");
                    System.out.println("type " + "\\" + "playCard to play a card from your Hand");
                    System.out.println("type " + "\\" + "showHand to show your current cards");
                    System.out.println("type " + "\\" + "showScore to show the scoreboard");
                    System.out.println("type " + "\\" + "showPlayers to show players still in the round and eliminated ones");
                    break;


                case "\\showPlayers":
                    System.out.println("These players are still in the game:");

                    for (int i = 0; i < Player.playerCount ; i++){

                        if (!playersEliminated[i]){
                            System.out.println(Player.playerNames[i]);
                        }

                    }

                    System.out.println("These players have been eliminated:");
                    for (int i = 0; i < Player.playerCount ; i++){
                        if (playersEliminated[i]){
                            System.out.print(Player.playerNames[i]);
                            System.out.println("\uD83D\uDC80");
                        }

                    }

                    break;

                default:
                    System.out.println("Command not recognized.");
                    System.out.println("For a list of available commands type: " + "\"" + "\\" + "help" + "\"");
                    break;
            }
        }
    }

    public static void showScore(){
        for (int j = 0; j < Player.playerCount; j++) {

            if (playerTokens[j] == 1){
                System.out.println("Player " + Player.playerNames[j] + " has " + playerTokens[j] + " token.");

            }
            else {
                System.out.println("Player " + Player.playerNames[j] + " has " + playerTokens[j] + " tokens.");
            }

            for (int i = 0 ; i < playerTokens[j] ; i++ ){
                System.out.print("♦️");

            }
            System.out.println();
        }
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

    public static int previousWinner(){
        int playerIndex = 0;

        playerIndex = previousWinnerIndex;

        return playerIndex;
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
            System.out.println(currentPlayer + " is the only non-eliminated player left.");
            weHaveAWinner();
        }
    }

    public static void endTurn() throws InterruptedException {
        emptyDeckWinner();
        checkSurvivors();

        int startingIndex = currentPlayerIndex;


        if (roundOngoing) {
            System.out.println(Player.playerNames[currentPlayerIndex] + "'s turn ended");
            System.out.println();

            do {
                if (currentPlayerIndex < Player.playerCount - 1) {
                    currentPlayerIndex++;
                } else {
                    currentPlayerIndex = 0;
                }

                resetWaitAndResetScreen();
            } while (playersEliminated[currentPlayerIndex]);

            if (currentPlayerIndex == startingIndex) {
                System.out.println("current player is the same as the starting index");
                weHaveAWinner();
            }
        }

        currentPlayer = Player.playerNames[currentPlayerIndex]; // updating currentPlayer sring
    }

    public static void emptyDeckWinner() {
        if (Deck.cards.length == 0) {
            System.out.println("The deck is empty.");
            System.out.println("Player card comparisons will commence");
            System.out.println("The highest card wins the round.");

            for (int i = 0; i < Player.playerCount; i++) {
                Player.displayPlayerHand(Player.playerNames[i]);
            }

            int maxCardValue = 0;
            ArrayList<Integer> playersWithMaxValue = new ArrayList<>();

            for (int i = 0; i < Player.playerCount; i++) {
                ArrayList<String> playerHand = Player.getPlayerHand(i);

                if (playerHand.size() >= 1) {
                    int card1Value = Card.cardValue(playerHand.get(0));
                    int highestValue = card1Value;

                    if (playerHand.size() == 2) {
                        int card2Value = Card.cardValue(playerHand.get(1));
                        highestValue = Math.max(card1Value, card2Value);
                    }

                    if (highestValue > maxCardValue) {
                        maxCardValue = highestValue;
                        playersWithMaxValue.clear();
                        playersWithMaxValue.add(i);
                    } else if (highestValue == maxCardValue) {
                        playersWithMaxValue.add(i);
                    }
                }
            }

            for (int playerIndex : playersWithMaxValue) {
                int i = playerTokens[playerIndex] + 1;
                playerTokens[playerIndex] = i;

                System.out.println("Player " + Player.playerNames[playerIndex] + " wins this round. \uD83C\uDFC6 ");
                System.out.println("Player " + Player.playerNames[playerIndex] + " gains 1 token.");

                showScore();

                previousWinnerIndex = playerIndex;
                System.out.println();
            }
            roundOngoing = false;
        }
    }


    public static void weHaveAWinner(){


        System.out.println("Player " + currentPlayer +" wins this round! \uD83C\uDFC6 ");

        int i = playerTokens[currentPlayerIndex] + 1;
        playerTokens[currentPlayerIndex] = i;

        previousWinnerIndex = currentPlayerIndex;

        checkTokenChampion();

        System.out.println("Player " + currentPlayer + " gains 1 token.");

        showScore();


        roundOngoing = false;

    }

    private static void checkTokenChampion() {
        weHaveAChampion = false;

        for (int token : playerTokens) {
            if (Player.playerCount == 2 && token == 1) {
                weHaveAChampion = true;
                break;
            }

            else if (Player.playerCount == 3 && token == 1) {
                weHaveAChampion = true;
                break;
            }

            else if (Player.playerCount == 4 && token == 1){
                weHaveAChampion = true;
                break;

            }
        }
    }

    private static void startCommands() throws InterruptedException {

    Scanner sc = new Scanner(System.in);
    String choice;

    System.out.println("type " + "\\" + "start to begin the round:");


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
    System.out.println();



    }

    private static void resetWaitAndResetScreen() throws InterruptedException {
/*
        if (roundOngoing) {
            System.out.println("Please pass the laptop along now.");
            Thread.sleep(5000);
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }

 */ //method for error hunting with my girlfriend so "i wouldnt cheat"

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


}

