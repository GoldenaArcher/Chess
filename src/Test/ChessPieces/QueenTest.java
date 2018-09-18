package Test.ChessPieces;

import Main.Operation.ChessBoard;
import Main.ChessPieces.Queen;
import Main.Exception.IllegalPositionException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static Main.ChessPieces.ChessPiece.Color.BLACK;
import static Main.ChessPieces.ChessPiece.Color.WHITE;

public class QueenTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getColor() {
        ChessBoard board = new ChessBoard();
        Queen queenBlack = new Queen(board, BLACK);
        Queen queenWhite = new Queen(board, WHITE);

        assertEquals(BLACK, queenBlack.getColor());
        assertEquals(WHITE, queenWhite.getColor());
    }

    @Test
    public void getPosition() {
        ChessBoard board = new ChessBoard();
        Queen queen = new Queen(board, BLACK);
        board.placePiece(queen, "a7");
        assertEquals("a7", queen.getPosition());
//        Test with some edge cases
        board.placePiece(queen, "a10");
        assertTrue(!"a10".equals(queen.getPosition()));
        board.placePiece(queen, "a");
        assertTrue(!"a".equals(queen.getPosition()));
        board.placePiece(queen, "i8");
        assertTrue(!"f8".equals(queen.getPosition()));
    }

    @Test
    public void setPosition() {
        ChessBoard localBoard = new ChessBoard();
        Queen localQueen = new Queen(localBoard, BLACK);

        try {
            localQueen.setPosition("a7");
            assertEquals(localQueen.getPosition(), "a7");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = IllegalPositionException.class)
    public void setPositionIllegal() throws IllegalPositionException {
        ChessBoard localBoard = new ChessBoard();
        Queen queen = new Queen(localBoard, BLACK);
        queen.setPosition("a10");
        queen.setPosition("a0");
        queen.setPosition("B1");
        queen.setPosition("i0");
    }

    @Test
    public void legalMoveUpLeft() {
        ChessBoard board = new ChessBoard();
        Queen queenA8 = new Queen(board, BLACK);
//        find all the pieces on column a but itself, all the pieces on row 8 but itself, and the diagonal from a8-h1
        board.placePiece(queenA8, "a8");
        String[] resA8 = {"a7", "a6", "a5", "a4", "a3", "a2", "a1", // same col
                "b8", "c8", "d8", "e8", "f8", "g8", "h8",   // same row
                "b7", "c6", "d5", "e4", "f3", "g2", "h1"};  // diagonal
        assertEquals(queenA8.legalMoves().size(), resA8.length);
        assertTrue(queenA8.legalMoves().containsAll(Arrays.asList(resA8)));

//        place 3 pieces to block some movements, now just put different color
        Queen queenBlock = new Queen(board,WHITE);
        board.placePiece(queenBlock, "d5");
        board.placePiece(queenBlock, "e8");
        board.placePiece(queenBlock, "a7");
        String[] resA82 = {"a7", "b8", "c8", "d8", "e8", "b7", "c6", "d5"};
        assertEquals(resA82.length, queenA8.legalMoves().size());
        assertTrue(queenA8.legalMoves().containsAll(Arrays.asList(resA82)));

//        place another piece to block all the movements
        Queen queenBlockAll = new Queen(board,BLACK);
        board.placePiece(queenBlockAll, "a7");
        board.placePiece(queenBlockAll, "b7");
        board.placePiece(queenBlockAll, "b8");
        assertEquals(0, queenA8.legalMoves().size());
    }

    @Test
    public void legalMoveBottomRight() {
        ChessBoard board = new ChessBoard();
        Queen queenH1 = new Queen(board, WHITE);
        //        find all the pieces on column 'h' but itself, all the pieces on row '1' but itself, and the diagonal from a8-h1
        board.placePiece(queenH1, "h1");
        String[] resH1 = {"h8", "h7", "h6", "h5", "h4", "h3", "h2",  // same col
                "a1", "b1", "c1", "d1", "e1", "f1", "g1",   // same row
                "a8", "b7", "c6", "d5", "e4", "f3", "g2"};  // diagonal
        assertEquals(queenH1.legalMoves().size(), resH1.length);
        assertTrue(queenH1.legalMoves().containsAll(Arrays.asList(resH1)));

        //        place 3 pieces to block some movements, now just put different color
        Queen queenBlock = new Queen(board, BLACK);
        board.placePiece(queenBlock, "d5");
        board.placePiece(queenBlock, "e1");
        board.placePiece(queenBlock, "h2");
        String[] resA82 = {"g2", "f3", "e4", "d5", "e1", "f1", "g1", "h2"};
        assertEquals(resA82.length, queenH1.legalMoves().size());
        assertTrue(queenH1.legalMoves().containsAll(Arrays.asList(resA82)));

        //        place another piece to block all the movements
        Queen queenBlockAll = new Queen(board, WHITE);
        board.placePiece(queenBlockAll, "h2");
        board.placePiece(queenBlockAll, "g2");
        board.placePiece(queenBlockAll, "g1");
        assertEquals(0, queenH1.legalMoves().size());
    }

    @Test
    public void legalMovesMiddle() {
        ChessBoard board = new ChessBoard();
        Queen queenE5 = new Queen(board, BLACK);
//        all available moves should be {A5, B5, C5, D5, F5, G5, H5, E1, E2, E3, E4, E6, E7, E8, D6, C7, B8, F4, G3,
//        H2, F6, G7, H8, D4, C3, B2, A1}. Put all result into an ArrayList, and then use set to remove duplicated key
//        in order to prevent human error
        board.placePiece(queenE5, "e5");
        String[] res = {"a5", "b5", "c5", "d5", "f5", "g5", "h5", // same row
                "e1", "e2", "e3", "e4", "e6", "e7", "e8", // same col
                "d6", "c7", "b8", "f4", "g3","h2",  // diagonal 1
                "f6", "g7", "h8", "d4", "c3", "b2", "a1"}; // diagonal 2
        List<String> solution = Arrays.asList(res);
        Set<String> uniqueKey = new HashSet<>(solution);
        assertEquals(uniqueKey.size(), queenE5.legalMoves().size());
        assertTrue(queenE5.legalMoves().containsAll(Arrays.asList(uniqueKey.toArray())));

//        to put some different pieces to block some of its moves
        Queen queenBlock = new Queen(board, WHITE);
        board.placePiece(queenBlock, "c7"); // left up
        board.placePiece(queenBlock, "b2"); // left down
        board.placePiece(queenBlock, "c5"); // left
        board.placePiece(queenBlock, "e6"); // up
        board.placePiece(queenBlock, "e4"); // down
        board.placePiece(queenBlock, "g5"); // right
        board.placePiece(queenBlock, "f6"); // right up
        board.placePiece(queenBlock, "g3"); // right down
        String[] resBlock = {"b2", "c7", "c5", "c3", "d6", "d5", "d4",
                "e6", "e4", "f6", "f5", "f4", "g5", "g3"};
        assertTrue(queenE5.legalMoves().containsAll(Arrays.asList(resBlock)));
        assertEquals(queenE5.legalMoves().size(), resBlock.length);

        Queen queenBlockAll = new Queen(board, BLACK);
        board.placePiece(queenBlockAll, "d6");
        board.placePiece(queenBlockAll, "d5");
        board.placePiece(queenBlockAll, "d4");
        board.placePiece(queenBlockAll, "e6");
        board.placePiece(queenBlockAll, "e4");
        board.placePiece(queenBlockAll, "f6");
        board.placePiece(queenBlockAll, "f5");
        board.placePiece(queenBlockAll, "f4");
        assertEquals(0, queenE5.legalMoves().size());
    }

    //    Exception test, should not have any result, throw a bunch of exceptions, but not terminate
    @Test
    public void testEdges() {
        ChessBoard board = new ChessBoard();
        Queen queenEdge = new Queen(board, BLACK);
        board.placePiece(queenEdge, "A10");
        assertEquals(0, queenEdge.legalMoves().size());
        board.placePiece(queenEdge, "A9");
        assertEquals(0, queenEdge.legalMoves().size());
        board.placePiece(queenEdge, "a9");
        assertEquals(0, queenEdge.legalMoves().size());
        board.placePiece(queenEdge, "h9");
        assertEquals(0, queenEdge.legalMoves().size());
        board.placePiece(queenEdge, "G9");
        assertEquals(0, queenEdge.legalMoves().size());
    }
}