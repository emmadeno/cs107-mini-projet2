/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GreenApple;
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
		
		DiscreteCoordinates door1MainCell = new DiscreteCoordinates(0,11);
		List<DiscreteCoordinates> door1 = new ArrayList<DiscreteCoordinates>();
		door1.add(new DiscreteCoordinates(0,12));
		
		
		DiscreteCoordinates door2MainCell = new DiscreteCoordinates(9,29);
		List<DiscreteCoordinates> door2 = new ArrayList<DiscreteCoordinates>();
		door2.add(new DiscreteCoordinates(10,29));
		door2.add(new DiscreteCoordinates(11,29));
		
		DiscreteCoordinates position1 = new DiscreteCoordinates(8,6);
		DiscreteCoordinates position2 = new DiscreteCoordinates(16,1);
		
		portesL1.add(new Door(this, Orientation.UP, "LevelSelector",door1MainCell,position1, door1));	
		portesL1.add(new Door(this, Orientation.DOWN, "Enigme1",door2MainCell,position2, door2));
		
		for(int i = 0; i < portesL1.size(); ++i) {
			super.registerActor(portesL1.get(i));
		}
		
		//creation pomme
		
		GreenApple apple = new GreenApple(this, Orientation.DOWN, new DiscreteCoordinates(20, 15));
		super.registerActor(apple);
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}

}
