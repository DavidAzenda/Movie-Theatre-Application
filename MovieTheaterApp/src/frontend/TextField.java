package frontend;

import javax.swing.*;

public class TextField {

    private JTextField text;

    public TextField() {
        this.text = new JTextField();
    }

    public JTextField getTextField() {
        return this.text;
    }

    public void setColumns(int cols) {
        this.text.setColumns(cols);
    }

    public void hideText() {
        this.text.setVisible(false);
    }

    public void displayText() {
        this.text.setVisible(true);
    }
}
