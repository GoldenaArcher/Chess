package Main.ChessPieces;

import Main.ChessBoard;
import Main.IllegalPositionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Lu Han
 */
public class King extends ChessPiece {
    public King (ChessBoard board, Color color) {
        super(board, color);
    }

//    The King can move (or capture) one square in any direction
    private String basicMove(int rowIncrementation, int colIncrementation) {
        int tempRow = row + rowIncrementation, tempCol = row + colIncrementation;
        if (tempCol <8 && tempCol >= 0 && tempRow < 8 && tempRow >= 0) {
            String position = "" + (char) (tempCol + 'a') +  (7 - tempRow + 1);
            ChessPiece piece = null;
            try {
                piece = board.getPiece(position);
            } catch (IllegalPositionException e) {
                System.out.println("error occurred in position check, please come back and check the error");
            }
            if (piece == null || piece.getColor() != color) {
                return position;
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();
        res.add(basicMove(1,0));
        res.add(basicMove(-1,0));
        res.add(basicMove(0,1));
        res.add(basicMove(0,-1));
        res.add(basicMove(1,1));
        res.add(basicMove(1,-1));
        res.add(basicMove(-1,1));
        res.add(basicMove(-1,-1));
//        remove all empty values added by methods
        res.removeAll(Collections.singleton(null));
        return res;
    }

    @Override
    public String toString() {
//        white chess king	♔	U+2654
//        black chess king	♚	U+265A
        return getColor() == Color.WHITE ? "\u2654" : "\u265A";
    }
}
