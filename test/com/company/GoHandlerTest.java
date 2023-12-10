package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

class GoHandlerTest {

    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initSquares();

        player = new Player(1);
        board.addPlayer(player);
        board.setTargetPlayer(player);

        board.getTargetPlayer().setLocation(board.getHeadSquare());
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
            fail("goHandler-takeAction has exception error.");
        }
    }
}