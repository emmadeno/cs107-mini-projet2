/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.Collections;
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
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor{
	
	private boolean travPorte;
	private Sprite personnage;
	private final static int ANIMATION_DURATION = 8;
	private Door lastPassedDoor;
	private final EnigmePlayerHandler handler;
	private List <DiscreteCoordinates> fieldView = new LinkedList<>();
	
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		
		@Override
		public void interactWith(Door door) {
			
				for(DiscreteCoordinates porte: door.getCurrentCells()) {  // on itère sur les coord de door
					
					if(EnigmePlayer.this.getCurrentMainCellCoordinates().equals(porte)){ // si la porte se trouve sur le fieldView de l'acteur 
					
						EnigmePlayer.this.setIsPassingDoor(door);
					}
				}
		}
		
		@Override
		public void interactWith(Apple apple) {
			
			System.out.println("entre dans interactwhith apple");
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(keyboard.get(Keyboard.L).isLastPressed()) { //si on presse la touche L
				
				System.out.println("Lpressed");
				for(DiscreteCoordinates cellView: fieldView) { 					//on itère sur les coordonées de fieldView
					for(DiscreteCoordinates pomme: apple.getCurrentCells()) {  // on itère sur les coord d'apple
						
						if(cellView.equals(pomme)){ // si la pomme se trouve sur le fieldView de l'acteur 
							apple.disappear();
							System.out.println("la pomme doit disparaitre");
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
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().left().left());
				System.out.print(fieldView.get(0));
			}
			else {
				setOrientation(Orientation.LEFT);
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().left());
				System.out.print(fieldView.get(0));
			}
			
		}
		if(keyboard.get(Keyboard.RIGHT).isDown()) {
			
			if(this.getOrientation()== Orientation.RIGHT) {
				
				move(ANIMATION_DURATION); 
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().right().right());
				
			}
			else {
				setOrientation(Orientation.RIGHT);
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().right());
			}
			
		}
		if(keyboard.get(Keyboard.UP).isDown()) {
			
			if(this.getOrientation()== Orientation.UP) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().up().up());
			}
			else {
				setOrientation(Orientation.UP);
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().up());
			}
			
		}
		if(keyboard.get(Keyboard.DOWN).isDown()) {
			
			if(this.getOrientation()== Orientation.DOWN) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().down().down());
			}
			else {
				setOrientation(Orientation.DOWN);
				fieldView.clear();
				fieldView.add(getCurrentMainCellCoordinates().down());
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
		return fieldView;
	}

	@Override
	public boolean wantsCellInteraction() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		
		Keyboard keyboard = this.getArea().getKeyboard();
		if(keyboard.get(Keyboard.L).wasDown()) {
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
