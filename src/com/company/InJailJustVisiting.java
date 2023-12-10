/*COMP3211 Course Project - Monopoly Game
Group 43

InJailJustVisting Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "InJailJustVisting" type on the game board.
 */
package com.company;

public class InJailJustVisiting extends Squares {

    /*
   Construct InJailJustVisiting
    */
    public InJailJustVisiting(int squareAddress){
        super.squareAddress = squareAddress;
    }

    /*
    Performs a series of operations for this type of square on the board.
     */
    public Board takeAction(Board board) {
        System.out.println("You landed on Just Visiting! No action is taken...");
        return board;
    }

}
