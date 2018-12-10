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

public class SignalRock extends AreaEntity implements Logic{
	
	private Logic signal;
	private Sprite rock = new Sprite("rock.3", 1, 1.f, this);

	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
		super(area, orientation, position);
		this.signal = signal;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {

		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
	if(signal.isOn()) {
		return false;
	}
	return true;
	}

	@Override
	public boolean isViewInteractable() {
		
		return false;
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
		if(!signal.isOn()) {
			rock.draw(canvas);
		}
		
	}

	@Override
	public boolean isOn() {
		if(signal == Logic.TRUE) {
			return true;
		}
		return false;
	}
	
	public void setSignal(Logic signal) {
		this.signal = signal;
	}

}
