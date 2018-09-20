package Main.View;

import javax.swing.*;
import java.awt.*;

/**
 * @author luhan
 */
// this class serves the purpose displays all the moves on the board
public class Record extends JPanel {
    public Record() {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.lightGray);
        JLabel label = new JLabel("History");
        label.setText("This is used to record moves history. Possibly implement later.");
        this.add(label);
    }
}
