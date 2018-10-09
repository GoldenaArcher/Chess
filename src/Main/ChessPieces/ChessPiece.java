package Main.ChessPieces;

import Main.Operation.ChessBoard;
import Main.Exception.IllegalPositionException;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public abstract class ChessPiece {
     public enum Color {BLACK, WHITE}

    protected ChessBoard board;
    protected Color color;
    protected int row, column;

    private String path;    // the path of image
    private boolean moved;

    //    A piece of chess piece needs color as it's property, and position

    /**
     * constructor for individual chess
     * @param board the chessboard passed
     * @param color the color of the chess
     */
    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
        this.column = -1;
        this.row = -1;
        this.moved = false;
    }

    public String getPath() {
        return path;
    }

    void setPath(String path) {
        this.path = path;
    }

//    used by Bishop and Queen
    void resetRowCol(int row, int col) {
        this.row = row;
        this.column = col;
    }

    //    This method returns the color of the piece. There is no need for a setColor method because a piece cannot change color.
    public Color getColor() {
        return color;
    }

    //    This method returns the position of the piece in the format single letter (a..h) followed by a single digit (1..8).
    public String getPosition() {
        return "" + (char)('a' + column) + (7- row + 1);
    }

    //    This method sets the position of the piece to the appropriate row and column based on the argument, which in the format
    // single letter (a..h) followed by a single digit (1..8). If the position is illegal for any of the two reasons mentioned earlier, throw the stated exception.
    public void setPosition(String position) throws IllegalPositionException {
        int letter = position.charAt(0) - 'a';
        int digit = 7 - (position.charAt(1) - '1');
        if (!position.matches("^[a-h][1-8]$"))
            throw new IllegalPositionException("Position is illegal, it must be between a1 to h8");
        row = digit;
        column = letter;
    }

//    for now, applicable for King and Knight, so added as a protected methods for at least King and Pawn to access
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
            if (piece == null || piece.getColor() != color) {
                return position;
            }
        }
        return null;
    }

    public void move() {
        this.moved = true;
    }

    public boolean isMoved() {
        return moved;
    }

    abstract public ArrayList<String> legalMoves();

    abstract public String toString();
}
