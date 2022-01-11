package frontend;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button {

    private JButton button;

    public Button(String label) {
        this.button = new JButton(label);
    }

    public JButton getButton() {
        return this.button;
    }

    public void displayButton() {
        this.button.setVisible(true);
    }

    public void hideButton() {
        this.button.setVisible(false);
    }

}
