package Main.ChessPieces;

public class Knight extends  ChessPiece{
    public Knight (ChessPiece[][] chessBoard, Color color, int row, int column) {
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
        //   white chess knight	♘	U+2658
        // black chess knight	♞	U+265E
        return getColor() == Color.WHITE ?  "\u2658" : "\u265E";
    }
}
