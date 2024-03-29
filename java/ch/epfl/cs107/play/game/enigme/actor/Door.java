/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity{
	
	private String destinationArea;
	private DiscreteCoordinates initialCoord;
	private List<DiscreteCoordinates> otherCoordinates;
	
	
	/**
	 * 
	 * @param area(Area) : l'aire à laquelle appartient la porte
	 * @param orientation(Orientation): orientation
	 * @param destinationArea(String): nom de l'aire de destination
	 * @param position(DiscreteCoordinates): position de la porte dans l'aire
	 * @param initialCoord(DiscreteCoordinates): position de départ dans la nouvelle aire
	 * @param otherCoordinates(Circle): autre Coordonées
	 */
	public Door(Area area, Orientation orientation, String destinationArea, DiscreteCoordinates position, DiscreteCoordinates initialCoord, List<DiscreteCoordinates> otherCoordinates) {
		super(area, orientation, position);
		this.destinationArea = destinationArea;
		this.initialCoord = initialCoord;
		this.otherCoordinates = otherCoordinates;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		
		List<DiscreteCoordinates> allCells = new ArrayList<DiscreteCoordinates>();
		allCells.add(super.getCurrentMainCellCoordinates());
		for(int i = 0; i < otherCoordinates.size(); i++) {
			allCells.add(otherCoordinates.get(i));
		}
		return allCells;
	}

	@Override
	public boolean takeCellSpace() {
		
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		
		return true;
	}

	public String getDestination() {
		return destinationArea;
	}
	
	public DiscreteCoordinates getInitialCoord() {
		return initialCoord;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
		
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
