/*COMP3211 Course Project - Monopoly Game
Group 43

Chance Sub-Class of Squares Class

This is an abstraction of code components, as required by section 1 in the API document for the group's submission
of the Monopoly game. The public methods' name, parameters are defined in various classes. Comments are included for explanation
of the methods.

This is a SUBCLASS of superclass Squares and represent squares with "Chance" type on the game board.
 */
package com.company;

public class Chance extends Squares {


    /*
    Construct Chance
     */
    public Chance(int squareAddress){
        super.squareAddress = squareAddress;
    }

    /*
    Performs a series of operations for this type of square on the board.
     */
    public Board takeAction(Board board) {
        Player curPlayer = board.getTargetPlayer();
        int money = 0;
        if (gain(board)) {
            money = gainMoney();
            System.out.printf("Player landed on Chance! Adding $%3d to player %1d%n", money, curPlayer.getPlayerID());
            curPlayer.addMoney(money);
        }
        else {
            money = lossMoney();
            System.out.printf("Player landed on Chance! Deducting $%3d from player %1d%n", money, curPlayer.getPlayerID());
            curPlayer.deductMoney(lossMoney());
        }

        return board;
    }

    /**
     * Private method for takeAction(). In the first three round, player shall always gain money.
     * @param board - The game board.
     * @return - return true if player gain money. Otherwise, player loss money.
     */
    private boolean gain(Board board) {
        // 0 == loss. 1 == gain
        int min = 0;
        int max = 1;
        int dice;

        if (board.getCurrentRound() > 3)
            dice = (int)Math.floor(Math.random()*(max-min+1)+min);
        else
            dice = 1;
        return dice == 1;
    }

    /**
     *  How much money that player gains.
     * @return - Total number of money which player gains.
     */
    private int gainMoney() {
        int min = 1;
        int max = 20;

        int multiplier = (int)Math.floor(Math.random()*(max-min+1)+min);
        return 10 * multiplier;
    }

    /**
     * How much money that player loss.
     * @return - Total number of money which player loss.
     */
    private int lossMoney() {
        int min = 1;
        int max = 30;

        int multiplier = (int)Math.floor(Math.random()*(max-min+1)+min);
        return 10 * multiplier;
    }
}
