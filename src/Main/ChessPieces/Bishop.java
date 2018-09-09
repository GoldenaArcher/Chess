package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Bishop extends  ChessPiece{
    public Bishop (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
//        black chess bishop	♝	U+265D
//        white chess bishop	♗	U+2657
        return getColor() == Color.WHITE ? "\u2657" : "\u265d";
    }
}
