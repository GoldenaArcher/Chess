package Test;

import Main.ChessBoard;
import Main.ChessPieces.*;
import Main.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * @author Lu Han
 */
public class ChessBoardTest {
    private ChessBoard chessBoard;
    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    public void placePiece() {
        Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK);
        Rook rookWhite = new Rook(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(rook, "a3"));
        assertTrue(!chessBoard.placePiece(rook, "a3"));
        assertTrue(chessBoard.placePiece(rookWhite, "a3"));
        assertTrue(!chessBoard.placePiece(rookWhite, "3"));
        assertTrue(!chessBoard.placePiece(rookWhite, "G10"));
        assertFalse(chessBoard.placePiece(rookWhite, "A10"));
    }

//    in this case, all passed parameters are valid, so there should not be a test that fails
    @Test
    public void getPiece() {
        Rook rook = new Rook(chessBoard, ChessPiece.Color.BLACK);
        try {
            assertNull(chessBoard.getPiece("a3"));
            chessBoard.placePiece(rook, "a3");
            assertEquals(rook, chessBoard.getPiece("a3"));
            assertNull(chessBoard.getPiece("a4"));
        } catch (IllegalPositionException e) {
            System.out.println("Fail on the getPiece process in testcase.");
        }
    }

//    also, have to test if the proper exceptions have been thrown by getPiece methods
    @Test(expected=IllegalPositionException.class)
    public void getPieceIllegalPosition() throws IllegalPositionException {
        chessBoard.getPiece("a10");
        chessBoard.getPiece("A1");
        chessBoard.getPiece("h9");
        chessBoard.getPiece("l9");
    }

    private boolean testOtherPiece(ChessPiece piece, int column){
        switch (column) {
            case 0:
            case 7:
                return piece instanceof Main.ChessPieces.Rook;
            case 1:
            case 6:
                return piece instanceof Main.ChessPieces.Knight;
            case 2:
            case 5:
                return piece instanceof Main.ChessPieces.Bishop;
            case 4:
                return piece instanceof Main.ChessPieces.King;
            default:
                return piece instanceof Main.ChessPieces.Queen;
        }
    }

    @Test
    public void initialize(){
        chessBoard.initialize();

        ChessPiece.Color black = ChessPiece.Color.BLACK;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        String position;
        try {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    position = "" + (char) ('a' + column) + (7 - row + 1);
                    ChessPiece piece = chessBoard.getPiece(position);
                    if (row == 0) {
                        assertTrue(testOtherPiece(piece, column) && piece.getColor() == black);
                    } else if (row == 1) {
                        assertTrue(piece instanceof Main.ChessPieces.Pawn && piece.getColor() == black);
                    } else if (row == 6) {
                        assertTrue(piece instanceof Main.ChessPieces.Pawn && piece.getColor() == white);
                    } else if (row == 7) {
                        assertTrue(testOtherPiece(piece, column) && piece.getColor() == white);
                    }
                }
            }
        } catch (IllegalPositionException e){
            System.out.println("Fail on the initialization process in testcase.");
        }
    }

    @Test
    public void move() {
    }
}