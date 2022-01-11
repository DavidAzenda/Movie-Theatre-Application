package project;

import businessLogic.MovieTheatreController;
import clients.clientModel.UserController;
import frontend.GUIController;

public class MasterController {
	private MovieTheatreController movieTheater;
	private DBController database;
	private UserController user;
	private GUIController gui;
	
	//MasterController - Creates DBController, GUIController, MovieTheatreController
	public MasterController() {
		database = new DBController("jdbc:mysql://localhost/ENSF614PROJECT", "user","12345");
		gui = new GUIController();
		movieTheater = new MovieTheatreController(database,gui);
		user = new UserController(database,gui);

	}
}
