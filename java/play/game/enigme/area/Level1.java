/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea{
	
	List<Door> portesL1 = new LinkedList<>();
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		//creation portes
		
		DiscreteCoordinates door1 = new DiscreteCoordinates(5,0);
		DiscreteCoordinates position1 = new DiscreteCoordinates(1,6);
		
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door1,position1, new Circle(0.5f,door1.toVector())));
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Level1";
	}

}
