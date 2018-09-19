package Main.ChessPieces;
import Main.Operation.ChessBoard;
import Main.Exception.IllegalPositionException;

import java.util.ArrayList;

import static Main.ChessPieces.ChessPiece.Color.BLACK;

/**
 * @author Lu Han
 */
public class Bishop extends  ChessPiece{
    public Bishop (ChessBoard board, Color color) {
        super(board, color);
        if (color == BLACK) setPath("./extraFiles/chessMaterials/black_bishop.png");
        else setPath("./extraFiles/chessMaterials/white_bishop.png");
    }

        /*
    Bishop can move any number of vacant squares in any diagonal direction.
    It has duplicated code w/ Queen, but since it's only used twice and only in Bishop & Queen, not planning to refactor it.
    ┌─┬─┬─┬─┬─┬─┬─┬─┐
    │  │  │×│  │×│  │  │  │
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │  │  │  │♝│  │  │  │  │
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │  │  │×│  │×│  │  │  │
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │  │×│  │  │  │×│  │  │
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │×│  │  │  │  │  │×│  │
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │  │  │  │  │  │  │  │×│
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │♙│♙│♙│♙│♙│♙│♙│♙│
    ├─┼─┼─┼─┼─┼─┼─┼─┤
    │♖│♘│♗│♕│♔│♗│♘│♖│
    └───────────────┘
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
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public String toString() {
//        black chess bishop	♝	U+265D
//        white chess bishop	♗	U+2657
        return getColor() == Color.WHITE ? "\u2657" : "\u265d";
    }
}
