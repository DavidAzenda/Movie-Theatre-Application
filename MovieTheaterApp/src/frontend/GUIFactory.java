package frontend;

public interface GUIFactory {

    public TextField createTextField();
    public Button createButton(String label);
    public Label createLabel(String text);
}
