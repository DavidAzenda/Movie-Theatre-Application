package frontend;

import javax.swing.*;
import java.awt.*;

public class Label {

    private JLabel label;

    public Label(String text) {
        this.label = new JLabel(text);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void hideLabel() {
        this.label.setVisible(false);
    }

    public void displayLabel() {
        this.label.setVisible(true);
    }

    public void changeColour(Color c) {
        this.label.setForeground(c);
    }
}
