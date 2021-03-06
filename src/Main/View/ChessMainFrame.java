package Main.View;

import java.awt.*;
import javax.swing.*;

/**
 * @author Lu Han
 */
public class ChessMainFrame extends JFrame {

//    private JPanel[] jPanel;   // 2 panel, 1 for the game board and the other for game record
    private ChessboardFrame board;   // 2 panel, 1 for the game board and the other for game record
    private RecordGUI recordgui;

    public ChessMainFrame() {
        board = new ChessboardFrame(this);
        recordgui = new RecordGUI();
        setFrame();
    }

//    Use this method to generate a basic frame/window of the app
    private void setFrame(){
        this.setSize(1200, 800);
//        this.setLocationRelativeTo(null);   // make the window to be centered
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimension = tk.getScreenSize();

//        dimension returns the width of the screen while getWidth return the width of the window
        int xPos = dimension.width / 2 - this.getWidth() / 2;
        int yPos = dimension.height / 2 - this.getHeight() / 2;
        this.setLocation(xPos, yPos);
        this.setResizable(true);  // allow user to resize the window, by default is false
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // click close button to close the work
        this.setTitle("Chess");
        this.setIconImage(tk.getImage(this.getClass().getResource("chessMaterials/logo.png"))); // change the icon of app

//        add 2 panels into frame
        this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        this.add(board, BorderLayout.WEST);
        this.add(recordgui, BorderLayout.EAST);

        this.setVisible(true);
    }
}

