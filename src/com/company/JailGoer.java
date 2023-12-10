/*COMP3211 Course Project - Monopoly Game
Group 43

JailGoer Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "JailGoer" type on the game board.
 */
package com.company;

public class JailGoer extends Squares {

    /*
   Construct JailGoer
    */
    public JailGoer(int squareAddress){
        super.squareAddress = squareAddress;
    }

    /*
    Performs a series of operations for this type of square on the board.
     */
    public Board takeAction(Board board) {
        Player curPlayer = board.getTargetPlayer();
        Squares square = board.getHeadSquare();

        int targetAddress = 6;
        for (int i = 1; i <= targetAddress; i++) {
            // Move player from square 16 to 6 AND change player's status to "In Jail"
            if (square.squareAddress == targetAddress) {
                curPlayer.setLocation(square);
                curPlayer.setStatus("In Jail");
                System.out.printf("Moved Player %1d to jail.%n", curPlayer.getPlayerID());
            }
            else
                square = square.getNextSquare();
        }
        return board;
    }


}
