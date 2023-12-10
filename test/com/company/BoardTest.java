package com.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;
    Player player;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initSquares();

        player = new Player(1);
        board.addPlayer(player);
        board.setTargetPlayer(player);
    }

    //Checks if headSquare is the Go square (referred as the GoHandler subclass in this program).
    @Test
    void getHeadSquare() {
        Squares headSquare = new GoHandler(1); //In the implemented version this headSquare will be constructed.
        assertEquals(headSquare.getClass().toString(), board.getHeadSquare().getClass().toString());
    }

    //Checks if current round returned is a positive number.
    @Test
    void getCurrentRound() {
        assertTrue(board.getCurrentRound() >= 0);
    }

    //Checks if player count is within range 1 to 4
    @Test
    void getPlayerCount() {
        assertTrue(  1 >= board.getPlayerCount() && board.getPlayerCount() <= 4 );
    }


    @Test
    //Checks if getTargetPlayer() is INSIDE the PlayerList[] array and is not null.
    void getTargetPlayer() {
        assertNotNull(board.getTargetPlayer());
        boolean found = false;
        for (int i = 0; i <= board.getPlayerCount(); i++) {
            if (board.getTargetPlayer().equals(board.getPlayerList().get(i))) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    //Checks if the PlayerList is not null and only holds at most 4 objects (4 players)
    @Test
    void getPlayerList() {
        assertNotNull(board.getPlayerList());
        assertTrue(board.getPlayerList().size() <= 4);
    }

    //Make sure an exception occurs if current round is set as a negative number
    @Test
    void setCurrentRound() {
        assertThrows(Exception.class, () -> {
           board.setCurrentRound(-1);
        });
    }

    //Make sure there is no exception occurs when running deductPlayerCount().
    @Test
    void deductPlayerCount() {
        try{
            board.deductPlayerCount(1);
        } catch (Exception e){
            System.out.println("toString(): " + e.toString());
            fail("chance-takeAction has exception error.");
        }
    }

    // //Make sure an exception occurs if targetPlayer is null.
    @Test
    void setTargetPlayer() {
        assertThrows(Exception.class, () -> {
            board.setTargetPlayer(null);
        });
    }

    //Make sure no runtime error occurs with the initSquares() method.
    @Test
    void initSquares() {
        try {
            board.initSquares();

            Squares tempSquare = board.getHeadSquare();

            for (int i = 1; i <= 20; i++) {
                System.out.println("Address: " + tempSquare.getNextSquare().getSquareAddress() + " Type: " + tempSquare.getNextSquare().getClass());
                tempSquare = tempSquare.getNextSquare();
            }


        } catch(Exception e) {
            fail("initSquares method checking failed! Runtime error detected.");
        }


    }


    //Make sure an exception is thrown if a null player object is added.
    @Test
    void addPlayer() {
        assertThrows(Exception.class, () -> {
            board.addPlayer(null);
        });
    }

    //Make sure no runtime error occurs with the saveBoard() method.
    @Test
    void saveBoard() {
        try {
            saveBoard();
        } catch(Exception e) {
            fail("saveBoard method checking failed! Runtime error detected.");
        }
    }



    //Make sure no runtime error occurs with the printGameBoard() method.
    @Test
    void printGameBoard() {
        board.initSquares();
        Player player1Test = new Player(1);
        Player player2Test = new Player(2);
        player1Test.setLocation(board.getHeadSquare().getNextSquare());
        player2Test.setLocation(board.getHeadSquare());

        board.addPlayer(player1Test);
        board.addPlayer(player2Test);

       //System.out.println(player1Test.getLocation().getClass());

        //System.out.flush();

        try {
            board.printGameBoard();
        } catch(Exception e) {
            fail("printGameBoard method checking failed! Runtime error detected.");
        }



        //board.printGameBoard();
    }
}