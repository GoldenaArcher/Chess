package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class King extends ChessPiece {
    public King (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
//        white chess king	♔	U+2654
//        black chess king	♚	U+265A
        return getColor() == Color.WHITE ? "\u2654" : "\u265A";
    }
}
