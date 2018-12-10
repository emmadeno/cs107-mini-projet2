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

public class Lever extends Switchable implements Logic{
	

	public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isOn = false;
		onPicture = new Sprite("lever.big.left", 1, 1.f, this);
		offPicture = new Sprite("lever.big.right", 1, 1.f, this);
				
	}

	@Override
	public boolean takeCellSpace() {

		return true;
	}

	@Override
	public boolean isViewInteractable() {
		
		return true;
	}

	@Override
	public boolean isCellInteractable() {

		return false;
	}

}
