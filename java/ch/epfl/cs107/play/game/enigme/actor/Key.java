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
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Pickup implements Logic{
	
	private Sprite key = new Sprite("key.1", 1, 1.f, this);
	private boolean isCollected;
	private Logic logic;

	public Key(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.setOrientation(Orientation.DOWN);
		isCollected = false;
	}


	@Override
	public void draw(Canvas canvas) {
		key.draw(canvas);	
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}
	
	@Override
	public void disappear() {
		this.getArea().unregisterActor(this);
		isCollected = true;
	}


	@Override
	public boolean isOn() {
		if(isCollected) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public void update(float f) {
		super.update(f);
		if(isOn()) {
			logic = Logic.TRUE;  //met le signal logique à true si isOn()
		}
		else {
			logic = Logic.FALSE; // sinon met le signal logique à false
		}
	}


}
