package Main.ChessPieces;

import Main.Operation.ChessBoard;
import Main.Exception.IllegalPositionException;

import java.util.ArrayList;
import java.util.Collections;

import static Main.ChessPieces.ChessPiece.Color.BLACK;

/**
 * @author Lu Han
 */
public class Pawn extends ChessPiece {
    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        if (color == BLACK) setPath("chessMaterials/black_pawn.png");
        else setPath("chessMaterials/white_pawn.png");
    }


    //    @TODO needed to be implemented
    //    On reaching the last rank, a pawn must immediately be exchanged, as part of the same move, for [either] a queen, a
// rook, a bishop, or a knight, of the same colour as the pawn, at the player's choice and without taking into account
// the other pieces still remaining on the chessboard. The effect of the promoted piece is immediate and permanent!
    private void pawnPromotion(){}

    //    @TODO implement En Passant after getting stack to work of recording all the movement
//    special moves are En Passant and Pawn promotion
//    A pawn, attacking a square crossed by an opponent's pawn which has [just] been advanced two squares in one move from
// its original square, may capture this opponent's pawn as though the latter had been moved only one square. This capture
// may only be made in [immediate] reply to such an advance, and is called an "en passant" capture.
    private void enPassant() {
    }

//    capture is different for pawn because it attacks out of it's moving range
    private ArrayList<String> capture(int rowIncrementation) {    // to check if there exists pieces can be captured diagonally
        ArrayList<String> res = new ArrayList<>();
        int tempRow = row + rowIncrementation;

        try {
            if (column > 0) {   // check left
                int tempCol = column - 1;
                String position = "" + (char) (tempCol + 'a') + (char) (7 - tempRow + '1');
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getColor() != color)
                    res.add(position);
            }

            if (column < 7) { // check right
                int temCol = column + 1;
                String position = "" + (char) (temCol + 'a') + (char) (7 - tempRow + '1');
                ChessPiece piece = board.getPiece(position);
                if (piece != null && piece.getColor() != color)
                    res.add(position);
            }
        } catch (IllegalPositionException e) {
            System.out.println("Error occurred at Pawn in capture");
        }

        return res;
    }

//    need to override this since the pawn can only move its piece when that position is null
    @Override
    String validMove(int rowIncrementation, int colIncrementation){
        int tempRow = row + rowIncrementation, tempCol = column + colIncrementation;
        if (tempCol <8 && tempCol >= 0 && tempRow < 8 && tempRow >= 0) {
            String position = "" + (char) (tempCol + 'a') +  (7 - tempRow + 1);
            ChessPiece piece = null;
            try {
                piece = board.getPiece(position);
            } catch (IllegalPositionException e) {
                System.out.println("error occurred in position check, please come back and check the error");
            }
            if (piece == null) {
                return position;
            }
        }
        return null;
    }

    //    Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn has the option of
// moving two squares forward provided both squares in front of the pawn are unoccupied.
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();

        if (!getPosition().matches("^[a-h][1-8]$"))
            return res;

//        Add the position in the front
        int rowImplementation = color == Color.BLACK ? 1 : -1;
        res.add(validMove(rowImplementation,0 ));

//        Check 2 position in the front if it's at initial position
        if (row == 1 && color == Color.BLACK)   // black pawn can move 2 steps of it's initial move
            res.add(validMove(2, 0));
        if (row == 6 && color == Color.WHITE)   // white pawn can move 2 steps of it's initial move
            res.add(validMove(-2,0 ));

        res.removeAll(Collections.singleton(null));

//        Check available captured pieces
        res.addAll(capture(rowImplementation));

        return res;
    }

    @Override
    public String toString() {
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
