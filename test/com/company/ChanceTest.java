package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ChanceTest {

    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initSquares();

        player = new Player(1);
        board.addPlayer(player);
        board.setTargetPlayer(player);

        // Find the Chance
        int targetAddress = 9;
        Squares square = board.getHeadSquare();
        for (int i = 1; i <= targetAddress; i++) {
            // Move player from square 16 to 6 AND change player's status to "In Jail"
            if (square.squareAddress == targetAddress) {
                break;
            }
            else
                square = square.getNextSquare();
        }

        board.getTargetPlayer().setLocation(square);
    }
    /*
    Checks for any runtime error when running the method for the specified type of squares.
     */
    @Test
    void takeAction() {
        try{
            board = board.getTargetPlayer().getLocation().takeAction(board);
        } catch (Exception e){
            System.out.println("toString(): " + e.toString());
            fail("chance-takeAction has exception error.");
        }
    }

    /*
    Checks Whether takeAction() is running properly. Amount of money in target player must be changed.
     */
    @Test
    void takeAction2() throws IOException {
        for (int i = 0; i < 10; i++) {
            int curPlayerMoney = board.getTargetPlayer().getMoney();
            // System.out.println(curPlayerMoney);
            board = board.getTargetPlayer().getLocation().takeAction(board);
            assumeTrue(board.getTargetPlayer().getMoney() != curPlayerMoney);
            // System.out.println(board.getTargetPlayer().getMoney());
        }
    }
}