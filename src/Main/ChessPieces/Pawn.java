package Main.ChessPieces;

import Main.Operation.ChessBoard;
import Main.Exception.IllegalPositionException;
import Main.Operation.Record;

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

//    special moves are En Passant and Pawn promotion
//    A pawn, attacking a square crossed by an opponent's pawn which has [just] been advanced two squares in one move from
// its original square, may capture this opponent's pawn as though the latter had been moved only one square. This capture
// may only be made in [immediate] reply to such an advance, and is called an "en passant" capture.
    public boolean enPassant(String to) {
        Record record = board.getRecord();
//        if it's initial move, then it could not possibly be en Passant
        if (record.size() == 0)
            return false;
//        the order is: fromPiece, toPiece, from, to
        Object[] lastRecord = record.lastMove();
        ChessPiece piece = (ChessPiece) lastRecord[0];
        String lastFrom = (String) lastRecord[2];
        String lastTo = (String) lastRecord[3];

//        if last move is not opponent's pawn move
        if (!(piece instanceof Pawn && piece.color != this.color))
            return false;
//        if last move's piece is not adjacent to the current moving piece
        if (piece.row + 1 == row && piece.row - 1 == row) {
            System.out.println(piece.row);
            return false;
        }
//        if opponent's isn't just moved 2 squares, which guaranteed that's pawn's 1st move
        if (Math.abs(lastFrom.charAt(1) - lastTo.charAt(1)) != 2)
            return false;
//        if that is not a en Passant, just regular straightforward move
        if (to.charAt(0) != lastTo.charAt(0))
            return false;
        return true;
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
