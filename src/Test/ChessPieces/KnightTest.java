package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Knight;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

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

//    @TODO test knight's legal moves

}