/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity{
	
	private boolean travPorte;
	private Sprite personnage;
	private final static int ANIMATION_DURATION = 8;
	private Door lastPassedDoor;
	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		personnage = new Sprite("ghost.1", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
	}
	
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		super.setCurrentPosition(position.toVector());
		this.update(0.01f);
		resetMotion();
	}
	
	public void leaveArea(Area area) {
		area.unregisterActor(this);
	}
	
	public void travPorte(boolean isTravPorte) {
		travPorte = isTravPorte;
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
		
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		personnage.draw(canvas);
	}
	
	@Override
	public void update(float deltatime) {
		Keyboard keyboard = this.getArea().getKeyboard();
		
		if(keyboard.get(Keyboard.LEFT).isDown()) {
			
			if(this.getOrientation()== Orientation.LEFT) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
			}
			else {
				setOrientation(Orientation.LEFT);
			}
			
		}
		if(keyboard.get(Keyboard.RIGHT).isDown()) {
			
			if(this.getOrientation()== Orientation.RIGHT) {
				
				move(ANIMATION_DURATION); 
			}
			else {
				setOrientation(Orientation.RIGHT);
			}
			
		}
		if(keyboard.get(Keyboard.UP).isDown()) {
			
			if(this.getOrientation()== Orientation.UP) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
			}
			else {
				setOrientation(Orientation.UP);
			}
			
		}
		if(keyboard.get(Keyboard.DOWN).isDown()) {
			
			if(this.getOrientation()== Orientation.DOWN) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
			}
			else {
				setOrientation(Orientation.DOWN);
			}
			
		}
		
		super.update(deltatime);
		
		
		
	}
	
	public void setIsPassingDoor(Door door) {
		travPorte(true);
		lastPassedDoor = door;
	}
	
	public Door passedDoor() {
		return lastPassedDoor;
	}
	
	public boolean getTravPorte() {
		
		return travPorte;
	}

}
