package Test.ChessPieces;

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
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a2",
                                                                    "b8", "c8", "d8", "e8", "f8", "g8", "h8")));
//        Place a piece with same color on A7, it should remove A7 from legal moves of A1
        Rook rookA1 = new Rook(board, BLACK);
        board.placePiece(rookA1, "a1");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a6", "a5", "a4", "a3", "a2", "a2",
                "b8", "c8", "d8", "e8", "f8", "g8", "h8")));

//        Place a piece with different color on H8, it should not affect the result
        Rook rookH8 = new Rook(board, WHITE);
        board.placePiece(rookH8, "h8");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a6", "a5", "a4", "a3", "a2", "a2",
                "b8", "c8", "d8", "e8", "f8", "g8", "h8")));

//        put the piece on B8, then the black pawn should not be able to move to the X8 but B8
        board.placePiece(rookH8, "b8");
        assertTrue(rookA8.legalMoves().containsAll(Arrays.asList("a6", "a5", "a4", "a3", "a2", "b8")));

//        Now piece at A8 should only have 1 legal move
        board.placePiece(rookA1, "a7");

        System.out.println(Arrays.toString(rookA8.legalMoves().toArray()));

        System.out.println(board.toString());
//        assertTrue(rookA8.legalMoves().contains("b8") && rookA8.legalMoves().size() == 1);
    }

    @Test
    public void legalMovesLeftDown() {
        ChessBoard board = new ChessBoard();
        Rook rookA1 = new Rook(board, WHITE);

        board.placePiece(rookA1, "a1");
        assertTrue(rookA1.legalMoves().containsAll(Arrays.asList("a7", "a6", "a5", "a4", "a3", "a2", "a8",
                "b1", "c1", "d1", "e1", "f1", "g1", "h1")));

    }
}