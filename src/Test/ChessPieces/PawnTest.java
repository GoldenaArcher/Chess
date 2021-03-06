package Test.ChessPieces;

import Main.Operation.ChessBoard;
import Main.ChessPieces.Pawn;
import Main.Exception.IllegalPositionException;
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
        assertEquals(WHITE, pawnWhite.getColor());
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

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Pawn pawn = new Pawn(localBoard, BLACK);
        pawn.setPosition("a10");
        pawn.setPosition("a0");
        pawn.setPosition("B1");
        pawn.setPosition("i0");
    }

//    Black Pawn tests. Put a black pawn on A row, and check it's legal moves
    @Test
    public void legalMovesMostLeftBlack() {
        ChessBoard board = new ChessBoard();
        Pawn pawnBlackA7 = new Pawn(board, BLACK);
        Pawn pawnBlackA5 = new Pawn(board, BLACK);

//        Set the black pawn on the board with it's initial position. In this case, A7 should have 2 valid moves
        board.placePiece(pawnBlackA7, "a7");
        assertTrue(pawnBlackA7.legalMoves().containsAll(Arrays.asList("a5", "a6")));
        assertEquals(2, pawnBlackA7.legalMoves().size());

//        Set another piece at A5, A7 should only have one valid move
        board.placePiece(pawnBlackA5, "a5");
        assertTrue(pawnBlackA7.legalMoves().contains("a6"));
        assertEquals(1, pawnBlackA7.legalMoves().size());

//        If there is a piece at A5, its only legal move is A4
        assertTrue(pawnBlackA5.legalMoves().contains("a4"));
        assertEquals(1,pawnBlackA7.legalMoves().size());

        //        Set another piece at A6, A7 should have no valid move
        board.placePiece(pawnBlackA5, "a6");
        assertEquals(0, pawnBlackA7.legalMoves().size());

//        Set a different color piece at B6, A7 should have 1 valid move since it can capture B6
        Pawn pawnWhiteB6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteB6, "b6");
        assertTrue(pawnBlackA7.legalMoves().contains("b6"));
        assertEquals(1, pawnBlackA7.legalMoves().size());
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
        assertEquals(2, pawnBlackH7.legalMoves().size());

//        Set another piece at H5, H7 should only have one valid move
        board.placePiece(pawnBlackH5, "h5");
        assertTrue(pawnBlackH7.legalMoves().contains("h6"));
        assertEquals(1, pawnBlackH7.legalMoves().size());

//        If there is a piece at H5, its only legal move is H4
        assertTrue(pawnBlackH5.legalMoves().contains("h4"));
        assertEquals(1,pawnBlackH7.legalMoves().size());

        //        Set another piece at h6, h7 should have no valid move
        board.placePiece(pawnBlackH5, "h6");
        assertEquals(0, pawnBlackH7.legalMoves().size());

//        Set a different color piece at G6, H7 should have 1 valid move since it can capture G6
        Pawn pawnWhiteG6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteG6, "g6");
        assertTrue(pawnBlackH7.legalMoves().contains("g6"));
        assertEquals(1,pawnBlackH7.legalMoves().size());
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
        assertEquals(2, pawnBlackE7.legalMoves().size());

//        Set another piece at E5, E7 should only have one valid move
        board.placePiece(pawnBlackE5, "e5");
        assertTrue(pawnBlackE7.legalMoves().contains("e6"));
        assertEquals(1, pawnBlackE7.legalMoves().size());

//        If there is a piece at E5, its only legal move is E4
        assertTrue(pawnBlackE5.legalMoves().contains("e4"));
        assertEquals(1,pawnBlackE7.legalMoves().size());

        //        Set another piece at E6, E7 should have no valid move
        board.placePiece(pawnBlackE5, "e6");
        assertEquals(0, pawnBlackE7.legalMoves().size());

//        Set a different color piece at F6, E7 should have 1 valid move since it can capture F6
        Pawn pawnWhiteF6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteF6, "f6");
        assertTrue(pawnBlackE7.legalMoves().contains("f6"));
        assertEquals(1,pawnBlackE7.legalMoves().size());

//        Add another new different piece at the D6, in this case, there should be 2 valid move for e7 to move
        Pawn pawnWhiteD6 = new Pawn(board, WHITE);
        board.placePiece(pawnWhiteD6, "d6");
        assertTrue(pawnBlackE7.legalMoves().containsAll(Arrays.asList("f6", "d6")));
        assertEquals(2, pawnBlackE7.legalMoves().size());
    }

    //    White Pawn Test. Put a black pawn on A row, and check it's legal moves
    @Test
    public void legalMovesMostLeftWhite() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawnA2 = new Pawn(board, WHITE);
        Pawn whitePawnA4 = new Pawn(board, WHITE);

//        Set the white pawn on the board with it's initial position. In this case, A2 should have 2 valid moves
        board.placePiece(whitePawnA2, "a2");
        assertTrue(whitePawnA2.legalMoves().containsAll(Arrays.asList("a3", "a4")));
        assertEquals(2, whitePawnA2.legalMoves().size());

//        Set another piece at A4, A2 should only have one valid move
        board.placePiece(whitePawnA4, "a4");
        assertTrue(whitePawnA2.legalMoves().contains("a3"));
        assertEquals(1,whitePawnA2.legalMoves().size());

//        If there is a piece at A3, its only legal move is A5
        assertTrue(whitePawnA4.legalMoves().contains("a5"));
        assertEquals(1,whitePawnA2.legalMoves().size());

        //        Set another piece at h6, A3 should have no valid move
        board.placePiece(whitePawnA4, "a3");
        assertEquals(0, whitePawnA2.legalMoves().size());

//        Set a different color piece at B3, A2 should have 1 valid move since it can capture G6
        Pawn blackPawnB3 = new Pawn(board, BLACK);
        board.placePiece(blackPawnB3, "b3");
        assertTrue(whitePawnA2.legalMoves().contains("b3"));
        assertEquals(1, whitePawnA2.legalMoves().size());
    }

    //    White Pawn Test. Put a black pawn on H row, and check it's legal moves
    @Test
    public void legalMovesMostRightWhite() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawnH2 = new Pawn(board, WHITE);
        Pawn whitePawnH4 = new Pawn(board, WHITE);

//        Set the white pawn on the board with it's initial position. In this case, H2 should have 2 valid moves
        board.placePiece(whitePawnH2, "h2");
        assertTrue(whitePawnH2.legalMoves().containsAll(Arrays.asList("h3", "h4")));

//        Set another piece at H4, H2 should only have one valid move
        board.placePiece(whitePawnH4, "h4");
        assertTrue(whitePawnH2.legalMoves().contains("h3"));
        assertEquals(1,whitePawnH2.legalMoves().size());

//        If there is a piece at H4, its only legal move is H5
        assertTrue(whitePawnH4.legalMoves().contains("h5"));
        assertEquals(1, whitePawnH2.legalMoves().size());

        //        Set another piece at h3, h4 should have no valid move
        board.placePiece(whitePawnH4, "h3");
        assertEquals(0, whitePawnH2.legalMoves().size());

//        Set a different color piece at G3, H2 should have 1 valid move since it can capture G6
        Pawn blackPawnG3 = new Pawn(board, BLACK);
        board.placePiece(blackPawnG3, "g3");
        assertTrue(whitePawnH2.legalMoves().contains("g3"));
        assertEquals(1, whitePawnH2.legalMoves().size());
    }

//    Then test the middle white piece
    @Test
    public void legalMiddleWhite(){
        ChessBoard board = new ChessBoard();
        Pawn whitePawnD2 = new Pawn(board, WHITE);
        Pawn whitePawnD4 = new Pawn(board, WHITE);

//        Set the white pawn on the board with it's initial position. In this case, D2 should have 2 valid moves
        board.placePiece(whitePawnD2, "d2");
        assertTrue(whitePawnD2.legalMoves().containsAll(Arrays.asList("d3", "d4")));
        assertEquals(2, whitePawnD2.legalMoves().size());

//        Set another piece at D4, H2 should only have one valid move
        board.placePiece(whitePawnD4, "d4");
        assertTrue(whitePawnD2.legalMoves().contains("d3"));
        assertEquals(1, whitePawnD2.legalMoves().size());

//        If there is a piece at D4, its only legal move is D5
        assertTrue(whitePawnD4.legalMoves().contains("d5"));
        assertEquals(1, whitePawnD2.legalMoves().size() );

        //        Set another piece at D3, D4 should have no valid move
        board.placePiece(whitePawnD4, "d3");
        assertEquals(0, whitePawnD2.legalMoves().size());

//        Set a different color piece at G3, H2 should have 1 valid move since it can capture G6
        Pawn blackPawnE3 = new Pawn(board, BLACK);
        board.placePiece(blackPawnE3, "e3");
        assertTrue(whitePawnD2.legalMoves().contains("e3"));
        assertEquals(1, whitePawnD2.legalMoves().size());

//        move another piece diagonally, then the white piece should have two possible pieces for capturing
        Pawn blackPawnC3 = new Pawn(board, BLACK);
        board.placePiece(blackPawnC3, "c3");
        assertTrue(whitePawnD2.legalMoves().containsAll(Arrays.asList("c3", "e3")));
        assertEquals(2, whitePawnD2.legalMoves().size());
    }

//    Test the white piece on X1 and black piece on X8. It is not going to happened in the real game, so a
//    IllegalPositionException is expected to be thrown.
    @Test
    public void  edgeCase() {
        ChessBoard board = new ChessBoard();
        Pawn blackPawnA1 = new Pawn(board, BLACK);

        board.placePiece(blackPawnA1, "a1");
        assertEquals(0, blackPawnA1.legalMoves().size());

//        put black piece on h1 should not yield any result either
        board.placePiece(blackPawnA1, "h1");
        assertEquals(0, blackPawnA1.legalMoves().size());

//        same as put the piece in the middle of the last board
        board.placePiece(blackPawnA1, "e1");
        assertEquals(0, blackPawnA1.legalMoves().size());

        Pawn whitePawnA8 = new Pawn(board,WHITE);
        board.placePiece(blackPawnA1, "a8");
        assertEquals(0, whitePawnA8.legalMoves().size());

        board.placePiece(blackPawnA1, "h8");
        assertEquals(0, whitePawnA8.legalMoves().size());

        board.placePiece(blackPawnA1, "e8");
        assertEquals(0, whitePawnA8.legalMoves().size());

//        These should throw exception but not fail the program
        Pawn newPawn = new Pawn(board, BLACK);
        board.placePiece(newPawn, "A10");
        assertEquals(0, whitePawnA8.legalMoves().size());
        board.placePiece(newPawn, "A9");
        assertEquals(0, whitePawnA8.legalMoves().size());
        board.placePiece(newPawn, "a9");
        assertEquals(0, whitePawnA8.legalMoves().size());
        board.placePiece(newPawn, "k2");
        assertEquals(0, whitePawnA8.legalMoves().size());
    }

//    @TODO check pawn promotion later
//    @TODO check En Passant later
}