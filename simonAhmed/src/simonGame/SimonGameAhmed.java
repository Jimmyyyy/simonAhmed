package simonGame;

import simonGame.SimonScreen;
import guiPractice.GUIApplication;

public class SimonGameAhmed extends GUIApplication{

	public SimonGameAhmed(){
		// TODO Auto-generated constructor stub
	}
	
	protected void initScreen() {
		SimonScreen screen = new SimonScreen(getWidth(), getHeight());
		setScreen(screen);
	}
	
	public static void main(String[] args) {
		SimonGameAhmed game = new SimonGameAhmed();
		Thread app = new Thread(game);
		app.start();
	}


}
