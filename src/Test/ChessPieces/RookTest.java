package Test.ChessPieces;

import Main.Chess;
import Main.ChessBoard;
import Main.ChessPieces.Rook;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;

public class RookTest {
    @Before
    public void setUp() {
    }

    @Test
    public void legalMovesLeftUp() {
        ChessBoard board = new ChessBoard();
        Rook rookA8 = new Rook(board, BLACK);
//        basically all piece of row 8 without a8, and all pieces on column a without A8
        board.placePiece(rookA8, "a8");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a2","a1",
                                                                    "b8", "c8", "d8", "e8", "f8", "g8", "h8")));
        assertEquals(14, rookA8.legalMoves().size());

//        Place a piece with same color on A7, it should remove A7 from legal moves of A1
        Rook rookA1 = new Rook(board, BLACK);
        board.placePiece(rookA1, "a1");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a2",
                "b8", "c8", "d8", "e8", "f8", "g8", "h8")));
        assertEquals(13, rookA8.legalMoves().size());

//        Place a piece with different color on H8, it should not affect the result
        Rook rookH8 = new Rook(board, WHITE);
        board.placePiece(rookH8, "h8");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a2",
                "b8", "c8", "d8", "e8", "f8", "g8", "h8")));
        assertEquals(13, rookA8.legalMoves().size());

//        put the piece on B8, then the black pawn should not be able to move to the X8 but B8
        board.placePiece(rookH8, "b8");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "b8")));
        assertEquals(7, rookA8.legalMoves().size());

//        Now piece at A8 should only have 1 legal move
        board.placePiece(rookA1, "a7");
        assertTrue(rookA8.legalMoves().contains("b8"));
        assertEquals(1, rookA8.legalMoves().size());

//        put another black piece at B8, then A8 should have no legal move
        board.placePiece(rookA1, "b8");
        assertTrue(rookA8.legalMoves().isEmpty());
    }

    @Test
    public void legalMovesLeftDown() {
        ChessBoard board = new ChessBoard();
        Rook rookA1 = new Rook(board, WHITE);

//        put a white rook at A1, it should be able to move all position on row 1 but A1, and all column on column A but A1
        board.placePiece(rookA1, "a1");
        assertTrue(rookA1.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a8",
                "b1", "c1", "d1", "e1", "f1", "g1", "h1")));
        assertEquals(14, rookA1.legalMoves().size());

//        put another white rook at H1, it should remove H1 from is valid result
        Rook rookH1 = new Rook(board, WHITE);
        board.placePiece(rookH1, "h1");
        assertTrue(rookA1.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a8",
                "b1", "c1", "d1", "e1", "f1", "g1")));
        assertEquals(13, rookA1.legalMoves().size());

//        put a different color rook at A8, it should not affect the result
        Rook rookA8 = new Rook(board, BLACK);
        board.placePiece(rookA8, "a8");
        assertTrue(rookA1.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a8",
                "b1", "c1", "d1", "e1", "f1", "g1")));
        assertEquals(13, rookA1.legalMoves().size());

//        put another white piece at A2, then A1 should only have A1 ... G1 as valid moves
        board.placePiece(rookH1, "a2");
        assertTrue(rookA1.legalMoves().containsAll(Arrays.asList("b1", "c1", "d1", "e1", "f1", "g1")));
        assertEquals(6, rookA1.legalMoves().size());

//        put another black piece at B1, then A1 should only have 1 legal position
        board.placePiece(rookA8, "b1");
        assertTrue(rookA1.legalMoves().contains("b1"));
        assertEquals(1, rookA1.legalMoves().size());

//        pub another white piece at B1, then A1's all legal moves are blocked
        board.placePiece(rookH1, "b1");
        assertTrue(rookA1.legalMoves().isEmpty());
    }

//    Test pawn's legal moves when the pawn is at X1 (neither A nor H)
    @Test
    public void legalMoveTopMiddle(){
        ChessBoard board = new ChessBoard();
        Rook rookD8 = new Rook(board, BLACK);

//        place a black piece on "D8", then all pieces on row D but D8 and A8-H8 but D8 are the valid moves
        board.placePiece(rookD8, "d8");
        assertTrue(rookD8.legalMoves().containsAll(Arrays.asList("d7", "d6", "d5", "d4", "d3", "d2", "d1",
                "a8", "b8", "c8", "e8", "f8", "g8", "h8")));
        assertEquals(14, rookD8.legalMoves().size());

//        put another same color piece on D7 to block all the pieces below D8
        Rook rookD7 = new Rook(board, BLACK);
        board.placePiece(rookD7, "d7");
        assertTrue(rookD8.legalMoves().containsAll(Arrays.asList("a8", "b8", "c8", "e8", "f8", "g8", "h8")));
        assertEquals(7, rookD8.legalMoves().size());

//        put another white piece on C8 to block all the legal moves on the left but C8
        Rook rookC8 = new Rook(board, WHITE);
        board.placePiece(rookC8, "c8");
        assertTrue(rookD8.legalMoves().containsAll(Arrays.asList("c8", "e8", "f8", "g8", "h8")));
        assertEquals(5, rookD8.legalMoves().size());

//        put the white piece on the right to block all the possible moves from right
        board.placePiece(rookC8, "e8");
        assertTrue(rookD8.legalMoves().containsAll(Arrays.asList("c8", "e8")));
        assertEquals(2, rookD8.legalMoves().size());

//        put the same color piece on both sides to block all the possible moves
        board.placePiece(rookD7, "e8");
        board.placePiece(rookD7, "c8");
        assertEquals(0, rookD8.legalMoves().size());
    }

//    place a piece at the middle of bottom lime and do the test
    @Test
    public void legalMoveBottomMiddle(){
        ChessBoard board = new ChessBoard();
        Rook rookE1 = new Rook(board, WHITE);

//        place the board on an empty board to get all 14 possible moves
        board.placePiece(rookE1, "e1");
        assertTrue(rookE1.legalMoves().containsAll(Arrays.asList("e2", "e3", "e4", "e5", "e6", "e7", "e8",
                "a1", "b1", "c1", "d1", "f1", "g1", "h1")));
        assertEquals(14, rookE1.legalMoves().size());

//        block all the moves above above E1, cut half of the legal moves
        Rook rookE2 = new Rook(board,WHITE);
        board.placePiece(rookE2, "e2");
        assertTrue(rookE1.legalMoves().containsAll(Arrays.asList("a1", "b1", "c1", "d1", "f1", "g1", "h1")));
        assertEquals(7, rookE1.legalMoves().size());

//        Next, block both side of E1
        Rook rookD1 = new Rook(board,BLACK);
        board.placePiece(rookD1, "d1");
        assertTrue(rookE1.legalMoves().containsAll(Arrays.asList("d1", "f1", "g1", "h1")));
        assertEquals(4, rookE1.legalMoves().size());

        board.placePiece(rookD1, "f1");
        assertTrue(rookE1.legalMoves().containsAll(Arrays.asList("d1", "f1")));
        assertEquals(2, rookE1.legalMoves().size());

//        Then, try to block both sides
        board.placePiece(rookE2, "d1");
        assertTrue(rookE1.legalMoves().contains("f1"));
        assertEquals(1, rookE1.legalMoves().size());

        board.placePiece(rookE2, "f1");
        assertEquals(0, rookE1.legalMoves().size());
    }

//    place a piece in the middle of an empty board
    @Test
    public void testMiddle(){
        ChessBoard board = new ChessBoard();
    }
}