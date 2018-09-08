package Test;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Rook;
import org.junit.Test;

public class ChessBoardTest {
    ChessBoard chessBoard = new ChessBoard();

    @Test
    public void placePiece() {
        chessBoard.placePiece(new Rook(chessBoard, ChessPiece.Color.BLACK), "a3");
    }

    @Test
    public void getPiece() {
    }

    @Test
    public void move() {
    }
}