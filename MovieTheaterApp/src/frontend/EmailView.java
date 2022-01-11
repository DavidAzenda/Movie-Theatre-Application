package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;
import businessLogic.*;

public class EmailView extends JFrame implements ActionListener, GUIFactory {

    Container container=getContentPane();

    Button returnButton= createButton("Return to paymentView");

    Label ticketLabel;
    String[] ticketInfoTitle;
    JTable ticketInfo;
    JScrollPane sp;

    Label receiptLabel;
    String[] receiptInfoTitle;
    JTable receiptInfo;
    JScrollPane rsp;

    String[][] TicketInfoFromDB;
    String[][] receiptInfoFromDB;
    public EmailView()
    {
        //this.setup();
    }

    public void setup() {
        setEmailDefaultInfo();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
    }

    private void setEmailDefaultInfo() {
        ticketLabel = createLabel("Your Ticket");
        ticketInfoTitle = new String[]{"Ticket Number","Movie Name", "Theater Name","Showtime","Seat", "Price"};
        ticketInfo = new JTable(TicketInfoFromDB,ticketInfoTitle);
        sp = new JScrollPane(ticketInfo);
        ticketInfo.setDefaultEditor(Object.class, null);

        receiptLabel = createLabel("Your Receipt");
        receiptInfoTitle = new String[]{"Receipt ID", "Price"};
        receiptInfo = new JTable(receiptInfoFromDB,receiptInfoTitle);
        rsp = new JScrollPane(receiptInfo);
        receiptInfo.setDefaultEditor(Object.class, null);

    }
    public void addReturnBtnListener(ActionListener l) {
        returnButton.getButton().addActionListener(l);
    }

    public void setReceiptInfo(String[][] info) {
    	receiptInfoFromDB = info;
    	container.removeAll();
    	setEmailDefaultInfo();
    	setLocationAndSize();
    	addComponentsToContainer();
    	container.revalidate();
    	container.repaint();
    }

    public void setTicketInfoFromDB(String[][] info){
        TicketInfoFromDB = info;
    }

    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        returnButton.getButton().setBounds(50,50,200,30);
        ticketLabel.getLabel().setBounds(50,100,200,30);
        sp.setBounds(50,150,700,200);

        receiptLabel.getLabel().setBounds(50,350,200,30);
        rsp.setBounds(50,400,700,100);
    }
    public void addComponentsToContainer()
    {
        container.add(returnButton.getButton());
        container.add(ticketLabel.getLabel());
        container.add(sp);

        container.add(receiptLabel.getLabel());
        container.add(rsp);
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
}
