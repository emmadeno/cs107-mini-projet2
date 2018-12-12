/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.area.enigmeArea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Cup;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.GreenApple;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Pickup;
import ch.epfl.cs107.play.game.enigme.actor.Pill;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.RedApple;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.SpeakingPerson;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.MultipleOr;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea{
	
	List<Actor> actors = new LinkedList<>();
	private Key key;
	
	private Lever lever1;
	private Lever lever2;
	private Lever lever3;
	
	private PressurePlate pressurePlate;
	
	private Torch torch1;
	private Torch torch2;
	private Torch torch3;
	private Torch torch4;
	private Torch torch5;
	private Torch torch6;
	
	private PressureSwitch switch1;
	private PressureSwitch switch2;
	private PressureSwitch switch3;
	private PressureSwitch switch4;
	private PressureSwitch switch5;
	private PressureSwitch switch6;
	private PressureSwitch switch7;
	private PressureSwitch switch8;
	private PressureSwitch switch9;
	
	private SignalRock rock1;
	private SignalRock rock2;
	private SignalRock rock3;
	private SignalRock rock4;
	private SignalRock rock5;
	private SignalRock rock6;
	private SignalRock rock7;
	private SignalRock rock8;
	
	private Cup cup1;
	private Cup cup2;
	private Cup cup3;
	
	private Pill pilluleBleue;
	private Pill pilluleRouge;
	
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		initiateActors();
		
		//register tous les acteurs
		for(int i = 0; i < actors.size(); ++i) {
			super.registerActor(actors.get(i));
		}
				
		return true;
		
		
	}
	
	private void initiateActors() {
		
		//creation pommes

		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(14, 14)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(17, 18)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(23, 22)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(10, 25)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(16, 31)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(12, 35)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(22, 33)));
		actors.add(new RedApple(this, Orientation.DOWN, new DiscreteCoordinates(17, 34)));
		
		actors.add(new GreenApple(this, Orientation.DOWN, new DiscreteCoordinates(19, 22)));
		actors.add(new GreenApple(this, Orientation.DOWN, new DiscreteCoordinates(10, 27)));
		
		//creation clés
		
		actors.add(new Key(this, Orientation.DOWN, new DiscreteCoordinates(23, 30)));
		key = new Key(this, Orientation.DOWN, new DiscreteCoordinates(21, 35));
		actors.add(key);
		
		//creation potion
		
		actors.add(new Potion(this, Orientation.DOWN, new DiscreteCoordinates(23, 35)));
		
		//creation leviers
		
		actors.add(lever1 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(16, 11)));
		actors.add(lever2 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(17, 11)));
		actors.add(lever3 = new Lever(this, Orientation.DOWN, new DiscreteCoordinates(18, 11)));
		List<Logic> levers = Arrays.asList(lever3, lever2, lever1);
		
		//creation pressurePlate
		
		actors.add(pressurePlate = new PressurePlate(this, Orientation.DOWN, new DiscreteCoordinates(22,2)));
		
		//creation torches
		
		actors.add(torch1 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(15, 4), false));
		actors.add(torch2 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(17, 4), false));
		actors.add(torch3 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(5, 8), false));
		actors.add(torch4 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(8, 8), false));
		actors.add(torch5 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(5, 16), false));
		actors.add(torch6 = new Torch(this, Orientation.DOWN, new DiscreteCoordinates(8, 16), false));
		List<Logic> torches = Arrays.asList(torch1, torch2, torch3, torch4, torch5, torch6);
		
		//creation boutons de pression
		
		actors.add(switch1 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6, 6)));
		actors.add(switch2 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6, 5)));
		actors.add(switch3 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(6, 4)));
		actors.add(switch4 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 5)));
		actors.add(switch5 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(7, 5)));
		actors.add(switch6 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 4)));
		actors.add(switch7 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(7, 4)));
		actors.add(switch8 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(5, 6)));
		actors.add(switch9 = new PressureSwitch(this, Orientation.DOWN, new DiscreteCoordinates(7, 6)));
		List<Logic> switches = Arrays.asList(switch1, switch2, switch3, switch4, switch5);
		List<Logic> notSwitches = Arrays.asList(switch6, switch7, switch8, switch9);
		
		//creation cups
		
		actors.add(cup1 = new Cup(this, Orientation.DOWN, new DiscreteCoordinates(4, 13), 1));
		actors.add(cup2 = new Cup(this, Orientation.DOWN, new DiscreteCoordinates(6, 13), 2));
		actors.add(cup3 = new Cup(this, Orientation.DOWN, new DiscreteCoordinates(8, 13), 3));
		
		//creation pillules
		actors.add(pilluleRouge = new Pill(this, Orientation.DOWN, new DiscreteCoordinates(7,30), 1));
		actors.add(pilluleBleue = new Pill(this, Orientation.DOWN, new DiscreteCoordinates(5,30), 2));
		
		//creation rochers
		
		actors.add(rock1 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(21, 6), new Or(pressurePlate, new LogicNumber(6, levers))));
		actors.add(rock2 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(13, 5), key));
		actors.add(rock3 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6, 9), new And(new MultipleAnd(switches), new Not(new MultipleOr(notSwitches)))));
		actors.add(rock4 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(7, 9), new And(new MultipleAnd(switches), new Not(new MultipleOr(notSwitches)))));
		actors.add(rock5 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6, 17), cup2)); 
		actors.add(rock6 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(7, 17), cup2));
		actors.add(rock7 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(6, 18), new MultipleAnd(torches)));
		actors.add(rock8 = new SignalRock(this, Orientation.DOWN, new DiscreteCoordinates(7, 18), new MultipleAnd(torches)));
		
		//creation personnes
		
		actors.add(new SpeakingPerson(this, Orientation.DOWN, new DiscreteCoordinates(12,8), "girl.1"));
		actors.add(new SpeakingPerson(this, Orientation.DOWN, new DiscreteCoordinates(8,31), "old.man.1"));
		
		//creation portes
		
		DiscreteCoordinates door1MainCell = new DiscreteCoordinates(16,0);
		List<DiscreteCoordinates> door1 = new ArrayList<DiscreteCoordinates>();
		door1.add(new DiscreteCoordinates(15,0));
		door1.add(new DiscreteCoordinates(17,0));
				
		DiscreteCoordinates door2MainCell = new DiscreteCoordinates(6,32);
		List<DiscreteCoordinates> door2 = new ArrayList<DiscreteCoordinates>();
				
		DiscreteCoordinates position1 = new DiscreteCoordinates(1,12);
		DiscreteCoordinates position2 = new DiscreteCoordinates(7,1);
				
		actors.add(new Door(this, Orientation.RIGHT, "Enigme0",door1MainCell,position1, door1));
		actors.add(new SignalDoor(this, Orientation.UP, "Enigme2",door2MainCell,position2, door2, pilluleRouge));
		
		
		
	}
	
	@Override
	public String getTitle() {
		return "Enigme1";
	}
	
	@Override
	public float getCameraScaleFactor() {
		return 15;
	}

}
