package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Bishop;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static Main.ChessPieces.ChessPiece.Color.BLACK;

public class BishopTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        Bishop bishopBlack = new Bishop(board, BLACK);
        Bishop bishopWhite = new Bishop(board, WHITE);

        assertEquals(BLACK, bishopBlack.getColor());
        assertEquals(WHITE, bishopWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        Bishop bishop = new Bishop(board, BLACK);
        board.placePiece(bishop, "a7");
        assertEquals("a7", bishop.getPosition());
//        Test with some edge cases
        board.placePiece(bishop, "a10");
        assertTrue(!"a10".equals(bishop.getPosition()));
        board.placePiece(bishop, "a");
        assertTrue(!"a".equals(bishop.getPosition()));
        board.placePiece(bishop, "i8");
        assertTrue(!"f8".equals(bishop.getPosition()));
    }

    @Test
    public void setPosition(){
        ChessBoard localBoard = new ChessBoard();
        Bishop bishop = new Bishop(localBoard, BLACK);

        try {
            bishop.setPosition("a7");
            assertEquals(bishop.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Bishop bishop = new Bishop(localBoard, BLACK);
        bishop.setPosition("a10");
        bishop.setPosition("a0");
        bishop.setPosition("B1");
        bishop.setPosition("i0");
    }

    @Test
    public void legalMoves1() {
        ChessBoard board = new ChessBoard();
        Bishop bishop = new Bishop(board, BLACK);
        board.placePiece(bishop, "d6");
    }
}