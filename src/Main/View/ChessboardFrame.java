package Main.View;

import Main.ChessPieces.*;
import Main.Exception.IllegalMoveException;
import Main.Exception.IllegalPositionException;
import Main.Operation.ChessBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.showMessageDialog;
import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static Main.ChessPieces.ChessPiece.Color.BLACK;

/**
 * @author luhan
 */
// this class set up a chess board GUI, basically serve the same purpose as ChessBoard, but the CB is doing actual work as now
public class ChessboardFrame extends JPanel implements MouseListener {
    private ChessMainFrame mainFrame;
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
    protected String from;

    ChessboardFrame(ChessMainFrame mainFrame) {
        this.mainFrame = mainFrame;
        cb = new ChessBoard();
        cb.initialize();
        board = new Square[64];
        this.setPreferredSize(new Dimension(600, 800));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new GridLayout(8, 8));
        initializeSquare();
        from = "";
    }

    private void setPiece(int i, ChessPiece piece) {
        Square square = new Square(piece, pos[i]);
        board[i] = square;
    }

    private Square getSquare(int i) {
        if (i < 0)  // when call from en Passant
            return new Square(null, "-1");
        return board[i];
    }

    private void initializeSquare() {
        int count = 0, circle = 1;      // variables to keep track how to add square's color
        for (int i = 0; i < 64; i++) {  // implement each cell with the given color
            if (i == 0 || i == 7)
                setPiece(i, new Rook(cb, BLACK));
            else if (i == 63 || i == 56)
                setPiece(i, new Rook(cb, WHITE));
            else if (i == 1 || i == 6)
                setPiece(i, new Knight(cb, BLACK));
            else if (i == 62 || i == 57)
                setPiece(i, new Knight(cb, WHITE));
            else if (i == 2 || i == 5)
                setPiece(i, new Bishop(cb, BLACK));
            else if (i == 61 || i == 58)
                setPiece(i, new Bishop(cb, WHITE));
            else if (i == 3)
                setPiece(i, new Queen(cb, BLACK));
            else if (i == 59)
                setPiece(i, new Queen(cb, WHITE));
            else if (i == 4)
                setPiece(i, new King(cb, BLACK));
            else if (i == 60)
                setPiece(i, new King(cb, WHITE));
            else if (i <= 15)
                setPiece(i, new Pawn(cb, BLACK));
            else if (i >= 48)
                setPiece(i, new Pawn(cb, WHITE));
            else
                setPiece(i, null);

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

    //    JOptionPane to end/restart the game
    public void gameOver() {
        Object[] options = {"Restart", "End Game"};
        int n = JOptionPane.showOptionDialog(this,
                "Would you like restart the game?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
        if (n == 0) mainFrame = new ChessMainFrame(); // restart a new game
    }

    public String pawnPromotion() {
//        0: queen, by default people want to change to the most widely moving piece
//        1: bishop, 2: Knight, 3: Rook
        Object[] possibilities = {"queen", "bishop", "knight", "rook"};
        String option = (String) JOptionPane.showInputDialog(
                this,
                "Which piece do you want promote to",
                "Pawn Promotion",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "queen");

        return option != null ? option : "queen";
    }

    public void updatePiece(int fromPos, int toPos, ChessPiece fromPiece) { // update GUI stuffs, let GUI handle GUI
        getSquare(fromPos).removePiece();    // remove the piece from the board(from pos)
        getSquare(toPos).removePiece();
        getSquare(toPos).setPiece(fromPiece);    // add the piece to the board(to pos)
    }

//    @TODO to implement highlight function
    @Override
    public void mouseClicked(MouseEvent e) {
        Square square = (Square) e.getSource();
//        showMessageDialog(null, square.getPosition());
        String position = square.getPosition();
        try {   // when player click on an empty board as from position, it should make any change
            if (cb.getPiece(position) == null && from.equals(""))
                return;
        } catch (IllegalPositionException ignored) {
        }

        if (from.equals("")) from = position;    // then when player click on a valid position
        else {
            try {   // try to move the piece by calling CB, and let CB handle everything
                cb.move(from, position, this);
                this.revalidate();  // update the panel, mainly for repaint();
            } catch (IllegalMoveException exception) {
                showMessageDialog(null, exception.getMessage());
            } finally { // no mater player makes a valid move or not, rest start position
                from = "";
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        Code not working, maybe come back and try to fix it later
        Square square = (Square) e.getSource();

        /**
        JLabel label = new JLabel("notice");
        label.setToolTipText(square.getPosition());
        this.add(label);
        this.setVisible(true);
        **/
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
