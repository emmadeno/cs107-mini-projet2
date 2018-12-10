/*
 *	Author:      Emmanuelle Denove
 *	Date:        9 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door implements Logic{
	
	private Logic signal;
	private Sprite openDoor = new Sprite("door.open.1", 1, 1.f, this);
	private Sprite closedDoor = new Sprite("door.close.1", 1, 1.f, this);

	public SignalDoor(Area area, Orientation orientation, String destinationArea, DiscreteCoordinates position,
			DiscreteCoordinates initialCoord, Circle otherCoordinates, Logic signal) {
		super(area, orientation, destinationArea, position, initialCoord, otherCoordinates);
		this.signal = signal;
	}
	
	@Override
	public boolean takeCellSpace() {
		if(signal.isOn()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isOn() {

		return signal.isOn();
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (signal.isOn()) {
			openDoor.draw(canvas);
		}
		else {
			closedDoor.draw(canvas);
		}
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		if(signal.isOn()) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		}
	}
	
	public void setSignal(Logic logic) {
		signal = logic;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
	}

}
