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
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea{
	
	private List<Actor> actors = new LinkedList<Actor>();
	private Key key;
	private Torch torch;
	private SignalDoor door1;
	private SignalRock rock1;
	private SignalRock rock2;
	private SignalRock rock3;
	private Lever lever1;
	private Lever lever2;
	private Lever lever3;
	private PressurePlate pressurePlate;
	private List<Logic> switches  = new LinkedList<Logic>();
	private List<Logic> levers  = new LinkedList<Logic>();
	
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
		DiscreteCoordinates position = new DiscreteCoordinates(3,4);
		
		key = new Key(this, Orientation.DOWN, new DiscreteCoordinates(1,3));
		actors.add(key);
		
		torch = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(7, 5), true);
		actors.add(torch);
		
		pressurePlate = new PressurePlate(this, Orientation.DOWN, new DiscreteCoordinates(9,8));
		actors.add(pressurePlate);
		
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,4)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,5)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(4,6)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5,6)));
		actors.add(new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6,6)));
		
		
		for(Actor actor : actors) {
			if(actor instanceof PressureSwitch) {
				switches.add((PressureSwitch) actor);
			}
		}
		
		
		
		lever1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(10,5)); 
		actors.add(lever1);
		lever2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(9,5));
		actors.add(lever2);
		lever3 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(8,5));
		actors.add(lever3);
		
		for(Actor actor : actors) {
			if(actor instanceof Lever) {
				levers.add((Lever) actor);
			}
		}
		
		door1 = new SignalDoor(this, Orientation.DOWN, "LevelSelector",signalDoor,position, new Circle(0.5f,signalDoor.toVector()), key);
		actors.add(door1);
		
		rock1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6,8), pressurePlate);
		actors.add(rock1);
		rock2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(5,8), new MultipleAnd(switches));
		actors.add(rock2);
		rock3 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(4,8), new LogicNumber(5, levers));
		actors.add(rock3);
	}
	
	@Override
	public String getTitle() {
		return "Level3";
	}
	
	@Override
	public void update(float dT) {
		super.update(dT);
		
		/*
		//if(key.isOn()) {
		//	door1.setSignal(Logic.TRUE);
		//}
		if (pressurePlate.isOn()) {
			rock1.setSignal(Logic.TRUE);
		}
		if (!pressurePlate.isOn()) {
			rock1.setSignal(Logic.FALSE);
		}
		
		if (multipleAnd.isOn()) {
			rock2.setSignal(Logic.TRUE);
		}
		if (!multipleAnd.isOn()) {
			rock2.setSignal(Logic.FALSE);
		}
		
		
		if((lever1.isOn() && lever3.isOn() && !lever2.isOn()) || torch.isOn()) {
			rock3.setSignal(Logic.TRUE);
		}
		if(!((lever1.isOn() && lever3.isOn() && !lever2.isOn()) || torch.isOn())) {
			rock3.setSignal(Logic.FALSE);
		}
		*/
		
	}

}
