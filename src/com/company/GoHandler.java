/*COMP3211 Course Project - Monopoly Game
Group 43

GoHandler Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "GoHandler" type on the game board.
 */
package com.company;

public class GoHandler extends Squares {

    /*
   Construct GoHandler
    */
    public GoHandler(int squareAddress){
        super.squareAddress = squareAddress;
    }

    /*
    Performs a series of operations for this type of square on the board.
    GoHandler will add money.
     */
    public Board takeAction(Board board) {
        //Add money to current player:
        System.out.println("You passed Go! Adding HKD 1500 to Player "+board.getTargetPlayer().getPlayerID()+"...");
        board.getTargetPlayer().addMoney(1500);

        return board;
    }

}
