package Test.ChessPieces;

import Main.Operation.ChessBoard;
import Main.ChessPieces.Bishop;
import Main.Exception.IllegalPositionException;
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
        ChessBoard board = new ChessBoard();
        Bishop bishopBlack = new Bishop(board, BLACK);
        Bishop bishopWhite = new Bishop(board, WHITE);

        assertEquals(BLACK, bishopBlack.getColor());
        assertEquals(WHITE, bishopWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        Bishop bishop = new Bishop(board, BLACK);
        board.placePiece(bishop, "a7");
        assertEquals("a7", bishop.getPosition());
//        Test with some edge cases
        board.placePiece(bishop, "a10");
        assertTrue(!"a10".equals(bishop.getPosition()));
        board.placePiece(bishop, "a");
        assertTrue(!"a".equals(bishop.getPosition()));
        board.placePiece(bishop, "i8");
        assertTrue(!"f8".equals(bishop.getPosition()));
    }

    @Test
    public void setPosition(){
        ChessBoard localBoard = new ChessBoard();
        Bishop bishop = new Bishop(localBoard, BLACK);

        try {
            bishop.setPosition("a7");
            assertEquals(bishop.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Bishop bishop = new Bishop(localBoard, BLACK);
        bishop.setPosition("a10");
        bishop.setPosition("a0");
        bishop.setPosition("B1");
        bishop.setPosition("i0");
    }

    @Test
    public void legalMoveLeftUp() {
        ChessBoard board = new ChessBoard();
        Bishop bishopA8 = new Bishop(board, BLACK);
        board.placePiece(bishopA8, "a8");
        String[] res = {"b7", "c6", "d5", "e4", "f3", "g2", "h1"};
        assertTrue(bishopA8.legalMoves().containsAll(Arrays.asList(res)));
        assertEquals(res.length, bishopA8.legalMoves().size());
        
//        add different color to block the move
        Bishop bishopD5 = new Bishop(board, WHITE);
        board.placePiece(bishopD5, "d5");
        assertTrue(bishopA8.legalMoves().containsAll(Arrays.asList("b7", "c6", "d5")));
        assertEquals(3, bishopA8.legalMoves().size());

//        to block all the moves of bishop at A8
        Bishop bishopBlock = new Bishop(board, BLACK);
        board.placePiece(bishopBlock, "b7");
        assertEquals(0, bishopA8.legalMoves().size());
    }

    @Test
    public void legalMoveRightDown() {
        ChessBoard board = new ChessBoard();
        Bishop bishopH1 = new Bishop(board, WHITE);
        board.placePiece(bishopH1, "h1");
        assertTrue(bishopH1.legalMoves().containsAll(Arrays.asList("a8", "b7", "c6", "d5", "e4", "f3", "g2")));
        assertEquals(7, bishopH1.legalMoves().size());

//        add a different piece to block the move
        Bishop bishopG2 = new Bishop(board, BLACK);
        board.placePiece(bishopG2, "g2");
        assertEquals(1, bishopH1.legalMoves().size());
        assertTrue(bishopH1.legalMoves().contains("g2"));

//        add last piece to block all the moves
        Bishop bishopBlock = new Bishop(board, WHITE);
        board.placePiece(bishopBlock, "g2");
        assertEquals(0, bishopH1.legalMoves().size());
    }

    @Test
    public void legalMovesMiddle() {
        ChessBoard board = new ChessBoard();
        Bishop bishopD6 = new Bishop(board, BLACK);
        board.placePiece(bishopD6, "d6");
        String[] res = {"a3", "b8", "b4", "c7", "c5", "e7", "e5", "f8", "f4", "g3", "h2"};
        assertTrue(bishopD6.legalMoves().containsAll(Arrays.asList(res)));
        assertEquals(bishopD6.legalMoves().size(), res.length);

//        add different color piece to block some of D6's moves
        Bishop bishopBlock = new Bishop(board, WHITE);
        board.placePiece(bishopBlock, "b4");
        board.placePiece(bishopBlock, "c7");
        board.placePiece(bishopBlock, "f8");
        board.placePiece(bishopBlock, "e5");
        assertTrue(bishopD6.legalMoves().containsAll(Arrays.asList("b4", "c7", "c5", "e7", "e5", "f8")));
        assertEquals(6, bishopD6.legalMoves().size());

//        block all the moves that D6 possibly have
        Bishop bishopBlockAll = new Bishop(board, BLACK);
        board.placePiece(bishopBlockAll, "c7");
        board.placePiece(bishopBlockAll, "c5");
        board.placePiece(bishopBlockAll, "e5");
        board.placePiece(bishopBlockAll, "e7");
        assertEquals(0, bishopD6.legalMoves().size());
    }

    //    Exception test, should not have any result, throw a bunch of exceptions, but not terminate
    @Test
    public void testEdges() {
        ChessBoard board = new ChessBoard();
        Bishop bishopEdge = new Bishop(board, BLACK);
        board.placePiece(bishopEdge, "A10");
        assertEquals(0, bishopEdge.legalMoves().size());
        board.placePiece(bishopEdge, "A9");
        assertEquals(0, bishopEdge.legalMoves().size());
        board.placePiece(bishopEdge, "a9");
        assertEquals(0, bishopEdge.legalMoves().size());
        board.placePiece(bishopEdge, "h9");
        assertEquals(0, bishopEdge.legalMoves().size());
        board.placePiece(bishopEdge, "G9");
        assertEquals(0, bishopEdge.legalMoves().size());
    }
}