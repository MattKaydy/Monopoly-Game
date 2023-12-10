package com.company;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();

    //Tests for any runtime error in running the main method.
    //Since main() is the first method to be executed when the program boots, also check if the Java version is correct.
    //(In this project, we specified to use Java version 11.0.13. Any version that's before and after will face dependency issues.
    @Disabled("Disabled.")
    @Test
    void main() {
        try {
            String[] input = {""};
            game.main(input);

        } catch(Exception e) {
            fail("Void method checking failed! Runtime error detected.");
        }
       assertEquals(System.getProperty("java.version"),"11.0.13"); //Check if Java version is 11.0.13
    }

    //Tests for any runtime error in running the mainMenuHandler method.
    @Disabled("Disabled.")
    @Test
    void mainMenuHandler() {
        try {
            game.mainMenuHandler();
        } catch(Exception e) {
            fail("Void method checking failed! Runtime error detected.");
        }
    }

    //Tests for any runtime error in running the gameManager method.
    @Disabled("Disabled.")
    @Test
    void gameManager() {
        try {
            game.gameManager();
        } catch(Exception e) {
            fail("Void method checking failed! Runtime error detected.");
        }
    }

    // loadBoard() will throw Exception.
    @Test
    void loadBoard() {
        assertThrows(Exception.class, () -> {
            game.loadBoard();
        });
    }
}