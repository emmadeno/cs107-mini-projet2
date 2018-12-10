package ch.epfl.cs107.play.game.areagame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
	private final Image behaviorMap; 
	private final int width, height; 
	/// We will convert the image into an array of cells 
	private final Cell[][] cells;
	
	/**
	 *Each game will have its own Cell extension
	 */
	public abstract class Cell implements Interactable{
		 
		 	protected Set<Interactable> interact;
		 	private DiscreteCoordinates cellCoord; // coordonnées de la cellule sur la grille
		 
			public Cell(int x, int y){
				
				cellCoord = new DiscreteCoordinates(x,y);
				interact = new HashSet<>();
				
			}
			@Override
			public List<DiscreteCoordinates> getCurrentCells(){
		 		
		 		List<DiscreteCoordinates> coordonees = new ArrayList<DiscreteCoordinates>();
		 		coordonees.add(cellCoord);
		 		return coordonees;
		 	}
			/**
			 * @param entity(Interactable) : ajoute entity au set d'interactables
			 */
			private void enter(Interactable entity) {
				interact.add(entity);
			}
			
			/**
			 * @param entity(Interactable) : supprime entity du set d'interactables
			 */
			private void leave(Interactable entity) {
				interact.remove(entity);
			}
			
			
			protected abstract boolean canEnter(Interactable entity);
			
			protected abstract boolean canLeave(Interactable entity);
			
			private void cellInteractionOf(Interactor interactor) {
				for(Interactable interactable : interact) {
					if(interactable.isCellInteractable()) {
						interactor.interactWith(interactable);
					}
				}
			}
			
			private void viewInteractionOf(Interactor interactor) {
				for(Interactable interactable : interact) {
					if(interactable.isViewInteractable()) {
						interactor.interactWith(interactable);
					}
				}
			}
			 
	}
	
    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
    	
    	//initialisation de behaviorMap
    	behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);

    	//initialisation de width et height
    	width = behaviorMap.getWidth();
    	height = behaviorMap.getHeight();
    	
    	//initialisation du tableau
    	cells = new Cell[width][height];
    }
    
    /**
     * 
     * @param entity(Interactable) : l'acteur qui veut se placer sur la cellule
     * @param coordinates(DiscreteCoordinates) : coordonnées de la cellule sur laquelle il veut se placer
     * @return
     */
    public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	int authorization = 0;
    	for(int i = 0; i < coordinates.size(); i++) {
    		Cell currentCell = cells[coordinates.get(i).y][coordinates.get(i).x];
    		if (currentCell.canLeave(entity)) {
    			++authorization;
    		}
    	}
    	
    	if(authorization == coordinates.size()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	int authorization = 0;
    	for(int i = 0; i < coordinates.size(); i++) {
    		if(coordinates.get(i).x >= 0 && coordinates.get(i).y >= 0) {
    		Cell currentCell = cells[coordinates.get(i).y][coordinates.get(i).x];

    		if (currentCell.canEnter(entity)) {
    			++authorization;
    		}
    		}
    		
    	}
    	
    	if(authorization == coordinates.size()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for(int i = 0; i < coordinates.size(); i++) {
    		Cell currentCell = cells[coordinates.get(i).y][coordinates.get(i).x];
    		currentCell.leave(entity);
    	}
    }
    
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for(int i = 0; i < coordinates.size(); i++) {
    		Cell currentCell = cells[coordinates.get(i).y][coordinates.get(i).x];
    		currentCell.enter(entity);
    	}
    }

    public void cellInteractionOf(Interactor interactor) {
  
    	for (DiscreteCoordinates coord : interactor.getCurrentCells()) {
    		Cell cell = cells[coord.y][coord.x];
    		cell.cellInteractionOf(interactor);
    	}
    }
    
    public void viewInteractionOf(Interactor interactor) {
    	interactor.getFieldOfViewCells().get(0);
    	for (DiscreteCoordinates coord : interactor.getFieldOfViewCells()) {
    		Cell cell = cells[coord.y][coord.x];
    		cell.viewInteractionOf(interactor);
    	}
    	
    }
    /**
     * Getter for the image width
     * @return (int) : the width in number of columns
     */
    public final int getWidth() {
    	
    	return width;
    }
    
    /**
     * Getter for the image height
     * @return (int) : the height in number of rows
     */
    public final int getHeight() {
    	
    	return height;
    }
    
    public final Image getImage() {
    	return behaviorMap;
    }
    
    public final Cell[][] getCell() {
    	return cells;
    }
}
