package Main.View;

import javax.swing.*;
import java.awt.*;

// this class set up a chess board GUI
public class Board extends JPanel {
    public Board() {
        this.setPreferredSize(new Dimension(500, 800));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
