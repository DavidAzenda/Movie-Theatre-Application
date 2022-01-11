package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;
import businessLogic.*;

public class MovieView extends JFrame implements ActionListener, GUIFactory {

    Container container=getContentPane();
    

    Label selectMoviesLbl=createLabel("Select a Movie");
    JComboBox selectMovies = new JComboBox<String>();

    Label selectTheaterLbl=createLabel("Select a Theater");
    JComboBox selectTheater = new JComboBox<String>();

    Label selectShowTimeLbl=createLabel("Select a Showtime");
    JComboBox selectShowTime = new JComboBox<String>();

    Button seatButton= createButton("Select Seats");

    Button cancelTicketButton= createButton("Cancel a Ticket");
    Button accountInfoButton= createButton("Account Info");

    Button showNewsButton= createButton("Show News");

    public Movie selectedMovie;
    public Theatre selectedTheater;
	public Showtime selectedShowtime;

    public MovieView()
    {
        this.setup();
    }

    private void setup() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();

        //addMovies();
       selectTheater.addItem("Calgary Theater");
        //addShowtimes();

        


    }
    public void addMovieMenuListener(ActionListener l) {
    	selectMovies.addActionListener(l);
    }
    public void addTheaterMenuListener(ActionListener l) {
    	selectTheater.addActionListener(l);
    }

    public void addShowtimeMenuListener(ActionListener l) {
    	selectShowTime.addActionListener(l);
    }

    public void sSeatBtnListener(ActionListener l) {
        seatButton.getButton().addActionListener(l);
    }
    public void cTicketBtnListener(ActionListener l) {
        cancelTicketButton.getButton().addActionListener(l);
    }
    public void accountInfoBtnListener(ActionListener l) {
        accountInfoButton.getButton().addActionListener(l);
    }
    public void addShowNewsBtnListener(ActionListener l) {
        showNewsButton.getButton().addActionListener(l);
    }



    public void showNews(String news) {
        showMessageDialog(null, news, "Movie News", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setMovies(ArrayList<Movie> movies) {
    	DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>();

    	for(Movie m: movies) {
    		if(m.isEarlyRelease())
    			temp.addElement(m.getTitle() + " (Early Release)");
    		else
    			temp.addElement(m.getTitle());

         }
    	selectMovies.setModel(temp);
    }
    public void setTheatre(ArrayList<Theatre> theatres) {
    	JComboBox temp = new JComboBox();

        for(Theatre t: theatres) {
        	temp.addItem(t.getTheatreId());
        }
    	selectTheater = temp;

     }
    public void setShowtimes(ArrayList<Showtime> times) {
    	DefaultComboBoxModel<String> temp = new DefaultComboBoxModel<String>();
    	for(Showtime t: times) {
     	   temp.addElement(t.getDate()+ "  "+t.getTime().toString());
        }
        selectShowTime.setModel(temp);
     }
    public void showErrorMessage(String error) {
        showMessageDialog(null, error);
        //CALL SOMETHING HERE
    }

    public int [] getSelectionIndex() {
    	int [] s =  new int[3];
    	//System.out.println(selectTheater.getSelectedItem().toString()[0]);
    	s[0] = selectMovies.getSelectedIndex();
    	s[1] = selectTheater.getSelectedIndex();
    	s[2] = selectShowTime.getSelectedIndex();
    	return s;
    	
    }
    public void setLayoutManager()
    {
        container.setLayout(null);
    }
    public void setLocationAndSize()
    {

        selectMoviesLbl.getLabel().setBounds(50,100,300,30);
        selectTheaterLbl.getLabel().setBounds(50,150,300,30);
        selectShowTimeLbl.getLabel().setBounds(50,200,300,30);

        selectMovies.setBounds(200,100,300,30);
        selectTheater.setBounds(200,150,300,30);
        selectShowTime.setBounds(200,200,300,30);

        seatButton.getButton().setBounds(50,250,450,30);
        cancelTicketButton.getButton().setBounds(50,300,450,30);
        accountInfoButton.getButton().setBounds(50,350,450,30);
        showNewsButton.getButton().setBounds(50,400,450,30);

    }
    public void addComponentsToContainer()
    {

        container.add(selectMoviesLbl.getLabel());
        container.add(selectTheaterLbl.getLabel());
        container.add(selectShowTimeLbl.getLabel());
        container.add(selectMovies);
        container.add(selectTheater);
        container.add(selectShowTime);
        container.add(seatButton.getButton());
        container.add(cancelTicketButton.getButton());
        container.add(accountInfoButton.getButton());
        container.add(showNewsButton.getButton());
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
