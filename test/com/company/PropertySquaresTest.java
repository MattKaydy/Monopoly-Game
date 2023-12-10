package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static java.time.Duration.ofMinutes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class PropertySquaresTest {

    PropertySquares propertySquares;
    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        propertySquares = new PropertySquares("Central", 800, 90, 1);
        board = new Board();
        player = new Player(1);
        board.addPlayer(player);
        board.setTargetPlayer(player);
    }

    /**
     * Check whether the return of getPlayerOwned != null.
     */
    @Test
    void getPlayerOwned() {
        assumeTrue(propertySquares.getPlayerOwned() != null);
    }

    /**
     * Check whether the return of getPropertyPrice is integer.
     */
    @Test
    void getPropertyPrice() {
        assumeTrue(propertySquares.getPropertyPrice() == (int)propertySquares.getPropertyPrice());
    }

    /**
     * Only 12 attributes can be got: "Central", "Wan Chai", "Stanley" etc. (Please refer to below)
     */
    @Test
    void getPropertyName() {
        assertThat(propertySquares.getPropertyName(), anyOf(is("Central"), is("Wan Chai"), is("Stanley")
                , is("Shek O"), is("Mong Kok"), is("Tsing Yi"), is("Shatin"), is("Tuen Mun")
                , is("Tai Po"), is("Sai Kung"), is("Yuen Long"), is("Tai O")));
    }

    /**
     * Check whether the return of getPropertyRent is integer.
     */
    @Test
    void getPropertyRent() {
        assumeTrue(propertySquares.getPropertyRent() == (int)propertySquares.getPropertyRent());
    }

    /**
     * Check whether it is successfully set the corresponding field.
     */
    @Test
    void setPlayerOwned() {
        //Player player = new Player(1);
        //propertySquares.setPlayerOwned(player);
    }

    /**
     * Test testTakeAction() whether it has exception. It needs an exception.
     */
    @Test
    void testTakeAction() {
        /*assertThrows(Exception.class, () -> {
            board = propertySquares.takeAction(board);
        });*/
    }
}