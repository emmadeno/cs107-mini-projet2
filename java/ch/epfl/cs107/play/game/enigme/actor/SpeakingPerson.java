/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
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
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class SpeakingPerson extends AreaEntity{
	
	private Sprite person;
	private String personName;

	public SpeakingPerson(Area area, Orientation orientation, DiscreteCoordinates position, String person) {
		super(area, orientation, position);
		this.personName = person;
		this.person = new Sprite(person, 1f, 1.3125f,this,new RegionOfInterest(16,0,16,21), Vector.ZERO);
		
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
	
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		person.draw(canvas);
		
	}
	
	/**
	 * getText method : retourne le texte qui doit s'afficher quand le Pickup est collecté
	 * @return String : texte à afficher
	 */
	public String getText() {
		if(personName.equals("old.man.1")) {
			return "Je ne peux que te montrer la porte, c'est à toi qu'il appartient de la franchir.";
		}
		else {
			return "Les coins sont les faiblesses";
		}
	}

}
