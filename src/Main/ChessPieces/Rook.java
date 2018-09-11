package Main.ChessPieces;

import Main.ChessBoard;
import Main.IllegalPositionException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class Rook extends ChessPiece {
    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

    private int positionCheck(String position) {    // return 0 if that place is null, 1 for different color, 2 for same color
        ChessPiece piece = null;
        try {
            piece = board.getPiece(position);
        } catch (IllegalPositionException e) {
            System.out.println("error occurred in position check, please come back and check the error");
        }

        if (piece == null)
            return 0;

        if (color != piece.getColor())      // if there is another piece and the color is different
            return 1;
        else    return 2;               // if that piece's color is same
    }

//    Given the incrementation, check all possible steps until meet the edge or another piece up or down
    private ArrayList<String> upDown(int incrementation){
        ArrayList <String> res = new ArrayList<>();
        int tempRow = row + incrementation;      // +1 moves from X8 -> X1, and -1 moves from X1 -> X8
        String position;

        while (tempRow < 8 && tempRow >= 0) {
            position = "" + (char) (column + 'a') +  (7 - tempRow + 1);
            switch (positionCheck(position)){
                default:    // case 0, position checked is null, add it and continue
                    res.add(position);
                    break;
                case 1:     // position checked is not null, with different color. That position can be captured, and return res
                    res.add(position);
                    return res;
                case 2:     // no further legal position, direct return it
                    return res;
            }
            tempRow += incrementation;
        }
        return res;
    }

    //    Given the incrementation, check all possible steps until meet the edge or another piece left or right
    private ArrayList<String> leftRight(int incrementation){
        ArrayList <String> res = new ArrayList<>();
        int tempCol = column + incrementation;   // +1 moves from AX -> HX, and -1 moves from HX -> AX
        String position;
        while (tempCol < 8 && tempCol >= 0) {
            position = "" + (char) (tempCol + 'a') + (7 - row + 1);
            switch (positionCheck(position)){
                default:    // case 0, position checked is null, add it and continue
                    res.add(position);
                    break;
                case 1:     // position checked is not null, with different color. That position can be captured, and return res
                    res.add(position);
                    return res;
                case 2:     // no further legal position, direct return it
                    return res;
            }
            tempCol += incrementation;
        }
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
        res.addAll(upDown(1));  // check from X8 -> X1
        res.addAll(upDown(-1)); // check from X1 -> X8
        res.addAll(leftRight(+1));  // check from AX -> HX
        res.addAll(leftRight(-1));  // check from HX -> AX
        return res;
    }

    @Override
    public String toString() {
        //   White Chess Rook "♖" (U+2656)
        // Black Chess Rook "♜" (U+265C)
        return getColor() == Color.WHITE ? "\u2656" : "\u265c";
    }
}
