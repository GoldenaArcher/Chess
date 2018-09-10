package Main.ChessPieces;

import Main.ChessBoard;
import Main.IllegalPositionException;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Pawn extends ChessPiece {
    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    //    @TODO implement En Passant after getting stack to work of recording all the movement
//    special moves are En Passant and Pawn promotion
//    A pawn, attacking a square crossed by an opponent's pawn which has [just] been advanced two squares in one move from
// its original square, may capture this opponent's pawn as though the latter had been moved only one square. This capture
// may only be made in [immediate] reply to such an advance, and is called an "en passant" capture.
    private void enPassant() {
    }

//    @TODO needed to be implemented
    //    On reaching the last rank, a pawn must immediately be exchanged, as part of the same move, for [either] a queen, a
// rook, a bishop, or a knight, of the same colour as the pawn, at the player's choice and without taking into account
// the other pieces still remaining on the chessboard. The effect of the promoted piece is immediate and permanent!
    private void promotion() {
        System.out.println("Pawn promotion");
    }

    private ArrayList<String> capture(ArrayList<String> list){
        return list;
    }

//    @TODO cannot throw exception here, and therefore the method fails to achieve it's goal
    //    Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn has the option of
// moving two squares forward provided both squares in front of the pawn are unoccupied.
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();
        if (color == Color.BLACK){  // only need to check from up to down since pawn cannot move backward
//            then need to check all the pieces all the way until reach the bottom
            if (row == 7)   // reach the end of the row
                promotion();
        } else {    // only need to check from bottom to up since pawn cannot move backward}
            if (row == 0)
                promotion();
        }
        /*
//        need to check 4 position for pawn
        try {
            String frontPosition = "" + (char) (column + 'a');   // the column is always the same, but row can be differed
            frontPosition += color == Color.BLACK ? 7 - (row - '1') + 1 : 7 - (row - '1') - 1;
            if (board.getPiece(frontPosition) == null)
                res.add(frontPosition);
//            check if pawn can move 2 instead of 1 -> the 1st move
            if ((color == Color.BLACK && column == 1) || (color == Color.WHITE && column == 6)) {
                frontPosition = "" + (char) (column + 'a');
                frontPosition += color == Color.BLACK ? 7 - (row - '1') + 2 : 7 - (row - '1') - 2;
                if (board.getPiece(frontPosition) == null)
                    res.add(frontPosition);
            }
//            check if pawn can move diagonally, first to the right
            frontPosition = "" + (char) (column + 'a') + 1;
            frontPosition += color == Color.BLACK ? 7 - (row - '1') + 1 : 7 - (row - '1') - 1;
            if (board.getPiece(frontPosition) != null && color != board.getPiece(frontPosition).getColor())
                res.add(frontPosition);
//            Then to the left
            frontPosition = "" + (char) ((column + 'a') - 1);
            frontPosition += color == Color.BLACK ? 7 - (row - '1') + 1 : 7 - (row - '1') - 1;
            if (board.getPiece(frontPosition) != null && color != board.getPiece(frontPosition).getColor())
                res.add(frontPosition);
        } catch (IllegalPositionException e) {
            System.out.println("Meeting edge case in Pawn' legalMoves method");
        }
        */

        return res;
    }

    @Override
    public String toString() {
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
