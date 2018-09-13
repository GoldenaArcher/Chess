package Main.ChessPieces;

import Main.ChessBoard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lu Han
 */
public class Bishop extends  ChessPiece{
    public Bishop (ChessBoard board, Color color) {
        super(board, color);
    }

    private ArrayList<String> allValidMove(int rowIncrementation, int colIncrementation){
        ArrayList<String> res = new ArrayList<>();
        int tempRow = row, tempCol = column;

        row = tempRow;
        column = tempCol;
        return res;
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
//        move to left&up
        while ((tempCol -= 1) >= 0 && (tempRow -= 1) >= 0){
            res.addAll(allValidMove(-1,-1));
        }

        tempRow = row;
        tempCol = column;
//        move to right up
        while ((tempCol -= 1) >= 0 && (tempRow -= 1) >= 0){
            res.addAll(allValidMove(-1,1));
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
