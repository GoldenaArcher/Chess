package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Knight extends  ChessPiece{
    public Knight (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    @Override
    public String toString() {
        //   white chess knight	♘	U+2658
        // black chess knight	♞	U+265E
        return getColor() == Color.WHITE ?  "\u2658" : "\u265E";
    }
}
