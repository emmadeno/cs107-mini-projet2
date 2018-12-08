/*
 *	Author:      Emmanuelle Denove
 *	Date:        8 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends AreaEntity implements Logic{
	
	private boolean isOn;
	private Sprite plateOn = new Sprite("rock.1", 1, 1.f, this);
	private Sprite plateOff = new Sprite("GroundPlateOff", 1, 1.f, this);

	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isOn = false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
	
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		if(isOn) {
			plateOn.draw(canvas);
		}
		else {
			plateOff.draw(canvas);
		}
		
	}

	@Override
	public boolean isOn() {
		return isOn;
	}
	
	public void switchOnOff(float runTime) {
		long currentTime = System.nanoTime();
		if(System.nanoTime() - currentTime > runTime) {
			isOn = !isOn;
		}
	}

}
