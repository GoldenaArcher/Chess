package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Pawn;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static org.junit.Assert.*;

/**
 * @author Lu Han
 */
public class PawnTest {

    @Before
    public void setUp() {
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
    public void getPosition() {
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

    //    No need to check the last row as promotion is not implemented yet
//    Black Pawn tests. Put a black pawn on A row, and check it's legal moves
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
//        Set the black pawn on the board with it's initial position. In this case, H7 should have 2 valid moves
        board.placePiece(pawnBlackH7, "h7");
        assertTrue(pawnBlackH7.legalMoves().containsAll(Arrays.asList("h5", "h6")));
//        Set another piece at H5, H7 should only have one valid move
        board.placePiece(pawnBlackH5, "h5");
        assertTrue(pawnBlackH7.legalMoves().contains("h6") && pawnBlackH7.legalMoves().size() == 1);
//        If there is a piece at H5, its only legal move is H4
        assertTrue(pawnBlackH5.legalMoves().contains("h4") && pawnBlackH7.legalMoves().size() == 1);
        //        Set another piece at h6, h7 should have no valid move
        board.placePiece(pawnBlackH5, "h6");
        assertEquals(0, pawnBlackH7.legalMoves().size());
//        Set a different color piece at G6, H7 should have 1 valid move since it can capture G6
        Pawn pawnWhiteG6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteG6, "g6");
        assertTrue(pawnBlackH7.legalMoves().contains("g6") && pawnBlackH7.legalMoves().size() == 1);
    }

    //    Need to check the middle column, mainly added new test to check maximum legal moves without special moves: 4
    @Test
    public void legalMovesMiddleBlack() {
        ChessBoard board = new ChessBoard();
        Pawn pawnBlackE7 = new Pawn(board, BLACK);
        Pawn pawnBlackE5 = new Pawn(board, BLACK);
//        Set the black pawn on the board with it's initial position. In this case, E7 should have 2 valid moves
        board.placePiece(pawnBlackE7, "e7");
        assertTrue(pawnBlackE7.legalMoves().containsAll(Arrays.asList("e5", "e6")));
//        Set another piece at E5, E7 should only have one valid move
        board.placePiece(pawnBlackE5, "e5");
        assertTrue(pawnBlackE7.legalMoves().contains("e6") && pawnBlackE7.legalMoves().size() == 1);
//        If there is a piece at E5, its only legal move is E4
        assertTrue(pawnBlackE5.legalMoves().contains("e4") && pawnBlackE7.legalMoves().size() == 1);
        //        Set another piece at E6, E7 should have no valid move
        board.placePiece(pawnBlackE5, "e6");
        assertEquals(0, pawnBlackE7.legalMoves().size());
//        Set a different color piece at F6, E7 should have 1 valid move since it can capture F6
        Pawn pawnWhiteF6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteF6, "f6");
        assertTrue(pawnBlackE7.legalMoves().contains("f6") && pawnBlackE7.legalMoves().size() == 1);
//        Add another new different piece at the D6, in this case, there should be 2 valid move for e7 to move
        Pawn pawnWhiteD6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteD6, "d6");
        assertTrue(pawnBlackE7.legalMoves().containsAll(Arrays.asList("f6", "d6")));
    }

    //    White Pawn Test. Put a black pawn on A row, and check it's legal moves
    @Test
    public void legalMovesMostLeftWhite() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawnA2 = new Pawn(board, WHITE);
        Pawn whitePawnA4 = new Pawn(board, WHITE);
//        Set the black pawn on the board with it's initial position. In this case, A2 should have 2 valid moves
        board.placePiece(whitePawnA2, "a2");
        assertTrue(whitePawnA2.legalMoves().containsAll(Arrays.asList("a3", "a4")));
//        Set another piece at A4, A2 should only have one valid move
        board.placePiece(whitePawnA4, "a4");
        assertTrue(whitePawnA2.legalMoves().contains("a3") && whitePawnA2.legalMoves().size() == 1);
//        If there is a piece at A3, its only legal move is H4
        assertTrue(whitePawnA4.legalMoves().contains("a5") && whitePawnA2.legalMoves().size() == 1);
        //        Set another piece at h6, h7 should have no valid move
        board.placePiece(whitePawnA4, "a3");
        assertEquals(0, whitePawnA2.legalMoves().size());
//        Set a different color piece at G6, H7 should have 1 valid move since it can capture G6
        Pawn blackPawnB3 = new Pawn(board, BLACK);
        board.placePiece(blackPawnB3, "b3");
        assertTrue(whitePawnA2.legalMoves().contains("b3") && whitePawnA2.legalMoves().size() == 1);
    }
}