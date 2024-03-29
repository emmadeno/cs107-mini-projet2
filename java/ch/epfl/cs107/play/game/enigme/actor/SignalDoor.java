/*
 *	Author:      Emmanuelle Denove
 *	Date:        9 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

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

	/**
	 * 
	 * @param area(Area): aire associée à la porte
	 * @param orientation(Orientation): orientation de la porte
	 * @param destinationArea(Area): aire vers laquelle dirige la porte
	 * @param position(DiscreteCoordinates) : position de la porte dans l'aire de départ
	 * @param initialCoord(DiscreteCoordinates) position de l'acteur sur l'aire d'arrivée
	 * @param otherCoordinates(Circle)
	 * @param signal(Logic): signal associé à la porte pour savoir si elle est ouverte ou fermée
	 */
	public SignalDoor(Area area, Orientation orientation, String destinationArea, DiscreteCoordinates position,
			DiscreteCoordinates initialCoord, List<DiscreteCoordinates> otherCoordinates, Logic signal) {
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
	

}
