/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea{
	
	List<Door> portesL1 = new LinkedList<>();
	
	
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		//creation portes
		
				DiscreteCoordinates door1 = new DiscreteCoordinates(15,0);
				DiscreteCoordinates door2 = new DiscreteCoordinates(16,0);
				DiscreteCoordinates door3 = new DiscreteCoordinates(17,0);
				DiscreteCoordinates door4 = new DiscreteCoordinates(6,32);
				
				DiscreteCoordinates position1 = new DiscreteCoordinates(1,12);
				DiscreteCoordinates position2 = new DiscreteCoordinates(7,1);
				
				portesL1.add(new Door(this, Orientation.RIGHT, "Enigme0",door1,position1, new Circle(0.5f,door1.toVector())));
				portesL1.add(new Door(this, Orientation.RIGHT, "Enigme0",door2,position1, new Circle(0.5f,door2.toVector())));
				portesL1.add(new Door(this, Orientation.RIGHT, "Enigme0",door3,position1, new Circle(0.5f,door3.toVector())));
				portesL1.add(new SignalDoor(this, Orientation.UP, "Enigme2",door4,position2, new Circle(0.5f,door4.toVector()), Logic.FALSE));
				
				for(int i = 0; i < portesL1.size(); ++i) {
					super.registerActor(portesL1.get(i));
				}
				
				return true;
		
		
	}
	
	@Override
	public String getTitle() {
		return "Enigme1";
	}

}
