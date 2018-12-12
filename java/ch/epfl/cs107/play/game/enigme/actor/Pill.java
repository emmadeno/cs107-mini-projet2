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

public class Pill extends Pickup implements Logic, Ressources {
	
	private Sprite cup;
	private int pillNum;
	private boolean isCollected;
	private Logic logic;

	public Pill(Area area, Orientation orientation, DiscreteCoordinates position, int pillNum) {
		super(area, orientation, position);
		this.pillNum = pillNum;
		if(pillNum == 1) {
			cup = new Sprite("redpill", 1, 1.f, this);   //associe le sprite approprié à la pillule
		}
		else {
			cup = new Sprite("bluepill", 1, 1.f, this);
		}
		
		isCollected = false;
	}
	
	@Override
	public void actOnLives(PlayerLives i) {
		if(pillNum == 2) {   //seulement la pillule bleue agit sur la vie
			i.kill();
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		cup.draw(canvas);
	}
	
	@Override
	public String getText() {
		if(pillNum == 1) {  //associe le texte approprié au cup
			return "Tu restes au Pays des Merveilles et on descend avec le lapin blanc au fond du gouffre.";
		}
		else {
			return "";
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
		if(isCollected && pillNum == 1) {
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
