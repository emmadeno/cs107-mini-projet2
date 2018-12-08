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

public class Lever extends AreaEntity implements Logic{
	
	private boolean isLeft;
	private Sprite left = new Sprite("lever.big.left", 1, 1.f, this);
	private Sprite right = new Sprite("lever.big.right", 1, 1.f, this);

	public Lever(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		isLeft = false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		return Collections.singletonList(getCurrentMainCellCoordinates());
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

		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		if(isLeft) {
			left.draw(canvas);
		}
		else {
			right.draw(canvas);
		}
		
	}

	@Override
	public boolean isOn() {
	
		return isLeft;
	}
	
	public void switchLever() {
		isLeft = !isLeft;
	}

}
