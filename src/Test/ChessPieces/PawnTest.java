package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Pawn;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static org.junit.Assert.*;

public class PawnTest {

    @Before
    public void setUp(){
    }

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        Pawn pawnBlack = new Pawn(board, BLACK);
        Pawn pawnWhite = new Pawn(board, WHITE);

        assertEquals(BLACK, pawnBlack.getColor());
        assertEquals(ChessPiece.Color.WHITE, pawnWhite.getColor());
    }

    @Test
    public void getPosition(){
        ChessBoard board = new ChessBoard();
        Pawn pawn = new Pawn(board, BLACK);
        board.placePiece(pawn, "a7");
        assertEquals("a7", pawn.getPosition());
    }

    @Test
    public void setPosition() {
        ChessBoard localBoard = new ChessBoard();
        Pawn localPawn = new Pawn(localBoard, BLACK);

        try {
            localPawn.setPosition("a7");
            assertEquals(localPawn.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void legalMoves() {
    }
}