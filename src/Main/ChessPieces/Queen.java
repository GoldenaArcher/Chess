package Main.ChessPieces;

import Main.Operation.ChessBoard;
import Main.Exception.IllegalPositionException;

import java.util.ArrayList;

import static Main.ChessPieces.ChessPiece.Color.BLACK;

/**
 * @author Lu Han
 */
public class Queen extends ChessPiece {
    public Queen(ChessBoard board, Color color) {
        super(board, color);
        if (color == BLACK) setPath("chessMaterials/black_queen.png");
        else setPath("chessMaterials/white_queen.png");
    }

    /*
     It can move any number of spaces in a straight line vertically, horizontally, or diagonally. It can move either forward
      or backward, and captures any enemy piece that would block its movement.
      code seemed to be very redundant, but haven't figured out a way to modify it due to different control over legal moves
     */
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();
        int tempRow = row, tempCol = column;
        ChessPiece piece;

        if (!getPosition().matches("^[a-h][1-8]$"))
            return res;

        try {
            //        move to left&up
            while (row > 0 && column > 0) {
                if (validMove(-1, -1) == null)
                    break;
                piece = board.getPiece(validMove(-1, -1));
                res.add(validMove(-1, -1));
                if (piece != null && piece.getColor() != color)
                    break;
                row -= 1;
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to left
            while (column > 0) {
                if (validMove(0, -1) == null)
                    break;
                piece = board.getPiece(validMove(0, -1));
                res.add(validMove(0, -1));
                if (piece != null && piece.getColor() != color)
                    break;
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to up
            while (row > 0) {
                if (validMove(-1, 0) == null)
                    break;
                piece = board.getPiece(validMove(-1, 0));
                res.add(validMove(-1, 0));
                if (piece != null && piece.getColor() != color)
                    break;
                row -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to left down
            while (row < 7 && column > 0) {
                if (validMove(1, -1) == null)
                    break;
                piece = board.getPiece(validMove(1, -1));
                res.add(validMove(1, -1));
                if (piece != null && piece.getColor() != color)
                    break;
                row += 1;
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to right up
            while (row > 0 && column < 7) {
                if (validMove(-1, 1) == null)
                    break;
                piece = board.getPiece(validMove(-1, 1));
                res.add(validMove(-1, 1));
                if (piece != null && piece.getColor() != color)
                    break;
                row -= 1;
                column += 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to right
            while (column < 7) {
                if (validMove(0, 1) == null)
                    break;
                piece = board.getPiece(validMove(0, 1));
                res.add(validMove(0, 1));
                if (piece != null && piece.getColor() != color)
                    break;
                column += 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to right down
            while (row < 7 && column < 7) {
                if (validMove(1, 1) == null)
                    break;
                piece = board.getPiece(validMove(1, 1));
                res.add(validMove(1, 1));
                if (piece != null && piece.getColor() != color)
                    break;
                row += 1;
                column += 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to down
            while (row < 7) {
                if (validMove(1, 0) == null)
                    break;
                piece = board.getPiece(validMove(1, 0));
                res.add(validMove(1, 0));
                if (piece != null && piece.getColor() != color)
                    break;
                row += 1;
            }
            resetRowCol(tempRow, tempCol);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public String toString() {
//        white chess queen	♕	U+2655
//        black chess queen	♛	U+265B
        return getColor() == Color.WHITE ? "\u2655" : "\u265B";
    }
}
