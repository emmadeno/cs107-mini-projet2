/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.PlayerLives;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Apple extends Pickup implements Ressources {
	

	/**
	 * 
	 * @param area(Area): aire associée à la pomme
	 * @param orientation(Orientation): orientation de la pomme
	 * @param position(DiscreteCoordinates): position de la pomme sur l'aire
	 */
	public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.setOrientation(Orientation.DOWN);
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith((Pickup)this);
		((EnigmeInteractionVisitor)v).interactWith((Ressources)this);	
	}
	

}
