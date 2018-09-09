package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Rook extends  ChessPiece{
    public Rook (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
        //   White Chess Rook "♖" (U+2656)
        // Black Chess Rook "♜" (U+265C)
        return getColor() == Color.WHITE ? "\u2656" : "\u265c";
    }
}
