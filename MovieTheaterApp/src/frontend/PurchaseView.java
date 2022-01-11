package frontend;

import javax.swing.*;

import clients.clientModel.PaymentInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class PurchaseView extends JFrame implements GUIFactory {

    Container container=getContentPane();

    Label numOfTicketsLabel,movieNameLabel,theaterLabel,showtimeLabel,priceLabel;
    TextField numbOfTicketsTextField,movieNameTextField,theaterTextField,showtimeTextField,priceTextField;

    Label couponLabel,totalAmountLabel;
    TextField couponTextField,couponResultTextField,totalAmountTextField;

    Button applyCouponBtn,enterPaymentBtn,confirmPaymentBtn, emailBtn, returnBtn;

    String coupon=null;
    String movieName,theaterName,showtime;
    int numofTickets;
    double price, totalAmount;
    private List<String> selectedSeats = new ArrayList<String>();
    
    public void reset() {
    	numofTickets =0;
    	price = 0;
    	totalAmount = 0;
    	selectedSeats = new ArrayList<String>();
    }
    // store credit card info when a user click on enterPaymentBtn
    JTextField payFirstNameTextField= new JTextField();
    JTextField payLastNameTextField= new JTextField();
    JTextField payAddressTextField= new JTextField();
    JTextField cardNumTextField= new JTextField();
    JTextField expiryTextField= new JTextField();
    JTextField cvvTextField= new JTextField();
    Object[] paymentinfo = {
            "First Name:", payFirstNameTextField,
            "Last Name:", payLastNameTextField,
            "Address:", payAddressTextField,
            "CardNum:", cardNumTextField,
            "ExpiryDate (mm/yy):",expiryTextField,
            "CVV:", cvvTextField,
    };
    // condition for payment info (for backend)
    //payInfo == JOptionPane.OK_OPTION
    /*
    if (payInfo == JOptionPane.OK_OPTION) {
        if (check/store payment info) {
            System.out.println(" successful");
        } else {
            System.out.println(" failed");
        }
    } else {
        System.out.println("canceled");
    }*/
    int payInfo;
    // the data is passed from SeatView & MovieSelectionView
    public PurchaseView(int numofTickets, String movieName, String theaterName, String showtime, int price, List<String> selectedSeats)
    {
    	this.numofTickets = numofTickets;
        this.movieName = movieName; 
        this.theaterName = theaterName;
        this.showtime = showtime;
        this.price = price;
        this.selectedSeats = selectedSeats;
        this.setup();

        enterPaymentBtn.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentInput();
            }
        });


    }
    public String [] getPurchaseInfo() {
        String[] s =  new String[5];;
        s[0] = String.valueOf(numofTickets);
        s[1] = movieName;
        s[2] = theaterName;
        s[3] = showtime;
        s[4] = String.valueOf(totalAmount);
        return s;
    }
    public String [] getPaymentInfo() {
        String[] s =  new String[6];;
        s[0] = payFirstNameTextField.getText();
        s[1] = payLastNameTextField.getText();
        s[2] = payAddressTextField.getText();
        s[3] = cardNumTextField.getText();
        s[4] = expiryTextField.getText();
        s[5] = cvvTextField.getText();
        return s;
    }
    public void setPaymentInfo(String [] s) {
         payFirstNameTextField.setText(s[0]);
         payLastNameTextField.setText(s[1]);
         payAddressTextField.setText(s[2]);
         cardNumTextField.setText(s[3]);
         expiryTextField.setText(s[4]);
         cvvTextField.setText(s[5]);
    }

    private void setup() {
        createElements();
        setNotEditable();
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

    }
    public void createElements(){
        numOfTicketsLabel= createLabel("Number of Tickets");
        numbOfTicketsTextField=createTextField();
        numbOfTicketsTextField.getTextField().setText(String.valueOf(numofTickets));

        movieNameLabel=createLabel("Movie Name");
        movieNameTextField=createTextField();
        movieNameTextField.getTextField().setText(movieName);

        theaterLabel=createLabel("Theater Name");
        theaterTextField=createTextField();
        theaterTextField.getTextField().setText(theaterName);

        showtimeLabel=createLabel("Showtime");
        showtimeTextField=createTextField();
        showtimeTextField.getTextField().setText(showtime);

        priceLabel=createLabel("Price");
        priceTextField=createTextField();
        priceTextField.getTextField().setText(String.valueOf(price));

        couponLabel=createLabel("Enter Coupon Code");
        couponTextField=createTextField();
        couponResultTextField=createTextField();

        totalAmountLabel=createLabel("Total Amount");
        totalAmountTextField=createTextField();
        calculateTotalAmount();

        applyCouponBtn= createButton("Apply Coupon");
        enterPaymentBtn= createButton("Enter Payment Info");
        confirmPaymentBtn= createButton("Confirm Payment");
        emailBtn= createButton("Email Receipt/Ticket");

        returnBtn= createButton("Return to Movie Selection");

       /*
        payNameTextField=createTextField();
        payAddressTextField=createTextField();
        cardNumTextField=createTextField();
        expiryTextField=createTextField();
        cvvTextField=createTextField();
         */
    }

    private void calculateTotalAmount() {
        if (coupon != null){
            // * or -     negative value?
            //totalAmount =  price * Integer.parseInt(coupon) * numofTickets;
            totalAmount =  price * numofTickets - Double.parseDouble(coupon) ;
        }else{
            totalAmount = price * numofTickets;
        }
        totalAmountTextField.getTextField().setText(String.valueOf(totalAmount));
    }
    public void setCoupon(String coupon){
        if (coupon!=null) {
            this.coupon = coupon;
        	setCouponResultTextField(coupon);
        	//calculateTotalAmount();
        }
    }
    public String getTotal() {
    	String s = priceTextField.getTextField().getText();
    	if(s!= "") {
    		return s;
    	}
    	return null;
    }

    public String getCouponCode() {
    	return couponTextField.getTextField().getText();
    }
    public void setNotEditable(){
        numbOfTicketsTextField.getTextField().setEditable(false);
        movieNameTextField.getTextField().setEditable(false);
        theaterTextField.getTextField().setEditable(false);
        showtimeTextField.getTextField().setEditable(false);
        priceTextField.getTextField().setEditable(false);
        couponResultTextField.getTextField().setEditable(false);
        totalAmountTextField.getTextField().setEditable(false);
    }
    public void setCouponResultTextField(String result){
        couponResultTextField.getTextField().setText(result);
    }
    public void applyCouponBtnListener(ActionListener l) {
        applyCouponBtn.getButton().addActionListener(l);
    }
    public void enterPaymentBtnListener(ActionListener l) {
        enterPaymentBtn.getButton().addActionListener(l);
    }
    public void emailBtnListener(ActionListener l) {
        emailBtn.getButton().addActionListener(l);
    }
    public void addreturnBtnListener(ActionListener l) {
        returnBtn.getButton().addActionListener(l);
    }
    public void addConfirmPaymentBtnListener(ActionListener l) {
        confirmPaymentBtn.getButton().addActionListener(l);
    }

    public void showPaymentInput() {
        payInfo = JOptionPane.showConfirmDialog(null, paymentinfo, "Enter Credit Card Info", JOptionPane.OK_CANCEL_OPTION);
        System.out.println();
        /* test for the value
        if (payInfo == JOptionPane.OK_OPTION) {
            System.out.println("payinfo: " + paymentinfo.getClass());
            String s[] = getPaymentInfo();
            System.out.println("payinfo: " + s[0] +"\n"+ s[1]+"\n" + s[2] +"\n"+ s[3]+"\n" + s[4]);
        } else {
            System.out.println("canceled");
        }
        */
    }


    public void showPaymentSuccessful() {
        showMessageDialog(null, "Payment Successfully Processed");
    }
    public void showPaymentFailed() {
        showMessageDialog(null, "Payment Failed!");
    }
	public void showCreditError() {
		  showMessageDialog(null, "Error with Credit!");
	}
	public void showMessage(String string) {
		showMessageDialog(null, string);
	}
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {
        Container container=getContentPane();

        numOfTicketsLabel.getLabel().setBounds(50,100,100,30);
        movieNameLabel.getLabel().setBounds(50,150,100,30);
        theaterLabel.getLabel().setBounds(50,200,100,30);
        showtimeLabel.getLabel().setBounds(50,250,100,30);
        priceLabel.getLabel().setBounds(50,300,100,30);

        numbOfTicketsTextField.getTextField().setBounds(150,100,150,30);
        movieNameTextField.getTextField().setBounds(150,150,150,30);
        theaterTextField.getTextField().setBounds(150,200,150,30);
        showtimeTextField.getTextField().setBounds(150,250,150,30);
        priceTextField.getTextField().setBounds(150,300,150,30);

        couponLabel.getLabel().setBounds(400,100,150,30);
        applyCouponBtn.getButton().setBounds(400,150,150,30);
        totalAmountLabel.getLabel().setBounds(400,200,150,30);

        couponTextField.getTextField().setBounds(600,100,150,30);
        couponResultTextField.getTextField().setBounds(600,150,150,30);
        totalAmountTextField.getTextField().setBounds(600,200,150,30);

        enterPaymentBtn.getButton().setBounds(80,350,150,30);
        confirmPaymentBtn.getButton().setBounds(280,350,150,30);
        emailBtn.getButton().setBounds(480,350,150,30);
        returnBtn.getButton().setBounds(680,350,200,30);

    }
    public void addComponentsToContainer()
    {
        container.add(numOfTicketsLabel.getLabel());
        container.add(movieNameLabel.getLabel());
        container.add(theaterLabel.getLabel());
        container.add(showtimeLabel.getLabel());
        container.add(priceLabel.getLabel());
        container.add(showtimeLabel.getLabel());

        container.add(numbOfTicketsTextField.getTextField());
        container.add(movieNameTextField.getTextField());
        container.add(theaterTextField.getTextField());
        container.add(showtimeTextField.getTextField());
        container.add(priceTextField.getTextField());

        container.add(couponLabel.getLabel());
        container.add(totalAmountLabel.getLabel());

        container.add(couponTextField.getTextField());
        container.add(couponResultTextField.getTextField());
        container.add(totalAmountTextField.getTextField());

        container.add(applyCouponBtn.getButton());
        container.add(enterPaymentBtn.getButton());
        container.add(confirmPaymentBtn.getButton());
        container.add(emailBtn.getButton());
        container.add(returnBtn.getButton());

/*
        container.add(payNameTextField.getTextField());
        container.add(payAddressTextField.getTextField());
        container.add(cardNumTextField.getTextField());
        container.add(expiryTextField.getTextField());
        container.add(cvvTextField.getTextField());
*/
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
