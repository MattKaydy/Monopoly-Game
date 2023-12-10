/*COMP3211 Course Project - Monopoly Game
Group 43

Dices Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This class represents A SET OF DICES. There are two dices of four sides in this game.
 */
package com.company;

import java.io.Serializable;

public class Dices implements Serializable {

    private int dice0;
    private int dice1;

    public Dices() {
        dice0 = 1;
        dice1 = 1;
    }
    /*
    Throws a set of dices by generating a random value from 1-4 for the two dice variables (which will be represented by dice0 and dice1
    and be private variables).
     */
    public void throwDice() {
        int min = 1;
        int max = 4;

        this.dice0 = (int)Math.floor(Math.random()*(max-min+1)+min);
        this.dice1 = (int)Math.floor(Math.random()*(max-min+1)+min);

        if (this.dice0 < 1 || this.dice0 > 4)
            throw new IllegalArgumentException("Error: Dice0 values generated are out of range values 1-4");
        if (this.dice1 < 1 || this.dice1 > 4)
            throw new IllegalArgumentException("Error: Dice1 values generated are out of range values 1-4");
    }

    /*
    Determines if two dices' values are equal and returns the result. This is used for determining if the player matches the condition of
    getting out of jail (two die have to be of same value/same side facing up) for the gameManager() method for the Game class.
     */
    public boolean isEqual() {
        return (dice0 == dice1);
    }

    /*
    Calculates the sum of two dices value and returns it. This is used for determining how many steps a player's token should walk, depending
    on the sum of the two rolled dices, by the gameManager() method for the Game class.
     */
    public int getSum() {
        return dice0 + dice1;
    }

    public int getDice0() {
        return this.dice0;
    }

    public int getDice1() {
        return this.dice1;
    }
}
