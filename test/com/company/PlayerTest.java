package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Console;


import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player1;
    Player player2;
    Player player3;

    /**
     * Set up the testing environment: three players are set up.
     */
    @BeforeEach
    void setUp() {
        // Set up three players:
        player1 = new Player(1);
        player1.setLocation(new GoHandler(1));
        player2 = new Player(2);
        player3 = new Player(3);
    }

    /**
     * Only three attributes can be got: "Free", "In Jail", or "Retired"
     */
    @Test
    void getStatusTest() {
        assertThat(player1.getStatus(), anyOf(is("Free"), is("In Jail"), is("Retired")));
    }


    /**
     *  Check whether PlayerJailCount is >= 0.
     */
    @Test
    void getPlayerJailCountTest() {
        assumeTrue(player1.getPlayerJailCount() >= 0);
    }

    /**
     * Check whether the return of getLocation != null.
     */
    @Test
    void getLocationTest() {
        assumeTrue(player1.getLocation() != null);
    }

    /**
     * Check whether the return of getMoney is integer.
     */
    @Test
    void getMoney() {
        assumeTrue(player1.getMoney() == (int)player1.getMoney());
    }

    /**
     * Check whether it is successfully set the corresponding field.
     */
    @Test
    void setStatusTest() {
        player1.setStatus("Free");
        player2.setStatus("In Jail");
        player3.setStatus("Retired");

        assertEquals("Free", player1.getStatus(),
                "The expected status is not got by the program.");
        assertEquals("In Jail", player2.getStatus(),
                "The expected status is not got by the program.");
        assertEquals("Retired", player3.getStatus(),
                "The expected status is not got by the program.");
    }

    /**
     * Check whether setPlayerJailCount is successfully set the corresponding field.
     * All integers which are >=0 are acceptable.
     */
    @Test
    void setPlayerJailCountTest() {
        player1.setPlayerJailCount(0);
        player2.setPlayerJailCount(40);
        player3.setPlayerJailCount(80000);

        assertEquals(0, player1.getPlayerJailCount(),
                "The expected status is not got by the program.");
        assertEquals(40, player2.getPlayerJailCount(),
                "The expected status is not got by the program.");
        assertEquals(80000, player3.getPlayerJailCount(),
                "The expected status is not got by the program.");
    }

    /**
     * Set 7 different squares by using setLocation() to test.
     */
    @Test
    void setLocationTest() {
        // Initialize 7 squares
        Squares chance = new Chance(1);
        Squares freeParking = new FreeParking(1);
        Squares goHandler = new GoHandler(1);
        Squares incomeTax = new IncomeTax(1);
        Squares inJailJustVisiting = new InJailJustVisiting(1);
        Squares jailGoer = new JailGoer(1);
        Squares propertySquares = new PropertySquares("testing", 9999, 111111, 1);

        // Assertions
        player1.setLocation(chance);
        assertEquals("class com.company.Chance", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(freeParking);
        assertEquals("class com.company.FreeParking", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(goHandler);
        assertEquals("class com.company.GoHandler", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(incomeTax);
        assertEquals("class com.company.IncomeTax", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(inJailJustVisiting);
        assertEquals("class com.company.InJailJustVisiting", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(jailGoer);
        assertEquals("class com.company.JailGoer", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
        player1.setLocation(propertySquares);
        assertEquals("class com.company.PropertySquares", player1.getLocation().getClass().toString(),
                "The expected status is not got by the program.");
    }

    /**
     * Check whether addMoney is successfully set the corresponding field.
     *  And the addition is run properly.
     */
    @Test
    void addMoneyTest() {
        player1.addMoney(4);
        player1.addMoney(4);
        player2.addMoney(40);
        player3.addMoney(80000);

        assertEquals(8, player1.getMoney(),
                "The expected status is not got by the program.");
        assertEquals(40, player2.getMoney(),
                "The expected status is not got by the program.");
        assertEquals(80000, player3.getMoney(),
                "The expected status is not got by the program.");
    }

    /**
     * Check whether addMoney is successfully set the corresponding field.
     *  And the Deduction is run properly.
     */
    @Test
    void deductMoneyTest() {
        player1.addMoney(4);
        player1.deductMoney(8);

        assertEquals(-4, player1.getMoney(),
                "The expected status is not got by the program.");
    }
}