/*COMP3211 Course Project - Monopoly Game
Group 43

Game Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This class is the main core class of the whole program and will first be accessed upon booting up of the program. It handles functions
related to the main menu of the game, as well as the logic of the game.
 */
package com.company;

import java.io.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Game {

    Board board;

    /*
        The main function first run by the compiled program. It will first call "mainMenuHandler()" for printing and prompt
        of the main menu UI, then game logic will be handled by the gameManager method when the user starts a game through the
        mainMenuHandler.
         */
    public static void main(String[] args) {
        Game game;
        while (true) {
            game = new Game();
            try {
                game.mainMenuHandler();
                game.board.initSquares();
                game.gameManager();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    This method prints the command line UI for the main menu when the game first starts. It will ask the user for number of players,
    and confirmation to start the game.
     */
    public void mainMenuHandler() throws IOException {
        clearScreen();
        System.out.println("Welcome to Monopoly Game!");

        boolean boolMainMenu = false;
        while (!boolMainMenu) {
            // Start new game or load game:
            // Get user input.
            System.out.println("Press 0: Start a new game");
            System.out.println("Press 1: Load game");
            System.out.println("Press 2: Exit");
            String newOrLoad;
            boolean boolNewOrLoad = false;
            do {
                // Enter data using BufferReader
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                newOrLoad = reader.readLine();

                if (newOrLoad.equals("0") || newOrLoad.equals("1") || newOrLoad.equals("2")) {
                    boolNewOrLoad = true;
                } else {
                    System.out.println("Only 0 or 1 or 2 is allowed. Please try again: ");
                }
            }
            while (!boolNewOrLoad);

            // Exit
            if(newOrLoad.equals("2")) {
                System.exit(0);
            }

            clearScreen();

            // Start new game
            if (newOrLoad.equals("0")) {
                this.board = new Board();

                // Asking the number of players
                System.out.println("Number of players (2-6): ");
                String numberOfPlayer;
                boolean boolNumberOfPlayer = false;
                do {
                    // Enter data using BufferReader
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(System.in));

                    // Reading data using readLine
                    numberOfPlayer = reader.readLine();

                    if (Integer.parseInt(numberOfPlayer) >= 2 && Integer.parseInt(numberOfPlayer) <= 6)
                        boolNumberOfPlayer = true;

                    if (!boolNumberOfPlayer)
                        System.out.println("Only 2-6 is allowed. Please try again:");
                }
                while (!boolNumberOfPlayer);
                // setPlayerCount
                //this.board.setPlayerCount(Integer.parseInt(numberOfPlayer));
                // Create players
                for (int i = 1; i <= Integer.parseInt(numberOfPlayer); i++) {
                    Player player = new Player(i);
                    this.board.addPlayer(player);
                }
                ArrayList<Player> playerList = this.board.getPlayerList();
                this.board.setTargetPlayer(playerList.get(0));
                for (int i = 0; i < playerList.size(); i++) {
                    //System.out.println("i:" + i);
                    playerList.get(i).setLocation(this.board.getHeadSquare());
                    playerList.get(i).setStatus("Free");
                }


            }
            // Load game
            else {
                // Load the board if it exists. Otherwise, loop back to the beginning.
                try {
                    loadBoard();
                } catch (FileNotFoundException | ClassNotFoundException e) {
                    if (e instanceof FileNotFoundException) {
                        System.out.println("No file can be loaded.");
                        continue;
                    }
                    e.printStackTrace();
                }
            }

            clearScreen();

            // Confirm to start the game
            System.out.println("Start the game? Press 0: Discard. Press 1: Start the game.");
            String startGame;
            boolean boolStartGame = false;
            do {
                // Enter data using BufferReader
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                startGame = reader.readLine();

                if (startGame.equals("0") || startGame.equals("1")) {
                    boolStartGame = true;
                } else {
                    System.out.println("Only 0 or 1 is allowed. Please try again:");
                }
            }
            while (!boolStartGame);
            // Start the game.
            if (startGame.equals("1")) {
                //System.out.println("Game board initializing...");
                boolMainMenu = true;

            }


        }
    }

    /*
    This method is the "core" method of the whole program. It handles the whole game logic and performs decision making on calling which
    other methods from objects/classes as the gameplay progresses.

    A detailed description of the game logic is explained in the Software Requirements Document. Here is a quick abstraction of the proposed
    game logic to be handled by the method.

    1. The method will initialize and reset variables for a new round.
    2. The method will request the Dices class to throw dice
    3. The method will check the information on the Dices class and determine what square it lands on.
    4. The method will call respective square effects.
    5. The method will check players' remaining money and determine if someone needs to be kicked from a round.
    6. The method will determine whether it's game over (round over) (if there is only 1 remaining player left), or it will move to next target player for
    dice rolling.
     */
    public void gameManager() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        //boolean gameIsRunning = true;

        //System.out.println("Running");

        //Step 1: Start a new round by resetting variables, reset player locations

        //System.out.println("Running");


        ArrayList<Player> playerList = this.board.getPlayerList();




        boolean roundRunning = true;

        while (this.board.getPlayerCount() >= 2 && this.board.getCurrentRound() <= 100) {
            //Checking roundRunning attributes

            if (this.board.getTargetPlayer().getStatus().equals("Retired")) {
                if (playerList.indexOf(this.board.getTargetPlayer()) == playerList.size() - 1) {
                    this.board.setCurrentRound(this.board.getCurrentRound() + 1);
                    this.board.setTargetPlayer(playerList.get(0));
                } else {
                    this.board.setTargetPlayer(playerList.get(this.board.getTargetPlayer().getPlayerID() - 1 + 1));
                }
                continue;
            }


            //Step 2: Throws dice, check get out of jail conditions


            this.board.printGameBoard();



            //Round 0 -> Just got in jail
            //Round 1 -> Allow fine payment, allow throw double
            //Round 2 -> Allow fine payment, allow throw double
            //Round 3 -> Allow throw double, if fail, force pay fine get out.


            //Ask if the player wants to pay the fine to get out of jail before rolling dice. Else, increment the
            //current round of jail the player is in by 1.
            if (this.board.getTargetPlayer().getStatus().equals("In Jail") && this.board.getTargetPlayer().getPlayerJailCount() <= 2) {
                clearScreen();
                this.board.getTargetPlayer().setPlayerJailCount(this.board.getTargetPlayer().getPlayerJailCount()+1); //Increment jail count

                this.board.printGameBoard();
                System.out.println("Current Player: Player " + this.board.getTargetPlayer().getPlayerID());
                System.out.println("You are currently in jail. Do you want to pay the fine of HKD 150 to get out of jail?");
                System.out.print("Press 1 (Yes) or 0 (No)");

                // Reading data using readLine
                String input = reader.readLine();
                boolean validInput = false;

                //Ask for input
                while (!validInput) {
                    if (input.equals("1") || input.equals("0")) {
                        validInput = true;
                    } else {
                        System.out.println("Only 1 or 0 is allowed. Please try again: ");
                        input = reader.readLine();
                    }
                }

                //Throw dice
                if (input.equals("1")) {
                    if (this.board.getTargetPlayer().getMoney() >= 150) {
                        this.board.getTargetPlayer().deductMoney(150);
                        this.board.getTargetPlayer().setStatus("Free");
                    } else {
                        System.out.println("You have insufficient money to get out of jail! Please continue to roll dice...");
                        System.out.println("Press enter to continue...");
                        reader.readLine();
                    }

                }
            }

            // If player wants to save the board and return to main menu, break the looping.
            if(promptRollDice())
                break;



            //Check 2nd set of conditions

            //Condition 1: If playerStatus” is “Free”, playerPosition will be incremented by the sum of
            //two dice values. Their token positions will be updated on the game board UI
            if (this.board.getTargetPlayer().getStatus().equals("Free")) {

                //Iterate and move locations on the board for the target player.
                int diceSum = this.board.getDiceObj().getSum();

                for (int i = 0; i < diceSum; i++) {
                    //Find the square with the corresponding address
                    Squares targetSquare = this.board.getTargetPlayer().getLocation();
                    targetSquare = targetSquare.getNextSquare();
                    this.board.getTargetPlayer().setLocation(targetSquare);
                    if (targetSquare instanceof GoHandler) {
                        this.board = ((GoHandler) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                    }
                }
            }

            //Condition 2: If the playerStatus” is “In Jail”, it will check the value of two dices. If Dice0
            //equals Dice1, playerPosition will be incremented by the sum of Dice0 and
            //Dice1, and playerStatus will be set to “Free”.

            //If Dice0 does not equal Dice1, it will check playerJailCount. If playerJailCount
            //>= 3, currentPlayer’s position will be incremented by the sum of Dice0 and
            //Dice1, fine will be ducted, token positions will be updated on the game UI, its playerStatus will be
            //set to “Free” and current round of jail will be set to 0;

            if (this.board.getTargetPlayer().getStatus().equals("In Jail")) {
                if (this.board.getDiceObj().isEqual()) {
                    //Iterate and move locations on the board for the target player.
                    int diceSum = this.board.getDiceObj().getSum();

                    for (int i = 0; i < diceSum; i++) {
                        //Find the square with the corresponding address
                        Squares targetSquare = this.board.getTargetPlayer().getLocation();
                        targetSquare = targetSquare.getNextSquare();
                        this.board.getTargetPlayer().setLocation(targetSquare);
                        if (targetSquare instanceof GoHandler) {
                            this.board = ((GoHandler) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                        }
                    }

                    this.board.getTargetPlayer().setStatus("Free");
                    this.board.getTargetPlayer().setPlayerJailCount(0);

                    System.out.println("Current Player: Player " + this.board.getTargetPlayer().getPlayerID());
                    System.out.println("You have roll two dices with the same value.");
                    System.out.println("Releasing player out of jail...");
                    System.out.print("Press enter to continue...");

                    // Reading data using readLine
                    reader.readLine();
                }
                else {
                    if (this.board.getTargetPlayer().getPlayerJailCount() >= 3) {

                        this.board.printGameBoard();
                        this.board.getTargetPlayer().setPlayerJailCount(this.board.getTargetPlayer().getPlayerJailCount()+1); //Increment jail count

                        this.board.printGameBoard();
                        System.out.println("Current Player: Player " + this.board.getTargetPlayer().getPlayerID());
                        System.out.println("You have been in jail for three rounds and you failed the dice roll.");
                        System.out.println("Releasing player out of jail and forcefully deduct HKD 150 from your balance...");

                        //Iterate and move locations on the board for the target player.
                        int diceSum = this.board.getDiceObj().getSum();

                        for (int i = 0; i < diceSum; i++) {
                            //Find the square with the corresponding address
                            Squares targetSquare = this.board.getTargetPlayer().getLocation();
                            targetSquare = targetSquare.getNextSquare();
                            this.board.getTargetPlayer().setLocation(targetSquare);
                            if (targetSquare instanceof GoHandler) {
                                this.board = ((GoHandler) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                            }
                        }

                        this.board.getTargetPlayer().setStatus("Free");
                        this.board.getTargetPlayer().setPlayerJailCount(0);
                    } else {
                        System.out.println("Current Player: Player " + this.board.getTargetPlayer().getPlayerID());
                        System.out.println("You have failed to roll two dices with the same value.");
                        System.out.println("You will stay in jail for this round");

                    }
                }

            }

            //Step 3: Check Dice Position & Step 4: Implement Special Square Effects & Step 5: Implement Property Square Effect
            //Step 4 and 5 is integrated into takeAction() across all the subclass
            //Checks the dice position of the current player and invokes corresponding
            //functions.

            if (this.board.getTargetPlayer().getStatus().equals("Free")) {
                this.board.printGameBoard();

                if (this.board.getTargetPlayer().getLocation() instanceof Chance) {
                    this.board = ((Chance) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                } else if (this.board.getTargetPlayer().getLocation() instanceof IncomeTax) {
                    this.board = ((IncomeTax) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                } else if (this.board.getTargetPlayer().getLocation() instanceof InJailJustVisiting) {
                    this.board = ((InJailJustVisiting) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                } else if (this.board.getTargetPlayer().getLocation() instanceof JailGoer) {
                    this.board = ((JailGoer) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                } else if (this.board.getTargetPlayer().getLocation() instanceof FreeParking) {
                    this.board = ((FreeParking) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                }
                else if (this.board.getTargetPlayer().getLocation() instanceof PropertySquares) {
                    this.board = ((PropertySquares) this.board.getTargetPlayer().getLocation()).takeAction(this.board);
                }
            }

            //Step 6: Check player's money to see who lose in this round

            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).getMoney() < 0 && !playerList.get(i).getStatus().equals("Retired")) {
                    playerList.get(i).setStatus("Retired");
                    this.board.deductPlayerCount(1);
                    System.out.println("Player "+playerList.get(i).getPlayerID()+" lost and is out of the game!");
                }
            }


            System.out.println("Press enter to continue...");

            // Reading data using readLine
            reader.readLine();


            //Step 7: Check Remaining Players
            // If number of remaining players is > 2, while loop continues, go to next player.
            // Otherwise, exit the while loop
            if (playerList.indexOf(this.board.getTargetPlayer()) == playerList.size() - 1) {
                this.board.setCurrentRound(this.board.getCurrentRound() + 1);
                this.board.setTargetPlayer(playerList.get(0));
            } else {
                this.board.setTargetPlayer(playerList.get(this.board.getTargetPlayer().getPlayerID() - 1 + 1));
            }
        }

        // When we have a winner:
        // If getPlayerCount = 1 OR getCurrentRound() > 100:
        if (this.board.getPlayerCount() == 1 || this.board.getCurrentRound() > 100) {
            this.board.printGameBoard();

            int highestValue = playerList.get(0).getMoney();
            int winnerPlayerID = 0;

            for (int i = 0; i < playerList.size(); i++) {
                if (playerList.get(i).getMoney() > highestValue) {
                    winnerPlayerID = playerList.get(i).getPlayerID();
                }
            }

            if (this.board.getPlayerCount() < 2) {
                System.out.println("Game over! Only 1 player is left. The winner is Player "+winnerPlayerID);
                System.out.println("Press enter to continue...");
                reader.readLine();
            } else if (this.board.getCurrentRound() > 100) {
                System.out.println("Game over! The game has exceeded 100 rounds. The winner is Player "+winnerPlayerID);
                System.out.println("Press enter to continue...");
                reader.readLine();
            }
        }
    }

    /*
    This method performs various functions to save the current instance of Monopoly game.
    This writes the current board object (this) to test.ser file saved in the INTELLIJ PROJECT DIRECTORY.
     */
    public void saveBoard() {
        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream("board.ser");
            ObjectOutputStream oout = new ObjectOutputStream(out);

            // write something in the file
            oout.writeObject(this.board);

            // close the stream
            oout.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    This method loads the previous instance of Monopoly game.
     */
    public void loadBoard() throws IOException, ClassNotFoundException {
        ObjectInputStream ois;
        ois = new ObjectInputStream(new FileInputStream("board.ser"));
        this.board = (Board) ois.readObject();
    }

    /**
     *  Roll the dice.
     * @return - ture if player saved the game and want to go back to the main menu. False otherwise.
     * @throws IOException - Input output Exception
     */
    public boolean promptRollDice() throws IOException {
        System.out.println("Current Player: Player "+ this.board.getTargetPlayer().getPlayerID());
        System.out.println("Press 0 to roll the dice!");
        System.out.println("Press 9 to save the game and exit!");

        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        String input = reader.readLine();
        boolean validInput = false;

        //Ask for input
        while (!validInput) {
            if (input.equals("0") || input.equals("9")) {
                validInput = true;
            } else {
                System.out.println("Only 0 or 9 is allowed. Please try again: ");
                input = reader.readLine();
            }
        }

        //Throw dice
        if (input.equals("0")) {
            this.board.getDiceObj().throwDice();
        }
        //Exit the game
        else if (input.equals("9")) {
            clearScreen();
            saveBoard();

            System.out.println("Save completed! Press Enter to go back to main menu.");
            reader.readLine();
            return true;
        }
    return false;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



}
