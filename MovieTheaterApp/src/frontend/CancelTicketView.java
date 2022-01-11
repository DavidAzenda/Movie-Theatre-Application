package frontend;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;

        import static javax.swing.JOptionPane.showMessageDialog;
        import businessLogic.*;

public class CancelTicketView extends JFrame implements ActionListener, GUIFactory {

    Container container=getContentPane();

    Button returnButton;

    Label ticketLabel;
    String[] ticketInfoTitle;
    JTable ticketInfo;
    JScrollPane sp;

    Label cancelTicketLabel;
    TextField ticketIDTextField;
    Button cancelButton;
    String ticketId;

    public CancelTicketView()
    {
        this.setup();
    }

    private void setup() {
        setCancelTicketDefaultInfo();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    private void setCancelTicketDefaultInfo() {
        returnButton= createButton("← Return to Movie Selection");
        /*
        ticketLabel = createLabel("Your Ticket");
        ticketInfoTitle = new String[]{"Ticket Number","Movie Name", "Theater Name", "Showtime", "Price", "Num of Tickets", "Total Amount"};
        ticketInfo = new JTable(getTicketInfo(),ticketInfoTitle);
        sp = new JScrollPane(ticketInfo);
        ticketInfo.setDefaultEditor(Object.class, null);
         */
        cancelTicketLabel= createLabel("Enter Ticket Number");
        ticketIDTextField = createTextField();
        cancelButton= createButton("Cancel Tickets");
    }

    public void addReturnBtnListener(ActionListener l) {
        returnButton.getButton().addActionListener(l);
    }
    public void addCancelBtnListener(ActionListener l) {
        cancelButton.getButton().addActionListener(l);
    }
    public TextField getTicketIDTextField(){
        return ticketIDTextField;
    }
    public void setTicketIDTextField(String s){
        ticketIDTextField.getTextField().setText(s); ;
    }
    public String getTicketId(){
        return ticketId;
    }
    public void setTicketId(String id){
         ticketId = id;
    }
    public String[][] getTicketInfo(){
        String[][] sampleTicketInfo = {
                { "1","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "2","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "3","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "4","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "5","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "6","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "7","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "8","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
                { "9","MovName", "CalgaryTheater", "12:00" , "$10" , "10" , "100" },
        };
        return sampleTicketInfo;
    }

    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        returnButton.getButton().setBounds(50,50,200,30);
       // ticketLabel.getLabel().setBounds(50,100,200,30);
       // sp.setBounds(50,150,700,200);

        cancelTicketLabel.getLabel().setBounds(50,400,150,30);
        ticketIDTextField.getTextField().setBounds(230,400,150,30);
        cancelButton.getButton().setBounds(450,400,200,30);
    }
    public void addComponentsToContainer()
    {
        container.add(returnButton.getButton());
       // container.add(ticketLabel.getLabel());
       // container.add(sp);

        container.add(cancelTicketLabel.getLabel());
        container.add(ticketIDTextField.getTextField());
        container.add(cancelButton.getButton());

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
    @Override
    public void actionPerformed(ActionEvent e) {

    }
	public void showError(String string) {
		showMessageDialog(null, string);
		
	}

	public void showCancelled() {
		showMessageDialog(null, "Ticket Cancelled");		
	}
	public void showCredit(String s) {
		showMessageDialog(null, s);		
	}
}
