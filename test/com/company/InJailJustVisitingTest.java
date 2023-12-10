package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InJailJustVisitingTest {

    InJailJustVisiting inJailJustVisiting = new InJailJustVisiting(1);

    /*
    Checks for any runtime error when running the method for the specified type of squares.
     */
    @Test
    void takeAction() {
        try{
            inJailJustVisiting.takeAction(new Board());
        } catch (Exception e){
            fail("InJailJustVisiting-takeAction has exception error.");
        }
    }
}