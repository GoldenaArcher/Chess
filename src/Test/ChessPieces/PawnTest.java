package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Pawn;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

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

//    Put a black pawn on A row, and check it's legal moves
    @Test
    public void legalMovesMostLeftBlack() {
        ChessBoard board = new ChessBoard();
        Pawn pawnBlackA7 = new Pawn(board, BLACK);
        Pawn pawnBlackA5 = new Pawn(board, BLACK);
//        Set the black pawn on the board with it's initial position. In this case, A7 should have 2 valid moves
        board.placePiece(pawnBlackA7, "a7");
        assertTrue(pawnBlackA7.legalMoves().containsAll(Arrays.asList("a5", "a6")));
//        Set another piece at A5, A7 should only have one valid move
        board.placePiece(pawnBlackA5, "a5");
        assertTrue(pawnBlackA7.legalMoves().contains("a6") && pawnBlackA7.legalMoves().size() == 1);
//        If there is a piece at A5, its only legal move is A4
        assertTrue(pawnBlackA5.legalMoves().contains("a4") && pawnBlackA7.legalMoves().size() == 1);
        //        Set another piece at A6, A7 should have no valid move
        board.placePiece(pawnBlackA5, "a6");
        assertEquals(0, pawnBlackA7.legalMoves().size());
//        Set a different color piece at B6, A7 should have 1 valid move since it can capture B6
        Pawn pawnWhiteB6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteB6, "b6");
        assertTrue(pawnBlackA7.legalMoves().contains("b6") && pawnBlackA7.legalMoves().size() == 1);
    }

//    Almost identical to previous test, but only put it on the H row
    @Test
    public void legalMovesMostRightBlack() {
        ChessBoard board = new ChessBoard();
        Pawn pawnBlackH7 = new Pawn(board, BLACK);
        Pawn pawnBlackH5 = new Pawn(board, BLACK);
//        Set the black pawn on the board with it's initial position. In this case, A7 should have 2 valid moves
        board.placePiece(pawnBlackH7, "h7");
        assertTrue(pawnBlackH7.legalMoves().containsAll(Arrays.asList("h5", "h6")));
//        Set another piece at A5, A7 should only have one valid move
        board.placePiece(pawnBlackH5, "h5");
        assertTrue(pawnBlackH7.legalMoves().contains("h6") && pawnBlackH7.legalMoves().size() == 1);
//        If there is a piece at A5, its only legal move is A4
        assertTrue(pawnBlackH5.legalMoves().contains("h4") && pawnBlackH7.legalMoves().size() == 1);
        //        Set another piece at A6, A7 should have no valid move
        board.placePiece(pawnBlackH5, "h6");
        assertEquals(0, pawnBlackH7.legalMoves().size());
//        Set a different color piece at B6, A7 should have 1 valid move since it can capture B6
        Pawn pawnWhiteG6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteG6, "g6");
        assertTrue(pawnBlackH7.legalMoves().contains("g6") && pawnBlackH7.legalMoves().size() == 1);
    }
}