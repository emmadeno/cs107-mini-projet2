/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.RedApple;
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
		
		
		portesL1.add(new Door(this, Orientation.DOWN, "LevelSelector",door1,position1, new ArrayList<DiscreteCoordinates>()));
		super.registerActor(portesL1.get(0));
		
		//creation pomme
		
		Apple apple = new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(6,4));
		super.registerActor(apple);
		
		Apple apple2 = new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(7,7));
		super.registerActor(apple2);
		
		//creation potion
		
		Potion potion = new Potion(this, Orientation.DOWN, new DiscreteCoordinates(4,6));
		super.registerActor(potion);
		
		
		return true;
		
	}
	
	@Override
	public String getTitle() {
		return "Level1";
	}

}
