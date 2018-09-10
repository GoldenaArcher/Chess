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

/**
 * @author Lu Han
 */
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
//        Test with some edge cases
        board.placePiece(pawn, "a10");
        assertTrue(!"a10".equals(pawn.getPosition()));
        board.placePiece(pawn, "a");
        assertTrue(!"a".equals(pawn.getPosition()));
        board.placePiece(pawn, "i8");
        assertTrue(!"f8".equals(pawn.getPosition()));
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
        ChessBoard board = new ChessBoard();
        Pawn pawnBlackA7 = new Pawn(board, BLACK);
        board.placePiece(pawnBlackA7, "a7");
        System.out.println(board.toString());
        for (String str: pawnBlackA7.legalMoves()) {
            System.out.println(str);
        }
    }
}