package Main.ChessPieces;

import Main.ChessBoard;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Rook extends ChessPiece {
    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

//    Given the incrementation, check all possible steps until meet the edge or another piece up or down
    private ArrayList<String> upDown(int incrementation){
        ArrayList <String> res = new ArrayList<>();
        return res;
    }

    //    Given the incrementation, check all possible steps until meet the edge or another piece left or right
    private ArrayList<String> leftRight(int incrementation){
        ArrayList <String> res = new ArrayList<>();
        return res;
    }

    /*
    The Rook moves vertically or horizontally in a straight line. It can move forward or backward, left or right. Again,
    like the King, the Rook can capture any enemy piece that is in its line of movement. In capturing, the Rook replaces
    the enemy piece, which is removed from the board.
     */
    @Override
    public ArrayList<String> legalMoves() {
        ArrayList <String> res = new ArrayList<>();
        return res;
    }

    @Override
    public String toString() {
        //   White Chess Rook "♖" (U+2656)
        // Black Chess Rook "♜" (U+265C)
        return getColor() == Color.WHITE ? "\u2656" : "\u265c";
    }
}
