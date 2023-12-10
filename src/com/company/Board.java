/*COMP3211 Course Project - Monopoly Game
Group 43

Board Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This class represents the game board and handles functions such as initializing a new round of game and handles player information
of a round of the game.
 */
package com.company;

import java.io.*;
import java.util.ArrayList;

public class Board implements Serializable {


    private Squares headSquare;
    private int currentRound;
    private ArrayList<Player> playerList;
    private int playerCount;
    private Player targetPlayer;
    private Dices diceObj;
    private int targetPlayerIndex;

    public Board() {
        this.headSquare = new GoHandler(1);
        this.currentRound = 1;
        this.playerList = new ArrayList<Player>();
        this.playerCount = 0;
        this.diceObj = new Dices();
    }

    public Dices getDiceObj() {
        return this.diceObj;
    }
    /*
    Returns the headSquare object. This object is essentially the object representing Square 1 and used for initializing
    the data structure of the squares in the game board.
     */
    public Squares getHeadSquare() {
        return this.headSquare;
    }

    /*
    Returns the currentRound number.
     */
    public int getCurrentRound() { return this.currentRound; }

    /*
    Returns the playerCount (how many players in this round).
     */
    public int getPlayerCount() {
        return this.playerCount;
    }

    /*
    Returns the targetPlayer object for other methods. This object identifies which player is ROLLING THE DICE currently.
    The player rolling the dice is the "target" of a sequence of game logic operations.
     */
    public Player getTargetPlayer () {
        return this.targetPlayer;
    }

    /*
    Return a list of current player objects.
     */
    public ArrayList<Player> getPlayerList() {
        return this.playerList;
    }
    /*
    Sets the number of currentRound variable.
     */
    public void setCurrentRound(int currentRound) {
        if (currentRound < 0)
            throw new IllegalArgumentException("setCurrentRound cannot be a negative number");

        this.currentRound = currentRound;
    }

    /*
    Deducts the current number of players in a round by count.
     */
    public void deductPlayerCount (int count)  {
        if (count <= 0)
            throw new IllegalArgumentException("deductPlayerCount should be negative number");

        this.playerCount = this.playerCount - count;

    }

    public void setTargetPlayer(Player targetPlayer) {
        if (targetPlayer == null)
            throw new IllegalArgumentException("targetPlayer cannot be null");
        this.targetPlayer = targetPlayer;
    }

    /*
        This method initializes the data structure of the "Squares" objects, representing the squares on the game board.

        The Squares objects uses a linked list data structure to keep track on its next square, simulating a circle network of nodes.
        In this method, we create a for loop from 1 to 20 times. For each time, we create a new Squares object and define its next Squares object,
        hence forming a network of linked Squares object. A Squares class has a lot of subclasses catagorized by the square type on the board.
        Their neighbors should be defined with the appropiate subclasses depending on the square's attribute on the board.
        (a.ka What type of square is the object representing)
         */
    public void initSquares() {

        Squares tempSquare = headSquare;

        for (int address = 1; address <= 20; address++) {
            //System.out.println("Address:"+address);
            switch(address){

                case 1:
                    tempSquare.setNextSquare(new PropertySquares("Central", 800, 90, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 2:
                    tempSquare.setNextSquare(new PropertySquares("Wan Chai", 700, 65, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 3:
                    tempSquare.setNextSquare(new IncomeTax(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 4:
                    tempSquare.setNextSquare(new PropertySquares("Stanley", 600, 60, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 5:
                    tempSquare.setNextSquare(new InJailJustVisiting(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 6:
                    tempSquare.setNextSquare(new PropertySquares("Shek O", 400, 10, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 7:
                    tempSquare.setNextSquare(new PropertySquares("Mong Kok", 500, 40, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 8:
                    tempSquare.setNextSquare(new Chance(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 9:
                    tempSquare.setNextSquare(new PropertySquares("Tsing Yi", 400, 15, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 10:
                    tempSquare.setNextSquare(new FreeParking(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 11:
                    tempSquare.setNextSquare(new PropertySquares("Shatin", 700, 75, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 12:
                    tempSquare.setNextSquare(new Chance(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 13:
                    tempSquare.setNextSquare(new PropertySquares("Tuen Mun", 400, 20, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 14:
                    tempSquare.setNextSquare(new PropertySquares("Tai Po", 500, 25, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 15:
                    tempSquare.setNextSquare(new JailGoer(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 16:
                    tempSquare.setNextSquare(new PropertySquares("Sai Kung", 400, 10, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 17:
                    tempSquare.setNextSquare(new PropertySquares("Yuen Long", 400, 25, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 18:
                    tempSquare.setNextSquare(new Chance(address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 19:
                    tempSquare.setNextSquare(new PropertySquares("Tai O", 600, 25, address+1));
                    tempSquare = tempSquare.getNextSquare();
                    continue;
                case 20:
                    tempSquare.setNextSquare(headSquare);
                    tempSquare = tempSquare.getNextSquare();
                    continue;
            }
        }
    }

    /*
    Adds a new player to the list of players
     */
    public void addPlayer(Player newPlayer) {
        if (newPlayer == null)
            throw new IllegalArgumentException("newPlayer cannot be null");

        this.playerList.add(newPlayer);
        this.playerCount = this.playerCount + 1;
    }


    //ClearScreen()
    //Print the squares according to the linkedList
    //Update the player's location by using getLocation() (and getSquaresAddress()) from the Players.
    //Print (Refresh) the game board and update each player token's position
    public void printGameBoard() {

        String[] playerStatuses = new String[6];
        Squares[] playerLocations = new Squares[6];
        int[][] boardAddressSequences = new int[][] {{11,12,13,14,15,16},{10,-1,-1,-1,-1,17},{9,-1,-1,-1,-1,18},{8,-1,-1,-1,-1,19},{7,-1,-1,-1,-1,20},{6,5,4,3,2,1}};

        for (int i = 0; i < playerList.size() && playerList != null; i++) {
            Squares playerLocationObj = playerList.get(i).getLocation();
            String playerStatusObj = playerList.get(i).getStatus();
            int targetPlayerID = playerList.get(i).getPlayerID() - 1;
            playerLocations[targetPlayerID] = playerLocationObj;
            playerStatuses[targetPlayerID] = playerStatusObj;
        }

        clearScreen();
        System.out.println("Dice 1: "+this.diceObj.getDice0()+" Dice 2: "+this.diceObj.getDice1());

        // Print leaderboard table
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getStatus() == "Retired")
                System.out.println("Player "+(i+1)+": Retired");
            else
                System.out.println("Player "+(i+1)+": HKD "+(playerList.get(i).getMoney()));
        }


        // Print board

        System.out.println("|---------------|---------------|---------------|---------------|---------------|---------------|");


        for (int j = 0; j < boardAddressSequences.length; j++) {
            for (int k = 1; k <= 5; k++) {
                //Step 1: Print the TOP BAR
                if (k == 1) {
                    for (int l = 0; l < boardAddressSequences[j].length; l++) {

                        if (boardAddressSequences[j][l] != -1) {

                            //Find the square with the corresponding address
                            Squares targetSquare = headSquare;

                            while (targetSquare.squareAddress != boardAddressSequences[j][l]) {
                                targetSquare = targetSquare.getNextSquare();
                            }

                            if (targetSquare instanceof Chance) {
                                System.out.print("| CHANCE        ");
                            } else if (targetSquare instanceof IncomeTax) {
                                System.out.print("| INCOME TAX    ");
                            } else if (targetSquare instanceof InJailJustVisiting) {
                                System.out.print("| IN JAIL       ");
                            } else if (targetSquare instanceof JailGoer) {
                                System.out.print("| GO TO JAIL    ");
                            } else if (targetSquare instanceof FreeParking) {
                                System.out.print("| FREE PARKING  ");
                            } else if (targetSquare instanceof GoHandler) {
                                System.out.print("| GO            ");
                            } else if (targetSquare instanceof PropertySquares) {
                                String propertyName = ((PropertySquares) targetSquare).getPropertyName();
                                //Add property owned indicator
                                //System.out.println(((PropertySquares) targetSquare).getPlayerOwned());
                                if (((PropertySquares) targetSquare).getPlayerOwned() != null) {
                                    String propertyIndicator = " ["+(((PropertySquares) targetSquare).getPlayerOwned().getPlayerID()+"]");
                                    propertyName = propertyName + propertyIndicator;
                                }
                                String blankSpaces = new String(new char[(14 - propertyName.length())]).replace('\0', ' ');
                                System.out.print("| " + propertyName + blankSpaces);
                            }

                        } else if (boardAddressSequences[j][l] == -1 && boardAddressSequences[j][l-1] != -1){
                            System.out.print("|               ");
                        }
                        else {
                            System.out.print("                ");
                        }

                        if (l == boardAddressSequences[j].length - 1)
                            System.out.print("|");
                    }

                    System.out.println();

                }
                //Printing second bar
                if (k == 2) {
                    for (int l = 0; l < boardAddressSequences[j].length; l++) {
                        if (boardAddressSequences[j][l] != -1) {
                            //Find the square with the corresponding address
                            Squares targetSquare = headSquare;
                            while (targetSquare.squareAddress != boardAddressSequences[j][l]) {
                                targetSquare = targetSquare.getNextSquare();
                            }

                            if (targetSquare instanceof PropertySquares) {
                                String propertyPrice = String.valueOf(((PropertySquares) targetSquare).getPropertyPrice());
                                String blankSpaces = new String(new char[(11 - propertyPrice.length())]).replace('\0', ' ');
                                System.out.print("| HKD" + propertyPrice + blankSpaces);
                            }
                            //In Jail Just Visiting Special Case - Check who is jailed
                            else if (targetSquare instanceof InJailJustVisiting) {
                                System.out.print("| ");
                                for (int targetIndex = 0; targetIndex < playerLocations.length; targetIndex++) {
                                    if (playerStatuses[targetIndex] == "In Jail") {
                                        System.out.print((targetIndex + 1) + " ");
                                    } else {
                                        System.out.print("  ");
                                        //System.out.print((targetIndex + 1) + " ");
                                    }
                                }
                                System.out.print("  ");
                            } else if (targetSquare instanceof GoHandler) {
                                System.out.print("| <-------      ");
                            }

                             else {
                                System.out.print("|               ");
                            }
                        }

                        else if (boardAddressSequences[j][l] == -1 && boardAddressSequences[j][l-1] != -1){
                            System.out.print("|               ");
                        }
                        else {
                            System.out.print("                ");
                        }
                        if (l == boardAddressSequences[j].length - 1)
                            System.out.print("|");
                    }
                    System.out.println();
                }
                //Printing third bar
                if (k == 3) {

                    for (int l = 0; l < boardAddressSequences[j].length; l++) {
                        if (boardAddressSequences[j][l] != -1) {
                            //Find the square with the corresponding address
                            Squares targetSquare = headSquare;
                            while (targetSquare.squareAddress != boardAddressSequences[j][l]) {
                                targetSquare = targetSquare.getNextSquare();
                            }

                            if (targetSquare instanceof InJailJustVisiting) {
                                System.out.print("| JUST VISITING ");
                            } else {
                                System.out.print("| ");

                                for (int targetIndex = 0; targetIndex < playerLocations.length; targetIndex++) {

                                    if (playerLocations[targetIndex] != null && playerLocations[targetIndex].squareAddress == boardAddressSequences[j][l] && playerStatuses[targetIndex] != "Retired") {
                                        System.out.print((targetIndex + 1) + " ");
                                    } else {
                                        System.out.print("  ");
                                        //System.out.print((targetIndex + 1) + " ");
                                    }
                                }
                                System.out.print("  ");
                            }

                        } else if (boardAddressSequences[j][l] == -1 && boardAddressSequences[j][l-1] != -1){
                            System.out.print("|               ");
                        }
                        else {
                            System.out.print("                ");
                        }
                        if (l == boardAddressSequences[j].length - 1)
                            System.out.print("|");
                    }
                    System.out.println();
                }
                //Printing fourth bar
                if (k == 4) {
                    for (int l = 0; l < boardAddressSequences[j].length; l++) {
                        if (boardAddressSequences[j][l] != -1) {
                            //Find the square with the corresponding address
                            Squares targetSquare = headSquare;
                            while (targetSquare.squareAddress != boardAddressSequences[j][l]) {
                                targetSquare = targetSquare.getNextSquare();
                            }

                            if (targetSquare instanceof InJailJustVisiting) {
                                System.out.print("| ");

                                for (int targetIndex = 0; targetIndex < playerLocations.length; targetIndex++) {
                                    if (playerLocations[targetIndex] != null && playerLocations[targetIndex].squareAddress == boardAddressSequences[j][l] && playerStatuses[targetIndex] != "In Jail" && playerStatuses[targetIndex] != "Retired") {
                                        System.out.print((targetIndex + 1) + " ");
                                    } else {
                                        System.out.print("  ");
                                    }
                                }
                                System.out.print("  ");

                            } else {
                                System.out.print("|               ");
                            }



                        } else if (boardAddressSequences[j][l] == -1 && boardAddressSequences[j][l-1] != -1){
                            System.out.print("|               ");
                        }
                        else {
                            System.out.print("                ");
                        }

                        if (l == boardAddressSequences[j].length - 1)
                            System.out.print("|");
                    }
                    System.out.println();
                }
                //Printing bottom bar
                if (k == 5) {
                    if (j == 0 || j == 4 || j == 5)
                        System.out.println("|---------------|---------------|---------------|---------------|---------------|---------------|");
                    else {
                        System.out.println("|---------------|                                                               |---------------|");
                    }
                }
            }

        }

    }



    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
