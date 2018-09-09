package Main.ChessPieces;

import Main.ChessBoard;

/**
 * @author Lu Han
 */
public class Queen extends ChessPiece {
    public Queen (ChessBoard board, Color color) {
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
//        white chess queen	♕	U+2655
//        black chess queen	♛	U+265B
        return getColor() == Color.WHITE ? "\u2655" : "\u265B";
    }
}
