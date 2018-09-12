package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.King;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;

/**
 * @author Lu Han
 */
public class KingTest {
    @Before
    public void setUp() {}

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        King kingBlack = new King(board, BLACK);
        King kingWhite = new King(board, WHITE);

        assertEquals(BLACK, kingBlack.getColor());
        assertEquals(WHITE, kingWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        King king = new King(board, BLACK);
        board.placePiece(king, "a7");
        assertEquals("a7", king.getPosition());
//        Test with some edge cases
        board.placePiece(king, "a10");
        assertTrue(!"a10".equals(king.getPosition()));
        board.placePiece(king, "a");
        assertTrue(!"a".equals(king.getPosition()));
        board.placePiece(king, "i8");
        assertTrue(!"f8".equals(king.getPosition()));
    }

    @Test
    public void setPosition() {
        ChessBoard localBoard = new ChessBoard();
        King king = new King(localBoard, BLACK);

        try {
            king.setPosition("a7");
            assertEquals(king.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void legalMovesLeftUp() {
        ChessBoard board = new ChessBoard();
//        place a black king at left corner
        King kingA8 = new King(board, BLACK);
        board.placePiece(kingA8, "a8");
        assertTrue(kingA8.legalMoves().containsAll(Arrays.asList("a7", "b7", "b8")));
        assertEquals(3, kingA8.legalMoves().size());

//        place different color piece around it, assume the other color are king as well. It should have no affect on moves
        King kingBlock = new King(board, WHITE);
        board.placePiece(kingBlock, "A7");
        board.placePiece(kingBlock, "B7");
        board.placePiece(kingBlock, "B8");
        assertTrue(kingA8.legalMoves().containsAll(Arrays.asList("a7", "b7", "b8")));
        assertEquals(3, kingA8.legalMoves().size());

//        Then place a different color pieces there, to block all the possible moves

    }

    @Test
    public void legalMoveRightBottom() {}

    @Test
    public void legalMoveMiddle() {}

//    intend to throw a bunch of error messages but not to fail the program
    @Test
    public void edgeCase(){}
}