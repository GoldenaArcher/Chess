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

    private ArrayList<String> capture(int row) {    // to check if there exists pieces can be captured diagonally
        ArrayList<String> res = new ArrayList<>();
        try {
            if (column > 0) {   // check left
                String position = "" + (char) (column + 'a' - 1) + (char) (7 - row + '1');
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getColor() != color)
                    res.add(position);
            }

            if (column < 7) { // check right
                String position = "" + (char) (column + 'a' + 1) + (char) (7 - row + '1');
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getColor() != color)
                    res.add(position);
            }
        } catch (IllegalPositionException e) {
            System.out.println("Error occurred at Pawn in capture");
        }

        return res;
    }

    //    Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn has the option of
// moving two squares forward provided both squares in front of the pawn are unoccupied.
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();
        try {   // black's index X7 -> X1 while white index Y2 -> Y8, so if black - 1 and white + 1
            int secondVal = color == Color.BLACK ? 7 - (row - '1') - 1 : 7 - (row - '1') + 1;
            String position = "" + (char) (column + 'a') + (char) secondVal;
            if (board.getPiece(position) == null)
                res.add(position);

            if (row == 1 && color == Color.BLACK) {  // black pawn can move 2 steps of it's initial move
                position = "" + (char) (column + 'a') + (char) (7 - (row - '1') - 2);
                if (board.getPiece(position) == null)
                    res.add(position);
            }

            if (row == 6 && color == Color.WHITE) {  // black pawn can move 2 steps of it's initial move
                position = "" + (char) (column + 'a') + (char) (7 - (row - '1') + 2);
                if (board.getPiece(position) == null)
                    res.add(position);
            }

            res.addAll(capture(color == Color.BLACK ? row + 1 : row - 1));

        } catch (IllegalPositionException e) {
            System.out.println("Error case in the legalMove of the pawn. Possible that white pieces is placed on X1 " +
                    "and black piece is placed on X8. Please check other implementation of the corresponding method " +
                    "which actually placed the pawn piece");
        }

        return res;
    }

    @Override
    public String toString() {
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
