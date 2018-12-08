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

public class Torch extends AreaEntity implements Logic{
	
	private Sprite torchOff = new Sprite("torch.ground.off", 1, 1.f, this);
	private Sprite torchOn = new Sprite("torch.ground.on.1", 1, 1.f, this);
	private boolean allumee;

	public Torch(Area area, Orientation orientation, DiscreteCoordinates position, boolean allumee) {
		super(area, orientation, position);
		this.setOrientation(Orientation.DOWN);
		this.allumee = allumee;
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
		
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		if(allumee) {
			torchOn.draw(canvas);
		}
		else {
			torchOff.draw(canvas);
		}
		
	}

	@Override
	public boolean isOn() {
		
		return allumee;
	}

}
