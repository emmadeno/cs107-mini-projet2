/*
 *	Author:      Emmanuelle Denove
 *	Date:        11 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Potion extends Pickup {
	
	private Sprite potion;
	
	/**
	 * 
	 * @param area(Area): aire associée à la potion
	 * @param orientation(Orientation): orientation de la potion
	 * @param position(DiscreteCoordinates): position de la potion sur l'aire
	 */
	public Potion(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		potion = new Sprite("potion.5", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		potion.draw(canvas);
		
	}

	@Override
	public void disappear() {
		this.getArea().unregisterActor(this);	
	}

}
