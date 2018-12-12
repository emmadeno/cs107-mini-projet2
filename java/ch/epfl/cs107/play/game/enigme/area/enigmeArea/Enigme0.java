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
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea{
	
	List<Door> portesL1 = new LinkedList<>();
	
	
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		//creation portes
		
		DiscreteCoordinates door1 = new DiscreteCoordinates(0,11);
		DiscreteCoordinates door2 = new DiscreteCoordinates(0,12);
		DiscreteCoordinates position1 = new DiscreteCoordinates(8,6);
		
		
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door1,position1, new Circle(0.5f,door1.toVector())));
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door2,position1, new Circle(0.5f,door1.toVector())));
		super.registerActor(portesL1.get(0));
		super.registerActor(portesL1.get(1));
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}

}
