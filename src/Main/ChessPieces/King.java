package Main.ChessPieces;

import Main.ChessBoard;

/**
 * @author Lu Han
 */
public class King extends ChessPiece {
    public King (ChessBoard board, Color color) {
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
//        white chess king	♔	U+2654
//        black chess king	♚	U+265A
        return getColor() == Color.WHITE ? "\u2654" : "\u265A";
    }
}
