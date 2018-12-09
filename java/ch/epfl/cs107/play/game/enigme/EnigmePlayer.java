/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Pickup;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor{
	
	private boolean travPorte;
	private Sprite personnage;
	private final static int ANIMATION_DURATION = 8;
	private Door lastPassedDoor;
	private final EnigmePlayerHandler handler;
	
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		
		@Override
		public void interactWith(Door door) {
			
				for(DiscreteCoordinates porte: door.getCurrentCells()) {  // on itère sur les coord de door
					
					if(EnigmePlayer.this.getCurrentMainCellCoordinates().equals(porte)){ // si la porte se trouve sur le fieldView de l'acteur 
					
						EnigmePlayer.this.setIsPassingDoor(door);
					}
				}
		}
		
		public void interactWith(PressurePlate pressurePlate) {
			
			for(DiscreteCoordinates bouton: pressurePlate.getCurrentCells()) {  // on itère sur les coord de pressureSwitch
				
				if(EnigmePlayer.this.getCurrentMainCellCoordinates().equals(bouton)){ // si le bouton se trouve en dessous de l'acteur 
					//long currentTime = System.currentTimeMillis();
					/*
					int date = new Date().getSeconds();
					while((new Date().getSeconds() - date) < 3) {
						pressurePlate.switchOnOff(true);
						EnigmePlayer.this.update(0);
					}
					pressurePlate.switchOnOff(false);
					*/
				}
			}
	}
		
		@Override
		public void interactWith(Switchable switchable) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(switchable.isViewInteractable()) {
			
			if(keyboard.get(Keyboard.L).isLastPressed()) { //si on presse la touche L
				
				List <DiscreteCoordinates> fieldView = EnigmePlayer.this.getFieldOfViewCells();
				for(DiscreteCoordinates cellView: fieldView) { 					//on itère sur les coordonées de fieldView
					for(DiscreteCoordinates coord: switchable.getCurrentCells()) {  // on itère sur les coord du pickup
						
						if(cellView.equals(coord)){ // si la pomme se trouve sur le fieldView de l'acteur 
							switchable.turnOnOff();
						}
					}
				}
			}
			}
			
			if(switchable.isCellInteractable()) {
				for(DiscreteCoordinates bouton: switchable.getCurrentCells()) {  // on itère sur les coord de pressureSwitch
					
					if(EnigmePlayer.this.getCurrentMainCellCoordinates().equals(bouton)){ // si le bouton se trouve sur le fieldView de l'acteur 
					
						switchable.turnOnOff();
					}
				}
			}
		}
		
	
		
		@Override
		public void interactWith(Pickup pickup) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(keyboard.get(Keyboard.L).isLastPressed()) { //si on presse la touche L
				
				List <DiscreteCoordinates> fieldView = EnigmePlayer.this.getFieldOfViewCells();
				for(DiscreteCoordinates cellView: fieldView) { 					//on itère sur les coordonées de fieldView
					for(DiscreteCoordinates coord: pickup.getCurrentCells()) {  // on itère sur les coord du pickup
						
						if(cellView.equals(coord)){ // si la pomme se trouve sur le fieldView de l'acteur 
							pickup.disappear();
						}
					}
				}
			}
		}
		
		
	}
	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		personnage = new Sprite("ghost.1", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
		handler = new EnigmePlayerHandler();
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
		
		
		// update du champ de vision en même temps que l'orientation.
		
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
	
	public void resetTravPorte() {
		travPorte = false;
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		List<DiscreteCoordinates> fieldOfView = new LinkedList<DiscreteCoordinates>();
		fieldOfView.add(this.getCurrentMainCellCoordinates().jump(this.getOrientation().toVector()));
		return fieldOfView;
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		
		Keyboard keyboard = this.getArea().getKeyboard();
		if(keyboard.get(Keyboard.L).isLastPressed()) {
			return true;
		}

		return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
