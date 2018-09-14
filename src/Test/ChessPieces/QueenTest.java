package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Queen;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;

public class QueenTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        Queen queenBlack = new Queen(board, BLACK);
        Queen queenWhite = new Queen(board, WHITE);

        assertEquals(BLACK, queenBlack.getColor());
        assertEquals(WHITE, queenWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        Queen queen = new Queen(board, BLACK);
        board.placePiece(queen, "a7");
        assertEquals("a7", queen.getPosition());
//        Test with some edge cases
        board.placePiece(queen, "a10");
        assertTrue(!"a10".equals(queen.getPosition()));
        board.placePiece(queen, "a");
        assertTrue(!"a".equals(queen.getPosition()));
        board.placePiece(queen, "i8");
        assertTrue(!"f8".equals(queen.getPosition()));
    }

    @Test
    public void setPosition() {
        ChessBoard localBoard = new ChessBoard();
        Queen localQueen = new Queen(localBoard, BLACK);

        try {
            localQueen.setPosition("a7");
            assertEquals(localQueen.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Queen queen = new Queen(localBoard, BLACK);
        queen.setPosition("a10");
        queen.setPosition("a0");
        queen.setPosition("B1");
        queen.setPosition("i0");
    }

    @Test
    public void legalMoves() {
        ChessBoard board = new ChessBoard();
        Queen queen = new Queen(board, BLACK);
        board.placePiece(queen, "e5");
        System.out.println(Arrays.toString(queen.legalMoves().toArray()));
    }
}