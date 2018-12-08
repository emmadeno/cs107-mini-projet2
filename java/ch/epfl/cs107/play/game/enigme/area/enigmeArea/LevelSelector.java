/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea{
	
	List<Door> portesLS = new LinkedList<>();
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
	
		DiscreteCoordinates door1 = new DiscreteCoordinates(1,7);
		DiscreteCoordinates position1 = new DiscreteCoordinates(5,1);
		DiscreteCoordinates door2 = new DiscreteCoordinates(2,7);
		
		// création des portes
		portesLS.add(new Door(this, Orientation.DOWN, "Level1",door1,position1, new Circle(0.5f,door1.toVector())));
		portesLS.add(new Door(this, Orientation.DOWN, "Level2",door2,position1, new Circle(0.5f,door2.toVector())));
		
		for(int i = 4; i<= 8; ++i ) {
			
			portesLS.add(new Door(this, Orientation.DOWN, "",new DiscreteCoordinates(i,7),new DiscreteCoordinates(i,7), new Circle(0.5f,new DiscreteCoordinates(i,7).toVector())));
		}
		
		super.registerActor(portesLS.get(0));
		super.registerActor(portesLS.get(1));
		
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "LevelSelector";
	}
	

}
