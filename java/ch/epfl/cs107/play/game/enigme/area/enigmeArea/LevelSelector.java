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
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
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
		portesLS.add(new SignalDoor(this, Orientation.DOWN, "Level2",door2,position1, new Circle(0.5f,door2.toVector()), Logic.TRUE));
		
		
		Torch torch = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(1, 3), true);
		SignalRock rock = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(3, 3), Logic.TRUE);
		
		for(int i = 3; i <= 8; ++i ) {
			
			portesLS.add(new SignalDoor(this, Orientation.DOWN, "",new DiscreteCoordinates(i,7),new DiscreteCoordinates(i,7), new Circle(0.5f,new DiscreteCoordinates(i,7).toVector()), Logic.FALSE));
		}
		
		for(int i = 0; i < portesLS.size(); ++i) {
		 super.registerActor(portesLS.get(i));
		}
		super.registerActor(torch);
		super.registerActor(rock);
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "LevelSelector";
	}
	

}
