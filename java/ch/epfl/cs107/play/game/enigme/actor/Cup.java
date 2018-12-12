/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.PlayerLives;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Cup extends Pickup implements Logic, Ressources{
	
	private Sprite cup;
	private int cupNum;
	private boolean isCollected;
	private Logic logic;

	public Cup(Area area, Orientation orientation, DiscreteCoordinates position, int cupNum) {
		super(area, orientation, position);
		this.cupNum = cupNum;
		if(cupNum == 1) {
			cup = new Sprite("cup.1", 1, 1.f, this);   //associe le sprite approprié au cup
		}
		else if(cupNum == 2) {
			cup = new Sprite("cup2", 1, 1.f, this);
		}
		else {
			cup = new Sprite("cup3", 1, 1.f, this);
		}
		isCollected = false;
	}
	
	@Override
	public void actOnLives(PlayerLives i) {
		if(cupNum == 1 || cupNum == 3) {   //seulement les cups 1 et 3 agissent sur la vie
			i.removeLife();
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		cup.draw(canvas);
	}
	
	@Override
	public String getText() {
		if(cupNum == 1 || cupNum == 3) {  //associe le texte approprié au cup
			return "You chose poorly";
		}
		else {
			return "You chose wisely";
		}
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith((Pickup)this);
		((EnigmeInteractionVisitor)v).interactWith((Ressources)this);
		
	}

	@Override
	public void disappear() {
		super.disappear();
		isCollected = true;
	}


	@Override
	public boolean isOn() {
		if(isCollected && cupNum == 2) {
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
