package Main.View;

import Main.ChessPieces.*;
import Main.Operation.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.showMessageDialog;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static Main.ChessPieces.ChessPiece.Color.BLACK;

// this class set up a chess board GUI, basically serve the same purpose as ChessBoard, but the CB is doing actual work as now
public class ChessboardFrame extends JPanel implements MouseListener {
    private Square[] board;
    private ChessBoard cb;
    private String[] pos = {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
            "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
            "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
            "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
            "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
            "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
            "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
            "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"};

    public ChessboardFrame() {
        cb = new ChessBoard();
        cb.initialize();
        board = new Square[64];
        this.setPreferredSize(new Dimension(600, 800));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(8, 8));
        initializeSquare();
    }

    private void setPiece(int i){
        Square square;
        if (i == 0 || i == 7)
            square = new Square(new Bishop(cb, BLACK), pos[i]);
        else if (i == 63 || i == 56)
            square = new Square(new Bishop(cb, WHITE), pos[i]);
        else if (i == 1 || i == 6)
            square = new Square(new Knight(cb, BLACK), pos[i]);
        else if (i == 62 || i == 57)
            square = new Square(new Knight(cb, WHITE), pos[i]);
        else if (i == 2 || i == 5)
            square = new Square(new Rook(cb, BLACK), pos[i]);
        else if (i == 61 || i == 58)
            square = new Square(new Rook(cb, WHITE), pos[i]);
        else if (i == 3)
            square = new Square(new Queen(cb, BLACK), pos[i]);
        else if (i == 59)
            square = new Square(new Queen(cb, WHITE), pos[i]);
        else if (i == 4)
            square = new Square(new King(cb, BLACK), pos[i]);
        else if (i == 60)
            square = new Square(new King(cb, WHITE), pos[i]);
        else if (i >= 8 && i <= 15)
            square = new Square(new Pawn(cb, BLACK), pos[i]);
        else if (i >= 48 && i <= 55)
            square = new Square(new Pawn(cb, WHITE), pos[i]);
        else
            square = new Square(pos[i]);

        board[i] = square;
    }

    private void initializeSquare(){
        int count = 0, circle = 1;      // variables to keep track how to add square's color
        for (int i = 0; i < 64; i++) {  // implement each cell with the given color
            setPiece(i);
            board[i].addMouseListener(this);
            if (count % 2 == 0) board[i].setBackground(Color.WHITE);
            else board[i].setBackground(Color.lightGray);
            this.add(board[i]);
            if (circle % 8 == 0)
                count--;
            count++;
            circle++;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        @TODO get the location of mouse clicked
        Square square = (Square) e.getSource();
        showMessageDialog(null, square.getPosition());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
