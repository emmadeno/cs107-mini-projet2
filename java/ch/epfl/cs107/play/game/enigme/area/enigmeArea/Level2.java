/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.RedApple;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea{
	
	List<Door> portesL2 = new LinkedList<>();
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		// creation portes
		DiscreteCoordinates door1 = new DiscreteCoordinates(5,0);
		DiscreteCoordinates position1 = new DiscreteCoordinates(2,6);
				
		portesL2.add(new Door(this, Orientation.DOWN, "LevelSelector",door1,position1, new ArrayList<DiscreteCoordinates>()));
		
		// creation pomme
		
		Apple pomme = new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(5,6));
		
		// register les acteurs
		super.registerActor(pomme);	
		super.registerActor(portesL2.get(0));
		
		
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Level2";
	}
	
	
}
