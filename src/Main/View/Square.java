package Main.View;

import Main.ChessPieces.ChessPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author luhan
 */
// this represents an individual square that will be placed on the ChessboardFrame class
public class Square extends JPanel{
    private String position;
    private JLabel label;

    private ChessPiece piece;

    public Square(ChessPiece piece, String pos) {
        setPiece(piece);
        this.position = pos;
    }

    public String getPosition(){
        return position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

//    to put the image on the board
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
        // set background picture icon
        if (piece != null) {
            this.position = piece.getPosition();
            ImageIcon image = new javax.swing.ImageIcon(getClass().getResource(piece.getPath()));
            label = new JLabel();
            label.setIcon(image);
            this.add(label);
        }
    }

    public void removePiece(){
        if (piece == null)
            return;
        piece = null;
        this.remove(label);
    }
}
