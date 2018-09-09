package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Queen extends ChessPiece {
    public Queen (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
//        white chess queen	♕	U+2655
//        black chess queen	♛	U+265B
        return getColor() == Color.WHITE ? "\u2655" : "\u265B";
    }
}
