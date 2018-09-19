package Main.View;

import Main.ChessPieces.ChessPiece;

import javax.swing.*;

// this represents an individual square that will be placed on the ChessboardFrame class
public class Square extends JPanel{
    private String position;

    private ChessPiece piece;
    //    private Square at(int row, int col) {
//        return board[row * COLS + col];
//    }

    public Square(String pos) {
//        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this(null, pos);
    }

    public Square(ChessPiece piece, String pos) {
        if (piece != null)  setPiece(piece);
        this.position = pos;
    }

    public String getPosition(){
        return position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        // set background picture icon
        ImageIcon image = new javax.swing.ImageIcon(piece.getPath());
        JLabel label = new JLabel();
        label.setIcon(image);
        this.add(label);
    }
}
