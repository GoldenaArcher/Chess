package Main.ChessPieces;

import Main.Chess;
import Main.ChessBoard;
import Main.IllegalPositionException;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Queen extends ChessPiece {
    public Queen(ChessBoard board, Color color) {
        super(board, color);
    }

    /*
     It can move any number of spaces in a straight line vertically, horizontally, or diagonally. It can move either forward
      or backward, and captures any enemy piece that would block its movement.

      code seemed to be very redundant, should be able to modify it
     */
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> res = new ArrayList<>();
        int tempRow = row, tempCol = column;
        ChessPiece piece = null;

        try {
            //        move to left&up
            while (row > 0 && column > 0) {
                if (validMove(0, 1) == null)
                    break;
                res.add(validMove(-1, -1));
                row -= 1;
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to left
            while (column > 0) {
                if (validMove(0, 1) == null)
                    break;
                res.add(validMove(0, -1));
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to up
            while (row > 0) {
                if (validMove(0, 1) == null)
                    break;
                res.add(validMove(-1, 0));
                row -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to left down
            while (row < 7 && column > 0) {
                if (validMove(0, 1) == null)
                    break;
                res.add(validMove(1, -1));
                row += 1;
                column -= 1;
            }
            resetRowCol(tempRow, tempCol);

//        move to right up
            while (row > 0 && column < 7) {
                if (validMove(0, 1) == null)
                    break;
                res.add(validMove(-1, 1));
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
                if (validMove(0, 1) == null)
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
                if (validMove(0, 1) == null)
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
