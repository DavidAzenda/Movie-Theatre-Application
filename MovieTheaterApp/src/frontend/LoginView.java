package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.showMessageDialog;


public class LoginView extends JFrame implements ActionListener, GUIFactory {

    Container container=getContentPane();
    Label LoginIntroLabel=createLabel("Please Enter Login Info");
    Label userLabel=createLabel("UserName");
    Label passwordLabel=createLabel("Password");
    JPasswordField passwordTextField=new JPasswordField();
    TextField userTextField=createTextField();
    Button loginButton= createButton("Login");
    Button regButton=createButton("Register");
    Button guestButton=createButton("Continue as a guest");


    public LoginView()
    {
        this.setup();
    }

    private void setup() {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

    }
    public void addRegButtonListener(ActionListener l) {
        regButton.getButton().addActionListener(l);
    }
    public void addLoginButtonListener(ActionListener l) {
        loginButton.getButton().addActionListener(l);
    }
    public void addGuestButtonListener(ActionListener l) {
        guestButton.getButton().addActionListener(l);
    }
    
    public String [] getLoginInfo() {
        String[] s =  new String[2];
        String userName = userTextField.getTextField().getText();
        String passWord = passwordTextField.getText();
        s[0] = userName;
        s[1] = passWord;
        return s;

    }
    public void showLoginSuccessful() {
        showMessageDialog(null, "Login Successful");
        //CALL SOMETHING HERE
    }
    public void showLoginFailed() {
        showMessageDialog(null, "Login Failed");
        //CALL SOMETHING HERE
    }
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        //Setting location and Size of each components using setBounds() method.
        LoginIntroLabel.getLabel().setBounds(50,100,300,30);
        userLabel.getLabel().setBounds(50,150,100,30);
        passwordLabel.getLabel().setBounds(50,220,100,30);
        userTextField.getTextField().setBounds(150,150,150,30);
        passwordTextField.setBounds(150,220,150,30);
        loginButton.getButton().setBounds(50,300,100,30);
        guestButton.getButton().setBounds(200,300,200,30);
        regButton.getButton().setBounds(50,350,350,30);


    }
    public void addComponentsToContainer()
    {
        //Adding each components to the Container
        container.add(LoginIntroLabel.getLabel());
        container.add(userLabel.getLabel());
        container.add(passwordLabel.getLabel());
        container.add(userTextField.getTextField());
        container.add(passwordTextField);
        container.add(loginButton.getButton());
        container.add(guestButton.getButton());
        container.add(regButton.getButton());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

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
 /*   public static void main(String[] a){
        LoginView frame=new LoginView();
        frame.setTitle("Movie Theater System");
        frame.setVisible(true);
        frame.setBounds(10,10,800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }*/
}
