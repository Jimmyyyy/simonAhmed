package simonGame;

import java.awt.Color;

import components.Action;
import components.Clickable;

public interface ButtonInterfaceAhmed extends Clickable{
	public void setColor(Color color);

	public void setX(int x);

	public void setY(int y);
	
	public void setAction(Action a);

	public void highlight();

}
