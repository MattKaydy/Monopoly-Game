package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DicesTest {
    Dices dices;

    @BeforeEach
    void setUp() {
        dices = new Dices();
    }

    //Make sure no runtime error occurs when dices are thrown. A manual exception is also coded in the class file making sure Dice0 and Dice1 are within range 1-4
    @Test
    void throwDice() {
        try {
            dices.throwDice();
        } catch(Exception e) {
            fail("Dices method checking failed! Runtime error detected.");
        }
    }

    // Check the dices.getSum() is in the range.
    @Test
    void throwDice2() {
        dices.throwDice();
        System.out.println(dices.getSum());
        assertTrue(2 <= dices.getSum() && dices.getSum() <= 8);
    }

    //Make sure the result cannot be null.
    @Test
    void isEqual() {
        assertNotNull(dices.isEqual());
    }

    //Make sure the sum is within ranges 2 (Dice0 = 1, Dice1 = 1) to 8 (Dice0 = 4, Dice1 = 4).
    @Test
    void getSum() {
        assertTrue(dices.getSum() >= 2 && dices.getSum() <= 8);
    }
}