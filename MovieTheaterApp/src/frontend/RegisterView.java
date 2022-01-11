package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;


public class RegisterView extends JFrame implements GUIFactory {

    Container container=getContentPane();
    Label LoginIntroLabel=createLabel("Please Enter register Info");
    Label userLabel=createLabel("UserName");
    Label passwordLabel=createLabel("Password");
    Label emailLabel=createLabel("Email");
    JPasswordField passwordTextField=new JPasswordField();
    TextField userTextField=createTextField();
    TextField emailTextField=createTextField();

    Label fNameLabel=createLabel("First Name");
    Label lNameLabel=createLabel("Last Name");
    Label addressLabel=createLabel("Address");
    Label cardNumLabel=createLabel("Card Number");
    Label expiryDateLabel=createLabel("Expiry Date(mm/yy)");
    Label cvvLabel=createLabel("CVV");


    TextField fNameTextField=createTextField();
    TextField lNameTextField=createTextField();
    TextField addressTextField=createTextField();
    TextField cardNumTextField=createTextField();
    TextField expiryDateTextField=createTextField();
    TextField cvvTextField=createTextField();

    Button submitButton= createButton("Submit & Pay Annual Fee");
    Button goBackButton= createButton("Go Back");


    public RegisterView()
    {
        this.setup();
    }

    private void setup() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }
    public void addSubmitButtonListener(ActionListener l) {
        submitButton.getButton().addActionListener(l);
    }
    public void addGoBackButtonListener(ActionListener l) {
        goBackButton.getButton().addActionListener(l);
    }

    public String [] getRegInfo() {
        String[] s =  new String[9];;
        String userName = userTextField.getTextField().getText();
        String passWord = passwordTextField.getText();
        String email = emailTextField.getTextField().getText();
        s[0] = userName;
        s[1] = passWord;
        s[2] = email;
        s[3] = fNameTextField.getTextField().getText();
        s[4] = lNameTextField.getTextField().getText();
        s[5] = addressTextField.getTextField().getText();
        s[6] = cardNumTextField.getTextField().getText();
        s[7] = expiryDateTextField.getTextField().getText();
        s[8] = cvvTextField.getTextField().getText();
        return s;
    }
    public void showRegSuccessful() {
        showMessageDialog(null, "Register Successful");
    }
    public void showRegFailed() {
        showMessageDialog(null, "Register Failed (Username used)!");
    }

    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {

        LoginIntroLabel.getLabel().setBounds(50,50,300,30);
        userLabel.getLabel().setBounds(50,100,100,30);
        passwordLabel.getLabel().setBounds(50,170,100,30);
        emailLabel.getLabel().setBounds(50,240,100,30);

        userTextField.getTextField().setBounds(150,100,150,30);
        passwordTextField.setBounds(150,170,150,30);
        emailTextField.getTextField().setBounds(150,240,150,30);

        fNameLabel.getLabel().setBounds(350,100,100,30);
        lNameLabel.getLabel().setBounds(350,170,100,30);
        addressLabel.getLabel().setBounds(350,240,100,30);
        cardNumLabel.getLabel().setBounds(350,310,100,30);
        expiryDateLabel.getLabel().setBounds(350,380,100,30);
        cvvLabel.getLabel().setBounds(350,450,100,30);

        fNameTextField.getTextField().setBounds(450,100,150,30);
        lNameTextField.getTextField().setBounds(450,170,150,30);
        addressTextField.getTextField().setBounds(450,240,150,30);
        cardNumTextField.getTextField().setBounds(450,310,150,30);
        expiryDateTextField.getTextField().setBounds(450,380,150,30);
        cvvTextField.getTextField().setBounds(450,450,150,30);

        submitButton.getButton().setBounds(100,520,500,30);
        goBackButton.getButton().setBounds(100,560,500,30);

    }
    public void addComponentsToContainer()
    {
        container.add(LoginIntroLabel.getLabel());
        container.add(userLabel.getLabel());
        container.add(passwordLabel.getLabel());
        container.add(emailLabel.getLabel());
        container.add(userTextField.getTextField());
        container.add(passwordTextField);
        container.add(emailTextField.getTextField());

        container.add(fNameLabel.getLabel());
        container.add(lNameLabel.getLabel());
        container.add(addressLabel.getLabel());
        container.add(cardNumLabel.getLabel());
        container.add(expiryDateLabel.getLabel());
        container.add(cvvLabel.getLabel());

        container.add(fNameTextField.getTextField());
        container.add(lNameTextField.getTextField());
        container.add(addressTextField.getTextField());
        container.add(cardNumTextField.getTextField());
        container.add(expiryDateTextField.getTextField());
        container.add(cvvTextField.getTextField());

        container.add(submitButton.getButton());
        container.add(goBackButton.getButton());
    }

    @Override
    public Button createButton(String label) {
        return new Button(label);
    }

    @Override
    public Label createLabel(String text) {
        return new Label(text);
    }
    @Override
    public TextField createTextField() {
        return new TextField();
    }
 
}
