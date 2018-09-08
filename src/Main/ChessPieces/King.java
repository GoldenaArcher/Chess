package Main.ChessPieces;
import Main.ChessBoard;

public class King extends ChessPiece {
    public King (ChessBoard chessBoard, Color color, int row, int column) {
        super(chessBoard, color, row, column);
    }

    @Override
    public boolean move(String from, String to) {
        return false;
    }

    @Override
    boolean validMove(String position) {
        return false;
    }

    @Override
    public String toString() {
//        white chess king	♔	U+2654
//        black chess king	♚	U+265A
        return getColor() == Color.WHITE ? "\u2654" : "\u265A";
    }
}
