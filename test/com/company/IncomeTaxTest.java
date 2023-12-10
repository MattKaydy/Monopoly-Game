package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

class IncomeTaxTest {

    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initSquares();

        player = new Player(1);
        board.addPlayer(player);
        board.setTargetPlayer(player);

        // Find the IncomeTax
        int targetAddress = 4;
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
            fail("incomeTax-takeAction has exception error.");
        }
    }
}