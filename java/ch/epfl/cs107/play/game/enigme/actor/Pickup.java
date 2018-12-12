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
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Pickup extends AreaEntity{

	public Pickup(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	/**
	 * getText method : retourne le texte qui doit s'afficher quand le Pickup est collecté
	 * @return String : texte à afficher
	 */
	public abstract String getText();
	
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
		
		return false;
	}
	//fait en sorte que l'acteur disparaisse lorsqu'il est connecté
	public void disappear() {
		this.getArea().unregisterActor(this);
	}
	
}
