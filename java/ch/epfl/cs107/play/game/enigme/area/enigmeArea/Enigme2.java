/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SpeakingPerson;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.MultipleOr;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea{
	
	private List<Actor> actors = new LinkedList<Actor>();
	private List<Logic> switches;
	private List<Logic> notSwitches;
	private SignalDoor door1;
	private Torch torch;
	private Lever lever1;
	private Lever lever2;
	private Lever lever3;
	private PressureSwitch switch1;
	private PressureSwitch switch2;
	private PressureSwitch switch3;
	private PressureSwitch switch4;
	private PressureSwitch switch5;
	private PressureSwitch switch6;
	private PressureSwitch switch7;
	private PressureSwitch switch8;
	private PressureSwitch switch9;
	private PressureSwitch switch10;
	private PressureSwitch switch11;
	private PressureSwitch switch12;
	private PressureSwitch switch13;
	private PressureSwitch switch14;
	private PressureSwitch switch15;
	private PressureSwitch switch16;
	private PressureSwitch switch17;
	private PressureSwitch switch18;
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		
		initiateActors();
		
		for(int i = 0; i < actors.size(); ++i) {
			 super.registerActor(actors.get(i));
			}

				
		return true;
		
		
	}
	
	private void initiateActors() {
		
		DiscreteCoordinates signalDoor = new DiscreteCoordinates(7,0);
		DiscreteCoordinates position = new DiscreteCoordinates(8,6);
		
		// pressure switchs
		actors.add(switch1 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(11,6)));
		actors.add(switch2 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(11,7)));
		actors.add(switch3 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(11,8)));
		actors.add(switch4 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(12,6)));
		actors.add(switch5 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(12,7)));
		actors.add(switch6 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(12,8)));
		actors.add(switch7 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(13,6)));
		actors.add(switch8 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(13,7)));
		actors.add(switch9 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(1,8)));
		actors.add(switch10 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(2,8)));
		actors.add(switch11 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(3,8)));
		actors.add(switch12 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(13,8)));
		actors.add(switch13 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(1,6)));
		actors.add(switch14 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(1,7)));
		actors.add(switch15 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(2,6)));
		actors.add(switch16 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(2,7)));
		actors.add(switch17 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(3,6)));
		actors.add(switch18 =new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(3,7)));
		
		//personnage
		actors.add(new SpeakingPerson(this, Orientation.DOWN, new DiscreteCoordinates(13,3), "max.ghost"));
		
		
		//torche
		torch = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(7, 9), false);
		actors.add(torch);
		
		//leviers
		lever1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(6,5)); 
		actors.add(lever1);
		lever2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(7,5));
		actors.add(lever2);
		lever3 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(8,5));
		actors.add(lever3);
		
		switches = Arrays.asList(switch1,switch2,switch3,switch4,switch5,switch6,switch7,switch8,switch9,
				switch10,switch11,lever1,lever3,torch);
		notSwitches = Arrays.asList(switch12, switch13, switch14, switch15, switch16, switch17, switch18, lever2);
		
		//door finale
		
		door1 = new SignalDoor(this, Orientation.DOWN, "LevelSelector",signalDoor,position, new ArrayList<DiscreteCoordinates>(), new And(new MultipleAnd(switches), new Not(new MultipleOr(notSwitches))));
		actors.add(door1);
		
	}
	
	@Override
	public String getTitle() {
		return "Enigme2";
	}
}
