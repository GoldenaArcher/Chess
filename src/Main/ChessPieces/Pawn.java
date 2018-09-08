package Main.ChessPieces;

public class Pawn extends ChessPiece {
    public Pawn (ChessPiece[][] chessBoard, Color color, int row, int column) {
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
//        white chess pawn	♙	U+2659
        // BLACK CHESS PAWN' (U+265F)
        return getColor() == Color.WHITE ? "\u2659" : "\u265f";
    }
}
