/*
 *	Author:      Emmanuelle Denove
 *	Date:        9 Dec 2018
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

public abstract class Switchable extends AreaEntity implements Logic{
	
	protected boolean isOn;
	protected Sprite onPicture;
	protected Sprite offPicture;

	public Switchable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
	 return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		if(isOn) {
			onPicture.draw(canvas);
		}
		else {
			offPicture.draw(canvas);
		}
		
	}

	@Override
	public boolean isOn() {

		return isOn;
	}
	
	public void turnOnOff() {
		isOn = !isOn;
	}
	

}
