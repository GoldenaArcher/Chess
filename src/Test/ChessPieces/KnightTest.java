package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Knight;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static org.junit.Assert.*;

/**
 * @author Lu Han
 */

public class KnightTest {
    @Before
    public void setUp(){}

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        Knight knightBlack = new Knight(board, BLACK);
        Knight knightWhite = new Knight(board, WHITE);

        assertEquals(BLACK, knightBlack.getColor());
        assertEquals(WHITE, knightWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        Knight Knight = new Knight(board, BLACK);
        board.placePiece(Knight, "a7");
        assertEquals("a7", Knight.getPosition());
//        Test with some edge cases
        board.placePiece(Knight, "a10");
        assertTrue(!"a10".equals(Knight.getPosition()));
        board.placePiece(Knight, "a");
        assertTrue(!"a".equals(Knight.getPosition()));
        board.placePiece(Knight, "i8");
        assertTrue(!"f8".equals(Knight.getPosition()));
    }

    @Test
    public void setPosition() {
        ChessBoard localBoard = new ChessBoard();
        Knight localPawn = new Knight(localBoard, BLACK);

        try {
            localPawn.setPosition("a7");
            assertEquals(localPawn.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Knight knight = new Knight(localBoard, BLACK);
        knight.setPosition("a10");
        knight.setPosition("a0");
        knight.setPosition("B1");
        knight.setPosition("i0");
    }

//    @TODO test knight's legal moves
//    need to test three pieces at top
    @Test
    public void legalMovesTop() {
        ChessBoard board = new ChessBoard();
        Knight knightA8 = new Knight(board, BLACK);
        board.placePiece(knightA8, "a8");

    }

}