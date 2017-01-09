package simonGame;

import java.awt.Color;
import java.util.ArrayList;

import guiPractice.ClickableScreen;
import components.Action;
import components.TextLabel;
import components.Visible;

public class SimonScreen extends ClickableScreen implements Runnable {
	
	private TextLabel info;
	private ButtonInterfaceAhmed[] buttons = new ButtonInterfaceAhmed[6];
	private ProgressInterfaceAhmed progress;
	private ArrayList<MoveInterfaceAhmed> sequence;
	private int numOfRounds;
	private boolean acceptingInput;
	private int sequenceIndex;	
	private int lastSelectedButton = -1;
	
	private int numberOfButtons = 6;
	private Color[] colors = {Color.blue, Color.red, Color.magenta,Color.yellow,Color.green,Color.orange,};

	public SimonScreen(int width, int height) {
		super(width, height);
	}

	public void run() {
		info.setText("");
	    nextRound();
	}

	private void nextRound() {
		acceptingInput = false;
		numOfRounds++;
		sequence.add(randomMove());
		progress.setRound(numOfRounds);
		progress.setSequenceSize(sequence.size());
		changeText("Simon's turn!");
		info.setText("");
		playSequence();
	}

	private void playSequence() {
		ButtonInterfaceAhmed b = null;
		for(int i=0; i<sequence.size(); i++){
			if(b != null)
				b.dim();
			b = sequence.get(i).getButton();
			b.highlight();
			int sleepTime = (numOfRounds/(3/4))*100;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		info.setText(string);
	}

	@Override
	public void initAllObjects(ArrayList<Visible> visibles) {
		addButtons();
		progress = getProgress();
		info = new TextLabel(130,230,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceAhmed>();
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		numOfRounds = 0;
		addObject(progress);
		addObject(info);
	}

	private MoveInterfaceAhmed randomMove() {
		ButtonInterfaceAhmed b;
		while(true){
			int randomIndex = (int)(Math.random()*buttons.length);
			if(randomIndex != lastSelectedButton){
				b = buttons[randomIndex];
				lastSelectedButton = randomIndex;
				break;
			}
		}
		return getMove(b);
	}


	private void addButtons() {
		int[][] coords = {{100,150}, {130,150}, {160,150}, {190,150}, {220,150}, {250,150}, {280,150}};
		for(int i=0; i<numberOfButtons; i++){
			final ButtonInterfaceAhmed b = getAButton();
			b.setColor(colors[i]);
			b.setX(coords[i][0]);
			b.setY(coords[i][1]);
			b.setAction(new Action(){
					public void act(){
						if(acceptingInput){
							Thread blink = new Thread(new Runnable(){
								public void run(){
									b.highlight();
									try {
										Thread.sleep(800);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									b.dim();
								}
								});
							blink.start();
							if(b == sequence.get(sequenceIndex).getButton()){
								sequenceIndex++;
							}else{
								progress.gameOver();
							}
							
							if(sequenceIndex  == sequence.size()){
								Thread nextRound = new Thread(SimonScreen.this);
								nextRound.start(); 
							}
						}
					}
			});
			buttons[i] = b;
			addObject(b);
			System.out.println("added");
		}
	}
	
	//for partner
	private ButtonInterfaceAhmed getAButton() {
		return null;
	}
	
	//for partner
	private MoveInterfaceAhmed getMove(ButtonInterfaceAhmed b) {
		return null;
	}

	//for partner
	private ProgressInterfaceAhmed getProgress() {
		return null;
	}

}
