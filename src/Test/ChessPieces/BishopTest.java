package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.Bishop;
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
    }

    @Test
    public void getPosition() {
    }

    @Test
    public void legalMoves1() {
        ChessBoard board = new ChessBoard();
        Bishop bishop = new Bishop(board, BLACK);
        board.placePiece(bishop, "d6");
        System.out.println(Arrays.toString(bishop.legalMoves().toArray()));
        System.out.println();
    }
}