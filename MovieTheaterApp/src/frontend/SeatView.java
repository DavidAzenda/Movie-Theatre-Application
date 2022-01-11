package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;

public class SeatView extends JFrame implements ActionListener, GUIFactory {

    private Container container=getContentPane();
    private int rows = 2;
    private int columns = 5;
    private Icon icon = (UIManager.getIcon("OptionPane.errorIcon"));
    private List<String> selectedSeats = new ArrayList<String>();
    private int numofSelectedSeats;
    private Button purchaseTicketButton= createButton("Purchase Ticket");
    private Button returnButton= createButton("Return Movie Selection");
    private List<Integer> availableSeats = new ArrayList<Integer>();
    
    public SeatView()
    {
        this.setup();
    }
	public void reset() {
		numofSelectedSeats = 0;
		selectedSeats = new ArrayList<String>();
	}
    private void setup() {
        setLayoutManager();
        //setLocationAndSize();
        addComponentsToContainer();
    }
    public void addPurchaseButtonListener(ActionListener l) {
        purchaseTicketButton.getButton().addActionListener(l);
    }
    public void addReturnBtnListener(ActionListener l) {
        returnButton.getButton().addActionListener(l);
    }
    public List<String> getSelectedSeats(){
        return selectedSeats;
    }
    public int getNumofSelectedSeats(){
        return numofSelectedSeats;
    }
    public int getNumofAvailableSeats(){
        return availableSeats.size();
    }
    public void setAvailableSeats(ArrayList<Integer> s) {
    	availableSeats = s;
    
    	container.removeAll();
    	setLocationAndSize();
    	addComponentsToContainer();
    	container.revalidate();
    	container.repaint();
    }
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void showError(String s) {
        showMessageDialog(null, s);
        //CALL SOMETHING HERE
    }
    public void setLocationAndSize()
    {	
    	//System.out.println(availableSeats);
        int x = 50;
        int y = 100;
        int counter = 1;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                final JToggleButton button = new JToggleButton("Row " + row + " seat " + column +" (#"+counter+")");
                button.setName(String.valueOf(counter));
                if( availableSeats.contains(counter)){
                	button.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent actionEvent) {
	                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	                        boolean selected = abstractButton.getModel().isSelected();
	                        if (selected) {
	                            button.setIcon(icon);
	                            selectedSeats.add(button.getName());
	                            //System.out.println(selectedSeats);
	                            numofSelectedSeats++;
	                        } else {
	                            selectedSeats.remove(button.getName().toString());
	                           // System.out.println(selectedSeats);
	                            numofSelectedSeats--;
	                            button.setIcon(null);
	                        }
	                    }
	                });
	            }else {
	            	button.setEnabled(false);
	            }
                counter++;
                button.setBounds(x,y,150,50);
                container.add(button);
                x = x + 150;
            }
            x= 50;
            y= y+150;
        }

        purchaseTicketButton.getButton().setBounds(50,350,380,30);
        returnButton.getButton().setBounds(440,350,380,30);

    }
    public void addComponentsToContainer()
    {
        container.add(purchaseTicketButton.getButton());
        container.add(returnButton.getButton());

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
