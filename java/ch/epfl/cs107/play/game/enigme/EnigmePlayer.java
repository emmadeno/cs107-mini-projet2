/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AnimationSprite;
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
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Pickup;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Ressources;
import ch.epfl.cs107.play.game.enigme.actor.SpeakingPerson;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.EnigmeArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends PlayerLives implements Interactor, AnimationSprite{
	
	private boolean travPorte;
	//private Sprite personnage;
	private final static int ANIMATION_DURATION = 8;
	private Door lastPassedDoor;
	private final EnigmePlayerHandler handler;
	
	private Sprite[] spritesDOWN;
	private Sprite[] spritesUP;
	private Sprite[] spritesLEFT;
	private Sprite[] spritesRIGHT;
	
	private float gameTime;
	private List<Dialog> dialogs;
	private float time;
	
	private int moves = 0;
	
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		
		@Override
		public void interactWith(Door door) {
					
	     	EnigmePlayer.this.setIsPassingDoor(door); // le player passe une porte

		}
		@Override
		public void interactWith(PressurePlate pressurePlate) {

			if(!pressurePlate.isOn()) {  //si la pressurePlate n'est pas déjà allumée
				pressurePlate.setCurrentTime();  // enregistre le temps auquel l'acteur interagit
			}
			pressurePlate.switchOnOff(true); // active la pressure plate	
	}
		@Override
		public void interactWith(SpeakingPerson person) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(keyboard.get(Keyboard.L).isPressed()) { //si on presse la touche L
				
				time = gameTime;
				dialogs.clear();
				dialogs.add(new Dialog(person.getText(), "dialog.1", EnigmePlayer.this.getArea()));	
			}
	}
		
		@Override
		public void interactWith(Switchable switchable) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(switchable.isViewInteractable()) {
			
			if(keyboard.get(Keyboard.L).isPressed()) { //si on presse la touche L
							switchable.turnOnOff();// change l'état
						}
					}
			
			if(switchable.isCellInteractable()) {
					 
				float positionX = EnigmePlayer.this.getPosition().x;
				float positionY = EnigmePlayer.this.getPosition().y;
				
					if(positionX == switchable.getPosition().x && EnigmePlayer.this.getIsMoving() && positionY == switchable.getPosition().y) {
						
						//active l'interactable si le player est exactement sur la cellule et est en train de bouger
					   switchable.turnOnOff();
					   
					}
					
			}
		}
		
	
		
		@Override
		public void interactWith(Pickup pickup) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(keyboard.get(Keyboard.L).isPressed()) { //si on presse la touche L
				
							pickup.disappear();  //fait disparaitre le pickup
							
							time = gameTime;    //le temps actuel est mémorisé
							dialogs.clear();    //les dialogues actuels sont enlevés
							dialogs.add(new Dialog(pickup.getText(), "dialog.1", EnigmePlayer.this.getArea())); //le dialogue actuel est ajouté
				}
							
			}
		
		@Override
		public void interactWith(Ressources ressource) {
			
			Keyboard keyboard = EnigmePlayer.this.getArea().getKeyboard();
			
			if(keyboard.get(Keyboard.L).isPressed()) {
			
				ressource.actOnLives(EnigmePlayer.this);
			
			}
			
		}
			
			

}
		
		
	
	
	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		//personnage = new Sprite("ghost.1", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
		handler = new EnigmePlayerHandler();
		
		gameTime = 0;
		dialogs = new ArrayList<Dialog>();
		
		spritesDOWN = separateSprite("girl.5", 4, 1f, 1.3125f, this, 0);
		spritesLEFT = separateSprite("girl.5", 4, 1f, 1.3125f, this, 16);
		spritesUP = separateSprite("girl.5", 4, 1f, 1.3125f, this, 32);
		spritesRIGHT = separateSprite("girl.5", 4, 1f, 1.3125f, this, 48);
	}
	
	/**
	 * enterArea method : permet au player d'entrer dans une aire à une position donnee 
	 * @param area(Area) : l'aire dans laquelle le player veut rentrer
	 * @param position(DiscreteCoordinates) : les positions de départ de l'aire
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		this.setCurrentPosition(position.toVector());
		resetMotion();
	}
	
	/**
	 * leaveArea method : l'acteur se desenregistre de l'area
	 * @param area(Area) : area où se trouve l'acteur et qu'il veut quitter
	 */
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

		return true;
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
		
		super.draw(canvas);
		
		// on test l'orientation du personnage afin de savoir quel tableau de sprites utiliser
		
		if(this.getOrientation().equals(Orientation.DOWN)) {
			draw(canvas,spritesDOWN,moves); // on appelle la methode draw qui se trouve dans l'interface AnimationSprite
		}
		if(this.getOrientation().equals(Orientation.LEFT)) {
			draw(canvas,spritesLEFT,moves);
		}
		if(this.getOrientation().equals(Orientation.RIGHT)) {
			draw(canvas,spritesRIGHT,moves);
		}
		if(this.getOrientation().equals(Orientation.UP)) {
			draw(canvas, spritesUP,moves);
		}
		
		if(this.getIsMoving()) {
			++moves;
		}
		
		if(moves>=4) {
			moves=0;
		}
		
		for(int i = 0; i < dialogs.size(); ++i) {
			dialogs.get(i).draw(canvas);   //les dialogues actuels sont dessinés
		}
		
		
	}
	
	@Override
	public void update(float deltatime) {
		
		gameTime += deltatime; //le temps de jeu est enregistré
		
		Keyboard keyboard = this.getArea().getKeyboard();
		Button left = keyboard.get(Keyboard.LEFT);
		Button right = keyboard.get(Keyboard.RIGHT);
		Button up = keyboard.get(Keyboard.UP);
		Button down = keyboard.get(Keyboard.DOWN);
		
		
		
		// update du champ de vision en même temps que l'orientation.
		
		if(left.isDown()) {
			
			if(this.getOrientation()== Orientation.LEFT) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
				
			}
			else {
				setOrientation(Orientation.LEFT);
				
			}
			
		}
		if(right.isDown()) {
			
			if(this.getOrientation()== Orientation.RIGHT) {
				
				move(ANIMATION_DURATION); 
				
				
			}
			else {
				setOrientation(Orientation.RIGHT);
				
			}
			
		}
		if(up.isDown()) {
			
			if(this.getOrientation()== Orientation.UP) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
				
			}
			else {
				setOrientation(Orientation.UP);
				
			}
			
		}
		if(down.isDown()) {
			
			if(this.getOrientation()== Orientation.DOWN) {
				
				move(ANIMATION_DURATION); // Prends en parametre la vitesse à laquelle on veut deplacer l'acteur
				
			}
			else {
				setOrientation(Orientation.DOWN);
				
			}
			
		}
		
		if(dialogs.size() > 0) {
	
			if(gameTime - time > 5) {
				dialogs.clear();    //les dialogues actuels sont enlevés après 5 secondes
			}
			
		}
		
		
		super.update(deltatime);
		
	}
	
	/***
	 * setIsPassingDoor method : met travPorte à true et update l'attribut lastPassedDoor
	 * @param door(Door):porte que l'acteur est en train de traverser
	 */
	public void setIsPassingDoor(Door door) {
		travPorte(true);
		lastPassedDoor = door;
	}
	
	//getter de lastPassedDoor
	protected Door passedDoor() {
		return lastPassedDoor;
	}
	
	// getter de travporte
	protected boolean getTravPorte() {
		
		return travPorte;
	}
	
	// reset travporte
	protected void resetTravPorte() {
		travPorte = false;
		this.setOrientation(lastPassedDoor.getOrientation());
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
		if(keyboard.get(Keyboard.L).isPressed()) { // indique que l'acteur veut une interaction à distanceh
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
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}


}
