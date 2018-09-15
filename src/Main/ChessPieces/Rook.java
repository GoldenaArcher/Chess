package Main.ChessPieces;

import Main.ChessBoard;
import Main.IllegalPositionException;

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
        else return 2;               // if that piece's color is same
    }

    //    Merge both methods(upDown & leftRight) to reduce repeated code
//    The idea is simple, only need to check all the way to left, right, up, down. So pass 1 parameter for manipulation
//    of column, 1 parameter for row. If check up, pass -1 for check up, -1 for down, +1 for right, -1 for left
    private ArrayList<String> findLegalMoves(int rowIncrementation, int colIncrementation) {
        ArrayList<String> res = new ArrayList<>();
        int tempRow = row + rowIncrementation;      // +1 moves from X8 -> X1, and -1 moves from X1 -> X8
        int tempCol = column + colIncrementation;   // +1 moves from AX -> HX, and -1 moves from HX -> AX

        String position;

        while (tempRow < 8 && tempRow >= 0 && tempCol < 8 && tempCol >= 0) {
            position = "" + (char) (tempCol + 'a') + (7 - tempRow + 1);
            switch (positionCheck(position)) {
                default:    // case 0, position checked is null, add it and continue
                    res.add(position);
                    break;
                case 1:     // position checked is not null, with different color. That position can be captured, and return res
                    res.add(position);
                    return res;
                case 2:     // no further legal position, direct return it
                    return res;
            }
            tempRow += rowIncrementation;
            tempCol += colIncrementation;
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
        ArrayList<String> res = new ArrayList<>();

        if (!getPosition().matches("^[a-h][1-8]$"))
            return res;

        res.addAll(findLegalMoves(1, 0));  // check from X8 -> X1
        res.addAll(findLegalMoves(-1, 0)); // check from X1 -> X8
        res.addAll(findLegalMoves(0, 1));  // check from AX -> HX
        res.addAll(findLegalMoves(0, -1));  // check from HX -> AX
        return res;
    }

    @Override
    public String toString() {
        //   White Chess Rook "♖" (U+2656)
        // Black Chess Rook "♜" (U+265C)
        return getColor() == Color.WHITE ? "\u2656" : "\u265c";
    }
}
