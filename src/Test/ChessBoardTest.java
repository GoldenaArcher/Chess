package Test;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Rook;
import org.junit.Test;

public class ChessBoardTest {
    ChessBoard chessBoard = new ChessBoard();

    @Test
    public void placePiece() {
        placePiece(new Rook(chessBoard, ChessPiece.Color.BLACK, 1, 1), "a3");
    }

    @Test
    public void getPiece() {
    }

    @Test
    public void move() {
    }
}