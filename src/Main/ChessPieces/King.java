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

    /*
Once during a game, a player may move his King two squares to the right or left and move the Rook on that side (the
Rook toward which the King moved) over the King to the square adjacent to the King.

There are five conditions that must be met in order to castle:

The squares between the King and the Rook used for castling must be empty. You can’t castle over your own or enemy pieces!
The King may not castle into or through check. If the King would be in check at the end of the castling move, or if the
square that the King passes over is guarded by an enemy piece, then castling is not allowed.
The King may not castle out of check.
The King may not have made a previous move. If the King has moved (even if it subsequently moved back to its home square),
castling is not permitted.
If one of the Rooks has moved, castling is prohibited with that Rook. Castling would still be permitted with the other Rook,
provided it has not moved and the other conditions are met.s
*/
//    TODO needed to be implemented
    private void castling() {}

//    The King can move (or capture) one square in any direction
    private String basicMove(int rowIncrementation, int colIncrementation) {
        int tempRow = row + rowIncrementation, tempCol = column + colIncrementation;
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
