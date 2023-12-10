/*COMP3211 Course Project - Monopoly Game
Group 43

PropertySquares Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "PropertySquares" type on the game board.
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PropertySquares extends Squares {

    private Player playerOwned = null;
    private final String propertyName;
    private final int propertyPrice;
    private final int propertyRent;

    /*
    Construct PropertySquares with propertyName, propertyPrice and propertyRent
     */
    public PropertySquares(String name, int price, int rent, int squareAddress) {
        this.propertyName = name;
        this.propertyPrice = price;
        this.propertyRent = rent;
        super.squareAddress = squareAddress;
    }

    /*
    Return who owned the property square.
    */
    public Player getPlayerOwned() {
        return playerOwned;
    }

    /*
    Get the property price.
    */
    public int getPropertyPrice() {
        return this.propertyPrice;
    }

    /*
    Get the property name.
    */
    public String getPropertyName() {
        return this.propertyName;
    }

    /*
    Get the property name.
    */
    public int getPropertyRent() {
        return this.propertyRent;
    }

    /*
    Set which player owned specific propertySquares
    */
    public void setPlayerOwned(Player player) {
        if (player == null)
            throw new IllegalArgumentException("Player cannot be null");

        this.playerOwned = player;
    }

    /*
    Performs a series of operations for this type of square on the board.
    */
    public Board takeAction(Board board) throws IOException {

        Player curPlayer = board.getTargetPlayer();

        // Property is available for buying
        if (playerOwned == null) {
            // Asking input
            System.out.printf("Player %1d landed on %s. Property is available for sale.%n", curPlayer.getPlayerID(), this.propertyName);
            System.out.println("Press 0: Buy it.");
            System.out.println("Press 1: Do not buy it.");
            String buyOrNot;
            boolean boolBuyOrNot = false;
            do {
                // Enter data using BufferReader
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in));

                // Reading data using readLine
                buyOrNot = reader.readLine();

                if (buyOrNot.equals("0") || buyOrNot.equals("1")) {
                    boolBuyOrNot = true;
                } else {
                    System.out.println("Only 0 or 1 is allowed. Please try again:");
                }
            }
            while (!boolBuyOrNot);
            // Buy
            if (buyOrNot.equals("0")) {

                if (curPlayer.getMoney() < this.propertyPrice)
                    System.out.printf("Player %1d do not have enough money. Abort the purchase.%n", curPlayer.getPlayerID());
                else {
                    System.out.printf("Player %1d have bought Property %s.%n", curPlayer.getPlayerID(), this.propertyName);
                    curPlayer.deductMoney(propertyRent);
                    this.playerOwned = curPlayer;
                }

            }
        }
        // property is owned
        else {
            //curPlayer is NOT the owner
            if (!playerOwned.equals(curPlayer)) {
                // Deduct money from curPlayer and add money to owner
                System.out.printf("Player %1d has entered owned property. Giving HKD %2d to Player %1d%n", curPlayer.getPlayerID(), this.propertyRent, this.playerOwned.getPlayerID());
                curPlayer.deductMoney(propertyRent);
                playerOwned.addMoney(propertyRent);
            }
        }
        return board;
    }
}

