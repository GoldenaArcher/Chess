package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Queen;
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
    }

    @Test
    public void getPosition() {
    }

    @Test
    public void setPosition() {
    }

    @Test
    public void legalMoves() {
        ChessBoard board = new ChessBoard();
        Queen queen = new Queen(board, BLACK);
        board.placePiece(queen, "e5");
        System.out.println(Arrays.toString(queen.legalMoves().toArray()));
    }
}