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
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea{
		
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		//creation portes
		
				DiscreteCoordinates door1MainCell = new DiscreteCoordinates(7,0);
				List<DiscreteCoordinates> door1 = new ArrayList<DiscreteCoordinates>();
				
				DiscreteCoordinates position1 = new DiscreteCoordinates(6,31);
				
				Door door = new Door(this, Orientation.DOWN, "Enigme1",door1MainCell,position1, door1);
				this.registerActor(door);

				
				return true;
		
		
	}
	
	@Override
	public String getTitle() {
		return "Enigme2";
	}
}
