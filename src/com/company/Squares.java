/*COMP3211 Course Project - Monopoly Game
Group 43

Board Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This Squares class is a SUPERCLASS and its sub classes are Change, IncomeTax, InJailJustVisiting, JailGoer,
FreeParking, GoHandler, PropertySquares. This class handles general events for effects related to the squares on the game board.
It represents EACH SQUARES on the game board.
 */
package com.company;

import java.io.IOException;
import java.io.Serializable;

abstract public class Squares implements Serializable {

    int squareAddress;
    private Squares nextSquare;


    /*
    Returns the nextSquare object representing the next square on the game board.
     */
    public Squares getNextSquare() {
        return nextSquare;
    }

    /*
    Sets its neighbor square object. This should only be done in the initialization phase by the Board class.
     */
    public void setNextSquare(Squares nextSquare) {
        this.nextSquare = nextSquare;
    }

    public void setSquareAddress(int address) {
        //To do: implement input checking 1-20;
        this.squareAddress = address;
    }

    public int getSquareAddress() {
       return this.squareAddress;
    }

    public abstract Board takeAction(Board board) throws IOException;
}
