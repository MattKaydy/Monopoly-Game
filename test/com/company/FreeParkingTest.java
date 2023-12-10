package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.*;

class FreeParkingTest {

    FreeParking freeParking = new FreeParking(1);

    /*
    Checks for any runtime error when running the method for the specified type of squares.
     */
    @Test
    void takeAction() {
        Board board = new Board();
        try{
            board = freeParking.takeAction(board);
        } catch (Exception e){
            fail("FreeParking-takeAction has exception error.");
        }
    }
}