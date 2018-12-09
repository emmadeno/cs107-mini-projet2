/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea{
	
	private List<Actor> actors = new LinkedList<Actor>();
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		createActors();
		for(int i = 0; i < actors.size(); ++i) {
			 super.registerActor(actors.get(i));
			}
		
		
		return true;
		
		
	}
	
	// creation des nouveaux acteurs du level
	private void createActors(){
		
		DiscreteCoordinates signalDoor = new DiscreteCoordinates(5,9);
		DiscreteCoordinates position = new DiscreteCoordinates(3,6);
		
		actors.add(new Key(this, Orientation.DOWN, new DiscreteCoordinates(1,3)));
		
		actors.add(new Torch(this, Orientation.DOWN, new DiscreteCoordinates(7, 5), true));
		
		actors.add(new PressurePlate(this, Orientation.DOWN, new DiscreteCoordinates(9,8)));
		
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,5)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,6)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,6)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,6)));
		
		actors.add(new Lever(this, Orientation.DOWN, new DiscreteCoordinates(10,5)));
		actors.add(new Lever(this, Orientation.DOWN, new DiscreteCoordinates(9,5)));
		actors.add(new Lever(this, Orientation.DOWN, new DiscreteCoordinates(8,5)));
		
		actors.add(new SignalDoor(this, Orientation.DOWN, "LevelSelector",signalDoor,position, new Circle(0.5f,signalDoor.toVector()), Logic.TRUE));
		
		actors.add(new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6,8), Logic.FALSE));
		actors.add(new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(5,8), Logic.FALSE));
		actors.add(new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(4,8), Logic.FALSE));
	}
	
	@Override
	public String getTitle() {
		return "Level3";
	}

}
