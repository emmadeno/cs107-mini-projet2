/*
 *	Author:      Emmanuelle Denove
 *	Date:        29 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme.area.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity{

	private boolean travPorte; //indique si l'acteur passe une porte
	private Sprite personnage;
	private final static int ANIMATION_DURATION = 8;
	
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		personnage = new Sprite("ghost.1", 1, 1.f, this);
		this.setOrientation(Orientation.DOWN);
	}
	
	/**
	 * enterArea method : permet au player d'entrer dans une aire à une position donnee 
	 * @param area(Area) : l'aire dans laquelle le player veut rentrer
	 * @param position(DiscreteCoordinates) : les positions de départ de l'aire
	 */
	public void enterArea(Area area, DiscreteCoordinates position) {
		area.registerActor(this);
		super.setCurrentPosition(position.toVector());
		resetMotion();
	}
	
	/**
	 * leaveArea method : l'acteur se desenregistre de l'area
	 * @param area(Area) : area où se trouve l'acteur et qu'il veut quitter
	 */
	public void leaveArea(Area area) {
		area.unregisterActor(this);
	}
	
	/**
	 * travPorte metohd : mettre travporte à true ou false
	 * @param isTravPorte(boolean)
	 */
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
		personnage.draw(canvas);
	}
	
	@Override
	public void update(float deltatime) {
		
		Keyboard keyboard = this.getArea().getKeyboard();
		Button left = keyboard.get(Keyboard.LEFT);
		Button right = keyboard.get(Keyboard.RIGHT);
		Button up = keyboard.get(Keyboard.UP);
		Button down = keyboard.get(Keyboard.DOWN);
		
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
		
		super.update(deltatime);
		
		
		
	}
	
	@Override
	protected boolean move(int framesForMove) {
		
		super.move(framesForMove);
		List<DiscreteCoordinates> enteringCells = this.getCurrentCells();
		
		for (DiscreteCoordinates coordinate : enteringCells) {
			
			int x = coordinate.x;
			int y = coordinate.y;
			Demo2Cell currentCell = (Demo2Cell) this.getArea().getAreaBehavior().getCell()[y][x];
			
			System.out.println(currentCell.getType());
			
			// si la cellule entrante est une porte
			if(currentCell.getType() == Demo2CellType.DOOR) {
				travPorte(true);
			}
			else {
				travPorte(false);
			}
			
			System.out.print(travPorte);
			
		}
		return true;
	}
	
	public boolean getTravPorte() {
		
		return travPorte;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
