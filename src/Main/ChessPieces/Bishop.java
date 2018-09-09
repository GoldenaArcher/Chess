package Main.ChessPieces;

import Main.ChessBoard;

/**
 * @author Lu Han
 */
public class Bishop extends  ChessPiece{
    public Bishop (ChessBoard board, Color color) {
        super(board, color);
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
//        black chess bishop	♝	U+265D
//        white chess bishop	♗	U+2657
        return getColor() == Color.WHITE ? "\u2657" : "\u265d";
    }
}
