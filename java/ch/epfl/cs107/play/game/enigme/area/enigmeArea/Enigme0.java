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
		DiscreteCoordinates door3 = new DiscreteCoordinates(9,29);
		DiscreteCoordinates door4 = new DiscreteCoordinates(10,29);
		DiscreteCoordinates door5 = new DiscreteCoordinates(11,29);
		
		DiscreteCoordinates position1 = new DiscreteCoordinates(8,6);
		DiscreteCoordinates position2 = new DiscreteCoordinates(16,1);
		
		portesL1.add(new Door(this, Orientation.UP, "Enigme1",door1,position2, new Circle(0.5f,door1.toVector())));
		portesL1.add(new Door(this, Orientation.UP, "Enigme1",door2,position2, new Circle(0.5f,door2.toVector())));
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door3,position1, new Circle(0.5f,door3.toVector())));
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door4,position1, new Circle(0.5f,door4.toVector())));
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door5,position1, new Circle(0.5f,door5.toVector())));
		
		for(int i = 0; i < portesL1.size(); ++i) {
			super.registerActor(portesL1.get(i));
		}
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}

}
