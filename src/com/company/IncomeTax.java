/*COMP3211 Course Project - Monopoly Game
Group 43

IncomeTax Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "IncomeTax" type on the game board.
 */
package com.company;

public class IncomeTax extends Squares {

    /*
   Construct IncomeTax
    */
    public IncomeTax(int squareAddress){
        super.squareAddress = squareAddress;
    }

    /*
    Performs a series of operations for this type of square on the board.
     */
    public Board takeAction(Board board) {

        Player curPlayer = board.getTargetPlayer();

        double moneyDeducted = (double)curPlayer.getMoney() * 0.1;
        curPlayer.deductMoney((int)moneyDeducted);

        System.out.println("You landed on Income Tax. Deducting HKD" + (int)moneyDeducted + " from Player " + curPlayer.getPlayerID() +"...");

        return board;
    }


}
