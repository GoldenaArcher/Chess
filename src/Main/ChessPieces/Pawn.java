package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Pawn extends ChessPiece {
    public Pawn (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
