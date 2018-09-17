package Main.View;

import java.awt.Dimension;  // able to store heights and widths
import java.awt.Toolkit;    // ask different questions of operating system
import javax.swing.*;       // all different frames of GUI

/**
 * @author Lu Han
 */

public class ChessboardMainFrame extends JFrame {

//    most of the code came from Derek Banas's tutorial of Java Swing
//    the video's link is https://www.youtube.com/watch?v=3XB3in9Xqy8&list=PLfyq5A05w62_verkKr3DWKWbttvdgexH3
//    the blog's link is http://www.newthinktank.com/2012/02/java-video-tutorial-20/
    public ChessboardMainFrame() {
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

        this.setVisible(true);
    }
}
