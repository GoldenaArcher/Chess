package Main.ChessPieces;

import Main.IllegalPositionException;

public abstract class ChessPiece {
    public enum Color {BLACK, WHITE}

    protected ChessPiece[][] board;
    protected Color color;
    protected int row, column;
    private char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    //    A piece of chess piece needs color as it's property, and position
    public ChessPiece(ChessPiece[][] board, Color color, int row, int column) {
        this.board = board;
        this.color = color;
        this.row = row;
        this.column = column;
    }

    //    This method returns the color of the piece. There is no need for a setColor method because a piece cannot change color.
    public Color getColor() {
        return color;
    }

    //    This method returns the position of the piece in the format single letter (a..h) followed by a single digit (1..8).
    public String getPosition() {
        return "" + (char)('a' + column) + (row + 1);
    }

    //    This method sets the position of the piece to the appropriate row and column based on the argument, which in the format
    // single letter (a..h) followed by a single digit (1..8). If the position is illegal for any of the two reasons mentioned earlier, throw the stated exception.
    public void setPosition(String position) throws IllegalPositionException {
        int letter = position.charAt(0) - 'a';
        int digit = position.charAt(1) - 1;
        if (letter > 7 || letter < 0 || digit < 0 || digit > 7)
            throw new IllegalPositionException("Position is illegal, it must be between a1 to h8");
        row = digit;
        column = letter;
    }

    abstract public boolean move(String from, String to);

    abstract boolean validMove(String position);

    abstract public String toString();
}
