package Main.ChessPieces;

import Main.ChessBoard;
import Main.IllegalPositionException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lu Han
 */
public class Knight extends  ChessPiece{
    public Knight (ChessBoard board, Color color) {
        super(board, color);
    }

    /*
    Knight can move one square along any rank or file and then at an angle. The knight´s movement can also be viewed as
    an “L” or “7″ laid out at any horizontal or vertical angle.
     */
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();

//        test for valid move

        res.removeAll(Collections.singleton(null));
        return res;
    }

    @Override
    public String toString() {
        //   white chess knight	♘	U+2658
        // black chess knight	♞	U+265E
        return getColor() == Color.WHITE ?  "\u2658" : "\u265E";
    }
}
