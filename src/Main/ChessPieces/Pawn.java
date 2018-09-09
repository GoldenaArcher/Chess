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

//    @TODO implement En Passant after getting stack to work of recording all the movement
//    special moves are En Passant and Pawn promotion
//    A pawn, attacking a square crossed by an opponent's pawn which has [just] been advanced two squares in one move from
// its original square, may capture this opponent's pawn as though the latter had been moved only one square. This capture
// may only be made in [immediate] reply to such an advance, and is called an "en passant" capture.
    private void enPassant(){}

//    On reaching the last rank, a pawn must immediately be exchanged, as part of the same move, for [either] a queen, a
// rook, a bishop, or a knight, of the same colour as the pawn, at the player's choice and without taking into account
// the other pieces still remaining on the chessboard. The effect of the promoted piece is immediate and permanent!
    private void promotion(){}

//    Pawns can move forward one square, if that square is unoccupied. If it has not yet moved, the pawn has the option of
// moving two squares forward provided both squares in front of the pawn are unoccupied.
    @Override
    public ArrayList<String> legalMoves() {
        String frontPosition = "" + (char)(column + 'a');
        if (color == Color.BLACK){
            frontPosition += 7 - (row - '1');
        } else {

        }
        return null;
    }

    @Override
    public String toString() {
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
