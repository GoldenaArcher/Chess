package Main.ChessPieces;

public class Queen extends ChessPiece {
    public Queen (ChessPiece[][] chessBoard, Color color, int row, int column) {
        super(chessBoard, color, row, column);
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
//        white chess queen	♕	U+2655
//        black chess queen	♛	U+265B
        return getColor() == Color.WHITE ? "\u2655" : "\u265B";
    }
}
