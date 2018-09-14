package Main.ChessPieces;
import Main.ChessBoard;
import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Bishop extends  ChessPiece{
    public Bishop (ChessBoard board, Color color) {
        super(board, color);
    }

        /*
    Bishop can move any number of vacant squares in any diagonal direction.
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

        if (!getPosition().matches("^[a-h][1-8]$"))
            return res;

        //        move to left&up
        while (row > 0 && column > 0){
            res.add(validMove(-1, -1));
            row -= 1;
            column -= 1;
        }
        resetRowCol(tempRow, tempCol);

//        move to left down
        while (row < 7 && column > 0) {
            res.add(validMove(1, -1));
            row += 1;
            column -= 1;
        }
        resetRowCol(tempRow, tempCol);

//        move to right up
        while (row > 0 && column < 7) {
            res.add(validMove(-1, 1));
            row -= 1;
            column += 1;
        }
        resetRowCol(tempRow, tempCol);

//        move to right down
        while (row < 7 && column < 7) {
            res.add(validMove(1, 1));
            row += 1;
            column += 1;
        }
        resetRowCol(tempRow, tempCol);
        return res;
    }

    @Override
    public String toString() {
//        black chess bishop	♝	U+265D
//        white chess bishop	♗	U+2657
        return getColor() == Color.WHITE ? "\u2657" : "\u265d";
    }
}
