package Test;

import Main.Operation.ChessBoard;
import Main.ChessPieces.*;
import Main.Exception.IllegalMoveException;
import Main.Exception.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author Lu Han
 */
public class ChessBoardTest {
    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initialize();
    }

    @Test
    public void placePiece() {
        Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK);
        Rook rookWhite = new Rook(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(rook, "a3"));
        assertTrue(!chessBoard.placePiece(rook, "a3"));
        assertTrue(chessBoard.placePiece(rookWhite, "a3"));
        assertTrue(!chessBoard.placePiece(rookWhite, "3"));
        assertTrue(!chessBoard.placePiece(rookWhite, "G10"));
        assertFalse(chessBoard.placePiece(rookWhite, "A10"));
    }

    //    in this case, all passed parameters are valid, so there should not be a test that fails
    @Test
    public void getPiece() {
        Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK);
        try {
            assertNull(chessBoard.getPiece("a3"));
            chessBoard.placePiece(rook, "a3");
            assertEquals(rook, chessBoard.getPiece("a3"));
            assertNull(chessBoard.getPiece("a4"));
        } catch (IllegalPositionException e) {
            System.out.println("Fail on the getPiece process in testcase.");
        }
    }

    //    also, have to test if the proper exceptions have been thrown by getPiece methods
    @Test(expected = IllegalPositionException.class)
    public void getPieceIllegalPosition() throws IllegalPositionException {
        chessBoard.getPiece("a10"); // 1st test to test if the length is different
    }

    @Test(expected = IllegalPositionException.class)
    public void getPieceIllegalPosition2() throws IllegalPositionException {
        chessBoard.getPiece("A1");  // test when uppercase is given
    }

    @Test(expected = IllegalPositionException.class)
    public void getPieceIllegalPosition3() throws IllegalPositionException {
        chessBoard.getPiece("h9");  // test when piece is placed outside of the board (range isn't right
    }

    @Test(expected = IllegalPositionException.class)
    public void getPieceIllegalPosition4() throws IllegalPositionException {
        chessBoard.getPiece("l9");  // test a wrong string
    }

    @Test(expected = IllegalPositionException.class)
    public void getPieceIllegalPosition5() throws IllegalPositionException {
        chessBoard.getPiece("j1");  // test another different range
    }

    private boolean testOtherPiece(ChessPiece piece, int column) {
        switch (column) {
            case 0:
            case 7:
                return piece instanceof Main.ChessPieces.Rook;
            case 1:
            case 6:
                return piece instanceof Main.ChessPieces.Knight;
            case 2:
            case 5:
                return piece instanceof Main.ChessPieces.Bishop;
            case 4:
                return piece instanceof Main.ChessPieces.King;
            default:
                return piece instanceof Main.ChessPieces.Queen;
        }
    }

    @Test
    public void initialize() {
        chessBoard.initialize();

        ChessPiece.Color black = ChessPiece.Color.BLACK;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        String position;
        try {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    position = "" + (char) ('a' + column) + (7 - row + 1);
                    ChessPiece piece = chessBoard.getPiece(position);
                    if (row == 0) {
                        assertTrue(testOtherPiece(piece, column) && piece.getColor() == black);
                    } else if (row == 1) {
                        assertTrue(piece instanceof Main.ChessPieces.Pawn && piece.getColor() == black);
                    } else if (row == 6) {
                        assertTrue(piece instanceof Main.ChessPieces.Pawn && piece.getColor() == white);
                    } else if (row == 7) {
                        assertTrue(testOtherPiece(piece, column) && piece.getColor() == white);
                    }
                }
            }
        } catch (IllegalPositionException e) {
            System.out.println("Fail on the initialization process in testcase.");
        }
    }

    /*
    @Test       // at this time, the board has been initialized
    public void move() throws IllegalMoveException {
//        move white pieces, and try to scatter moved pieces if possible
        chessBoard.move("g2", "g4");
        chessBoard.move("g1", "f3");
        chessBoard.move("h1", "g1");
        chessBoard.move("b2", "b4");
        chessBoard.move("b1", "c3");
        chessBoard.move("e2", "e4");
        chessBoard.move("g1", "g3");
        chessBoard.move("g3", "h3");
        chessBoard.move("c1", "a3");
        chessBoard.move("c3", "d5");
        chessBoard.move("d1", "b1");
        chessBoard.move("b1", "b3");
        chessBoard.move("b3", "c4");
//        move black pieces, and try to scatter moved pieces if possible
        chessBoard.move("d7", "d6");
        chessBoard.move("h7", "h5");
        chessBoard.move("g8", "f6");
        chessBoard.move("b7", "b5");
        chessBoard.move("b8", "c6");
        chessBoard.move("d8", "d7");
        chessBoard.move("c8", "a6");
        chessBoard.move("e7", "e6");
        chessBoard.move("h8", "h6");
//        now, try to do some capture
        chessBoard.move("e6", "d5");
        chessBoard.move("e4", "d5");
        chessBoard.move("f6", "g4");
        chessBoard.move("c4", "g4");
        chessBoard.move("c6", "b4");
        chessBoard.move("h3", "h5");
        chessBoard.move("h6", "h5");
        chessBoard.move("g4", "h5");
        chessBoard.move("h5", "f7");
        chessBoard.move("f7", "e8");    // should expect to see a game over notification
    }

    //    to test the illegal moves that could occurred in the process of game
    @Test(expected = IllegalMoveException.class)
    public void moveIllegal1() throws IllegalMoveException {
        chessBoard.initialize();
        System.out.println(chessBoard.toString());
        chessBoard.move("a8", "a7");    // move piece to a block position - to position is occupied - castle
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal2() throws IllegalMoveException {
        chessBoard.move("g8", "e7");    // move piece to illegal position, not in knight's move pattern
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal3() throws IllegalMoveException {
        chessBoard.move("d1", "d6");    // queen's path has a same color piece to block the move
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal4() throws IllegalMoveException {
        chessBoard.move("c3", "c4");    // from position is empty
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal5() throws IllegalMoveException {
        chessBoard.move("e1", "e0");    // king move outside of the board
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal6() throws IllegalMoveException {
        chessBoard.move("f2", "g3");    // pawn try to capture, but there's no piece on captured position
    }

    @Test(expected = IllegalMoveException.class)
    public void moveIllegal7() throws IllegalMoveException {
        chessBoard.move("c1", "a3");    // move Bishop over to blocked path
    }
    */
}