package Main.ChessPieces;

import Main.ChessBoard;

public class Rook extends  ChessPiece{
    public Rook (ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public boolean move(String from, String to) {
        return false;
    }

    @Override
    boolean validMove(String position) {
        return false;
    }

    @Override
    public String toString() {
        //   White Chess Rook "♖" (U+2656)
        // Black Chess Rook "♜" (U+265C)
        return getColor() == Color.WHITE ? "\u2656" : "\u265c";
    }
}
