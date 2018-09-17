package Test.ChessPieces;

import Main.Operation.ChessBoard;
import Main.ChessPieces.Knight;
import Main.Operation.IllegalPositionException;
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

    @Test
    public void legalMovesTopLeft() {
        ChessBoard board = new ChessBoard();
        Knight knightA8 = new Knight(board, BLACK);
        board.placePiece(knightA8, "a8");
        assertTrue(knightA8.legalMoves().containsAll(Arrays.asList("b6", "c7")));
        assertEquals(2, knightA8.legalMoves().size());

//        place 2 different color pieces on the spot
        Knight knightBlock = new Knight(board, WHITE);
        board.placePiece(knightBlock, "b6");
        board.placePiece(knightBlock, "c7");
        assertTrue(knightA8.legalMoves().containsAll(Arrays.asList("b6", "c7")));
        assertEquals(2, knightA8.legalMoves().size());

//        place 2 same color pieces on the spots to block all the moves
        Knight knightBlockAll = new Knight(board, BLACK);
        board.placePiece(knightBlockAll, "b6");
        board.placePiece(knightBlockAll, "c7");
        assertEquals(0, knightA8.legalMoves().size());
    }

    @Test
    public void legalMoveBottomRight() {
        ChessBoard board = new ChessBoard();
        Knight knightH1 = new Knight(board, WHITE);
        board.placePiece(knightH1, "h1");
        assertTrue(knightH1.legalMoves().containsAll(Arrays.asList("g3", "f2")));
        assertEquals(2, knightH1.legalMoves().size());

//        place 2 different color pieces on the spot
        Knight knightBlock = new Knight(board, BLACK);
        board.placePiece(knightBlock, "g3");
        board.placePiece(knightBlock, "f2");
        assertTrue(knightH1.legalMoves().containsAll(Arrays.asList("g3", "f2")));
        assertEquals(2, knightH1.legalMoves().size());

//        place 2 same color pieces on the spots to block all the moves
        Knight knightBlockAll = new Knight(board, WHITE);
        board.placePiece(knightBlockAll, "g3");
        board.placePiece(knightBlockAll, "f2");
        assertEquals(0, knightH1.legalMoves().size());
    }

    @Test
    public void legalMiddle() {
        ChessBoard board = new ChessBoard();
        Knight knightE5 = new Knight(board, WHITE);
        board.placePiece(knightE5, "e5");
        assertTrue(knightE5.legalMoves().containsAll(Arrays.asList("c6", "c4", "d7", "d3", "f7", "f3", "g6", "g4")));
        assertEquals(8, knightE5.legalMoves().size());

//        place some different color pieces on the spots to block the moves
        Knight knightBLock = new Knight(board, BLACK);
        board.placePiece(knightBLock, "c6");
        board.placePiece(knightBLock, "d7");
        board.placePiece(knightBLock, "f7");
        board.placePiece(knightBLock, "g6");
        assertTrue(knightE5.legalMoves().containsAll(Arrays.asList("c6", "c4", "d7", "d3", "f7", "f3", "g6", "g4")));
        assertEquals(8, knightE5.legalMoves().size());

//        place different color pieces to block all the moves
        Knight knightBlockAll = new Knight(board, WHITE);
        board.placePiece(knightBlockAll, "c6");
        board.placePiece(knightBlockAll, "c4");
        board.placePiece(knightBlockAll, "d7");
        board.placePiece(knightBlockAll, "d3");
        board.placePiece(knightBlockAll, "f7");
        board.placePiece(knightBlockAll, "f3");
        board.placePiece(knightBlockAll, "g6");
        board.placePiece(knightBlockAll, "g4");
        assertEquals(0, knightE5.legalMoves().size());
    }

    //    Exception test, should not have any result, throw a bunch of exceptions, but not terminate
    @Test
    public void testEdges() {
        ChessBoard board = new ChessBoard();
        Knight knightEdge = new Knight(board, BLACK);
        board.placePiece(knightEdge, "A10");
        assertEquals(0, knightEdge.legalMoves().size());
        board.placePiece(knightEdge, "A9");
        assertEquals(0, knightEdge.legalMoves().size());
        board.placePiece(knightEdge, "a9");
        assertEquals(0, knightEdge.legalMoves().size());
        board.placePiece(knightEdge, "h9");
        assertEquals(0, knightEdge.legalMoves().size());
        board.placePiece(knightEdge, "G9");
        assertEquals(0, knightEdge.legalMoves().size());
    }
}