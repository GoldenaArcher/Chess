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
    public void legalMovesLeftTop() {
        ChessBoard board = new ChessBoard();
//        place a black king at top left corner
        King kingA8 = new King(board, BLACK);
        board.placePiece(kingA8, "a8");
        assertTrue(kingA8.legalMoves().containsAll(Arrays.asList("a7", "b7", "b8")));
        assertEquals(3, kingA8.legalMoves().size());

//        place different color piece around it, assume the other color are king as well. It should have no affect on moves
        King kingCapture = new King(board, WHITE);
//        board.placePiece(kingCapture, "a7");
        assertTrue(board.placePiece(kingCapture, "a7"));
        assertTrue(board.placePiece(kingCapture, "b7"));
        assertTrue(board.placePiece(kingCapture, "b8"));
        assertTrue(kingA8.legalMoves().containsAll(Arrays.asList("a7", "b7", "b8")));
        assertEquals(3, kingA8.legalMoves().size());

//        Then place a same color as original pieces there, to block all the possible moves
        King kingBlock = new King(board, BLACK);
        assertTrue(board.placePiece(kingBlock, "a7"));
        assertTrue(board.placePiece(kingBlock, "b7"));
        assertTrue(board.placePiece(kingBlock, "b8"));
        assertEquals(0, kingA8.legalMoves().size());
    }

    @Test
    public void legalMoveRightBottom() {
        ChessBoard board = new ChessBoard();
//        Place a white king at bottom right
        King kingH1 = new King(board, WHITE);
        board.placePiece(kingH1,"h1");
        assertTrue(kingH1.legalMoves().containsAll(Arrays.asList("h2", "g2", "g1")));
        assertEquals(3,kingH1.legalMoves().size());

//        now use different color to block the king
        King kingG1 = new King(board,BLACK);
        assertTrue(board.placePiece(kingG1,"g1"));
        assertTrue(board.placePiece(kingG1,"g2"));
        assertTrue(board.placePiece(kingG1,"h2"));
        assertTrue(kingH1.legalMoves().containsAll(Arrays.asList("h2", "g2", "g1")));
        assertEquals(3,kingH1.legalMoves().size());

//        use a same color piece to block all possible moves
        King kingBlock = new King(board, WHITE);
        board.placePiece(kingBlock, "g1");
        board.placePiece(kingBlock, "g2");
        board.placePiece(kingBlock, "h2");
        assertEquals(0,kingH1.legalMoves().size());
    }

    @Test
    public void legalMoveMiddle() {
//        pub a king in middle of the board to check the maximum possible moves - 8
        ChessBoard board = new ChessBoard();
        King middle = new King(board, BLACK);
        board.placePiece(middle, "e5");
        assertTrue(middle.legalMoves().containsAll(Arrays.asList("e4", "e6", "d4", "d5", "d6", "f4", "f5", "f6")));
        assertEquals(8, middle.legalMoves().size());

//        Now put few black piece on surrounding legal moves
        King white = new King(board, WHITE);
        assertTrue(board.placePiece(white, "e4"));
        assertTrue(board.placePiece(white, "d6"));
        assertTrue(board.placePiece(white, "f5"));
        assertTrue(middle.legalMoves().containsAll(Arrays.asList("e4", "e6", "d4", "d5", "d6", "f4", "f5", "f6")));
        assertEquals(8, middle.legalMoves().size());

//      Put another new black kings to block all the moves
        King black = new King(board, BLACK);
        board.placePiece(black, "e4");
        board.placePiece(black, "e6");
        board.placePiece(black, "d4");
        board.placePiece(black, "d5");
        board.placePiece(black, "d6");
        board.placePiece(black, "f4");
        board.placePiece(black, "f5");
        board.placePiece(black, "f6");
        assertEquals(0, middle.legalMoves().size());
    }

//    intend to throw a bunch of error messages but not to fail the program
    @Test
    public void edgeCase(){
        ChessBoard board = new ChessBoard();
        King king = new King(board, BLACK);
        assertFalse(board.placePiece(king, "A10"));
        assertFalse(board.placePiece(king, "a10"));
        assertFalse(board.placePiece(king, "11"));
        assertFalse(board.placePiece(king, "l1"));
        assertFalse(board.placePiece(king, "9a"));
    }
}