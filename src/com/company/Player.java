/*COMP3211 Course Project - Monopoly Game
Group 43

Board Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This class represents the data of EACH player in the game.
 */
package com.company;

import java.io.Serializable;

public class Player implements Serializable {

    private String status;
    private int playerJailCount;
    private Squares location;
    private int money;
    private int playerID;

    /*
    Construct a player. Set status == "Free", playerJailCount == 0, location == First square and money == 0.
     */
    public Player(int ID) {
        this.playerID = ID;
        this.money = 1500;
        this.status = "Free";
        this.playerJailCount = 0;
    }

    /*
    Returns the status of the current player. Status includes "Free", "In Jail", or "Retired".
     */
    public String getStatus () {
        return this.status;
    }

    /*
    Returns the playerJailCount value. A playerJailCount keeps track on how many "loops" a player has been in jail.
     */
    public int getPlayerJailCount() {
        return this.playerJailCount;
    }

    /*
    Returns the current square the player is on in the board.
     */
    public Squares getLocation() {
        return this.location;
    }

    /*
    Returns the value of money the player has.
     */
    public int getMoney() {
        return this.money;
    }

    /*
    Sets the status of the current player. A status can be "Free", "In Jail", or "Retired".
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
    Sets the PlayerJailCount of the player.
     */
    public void setPlayerJailCount(int count) {
        this.playerJailCount = count;
    }

    /*
    Sets the location Squares object of the player. This defines which location on the game board the player is on.
     */
    public void setLocation(Squares locationObj) {
        this.location = locationObj;
    }

    /*
    Adds money by the stated amount for the player.
     */
    public void addMoney(int amount) {
        this.money = this.money + amount;
    }

    /*
    Deducts money by the stated amount for the player.
     */
    public void deductMoney(int amount) {
        this.money = this.money - amount;
    }

    public int getPlayerID() {
        return this.playerID;
    }


}
