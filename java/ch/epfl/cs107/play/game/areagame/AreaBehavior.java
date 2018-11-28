package ch.epfl.cs107.play.game.areagame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    // TODO implements me #PROJECT #TUTO
	private final Image behaviorMap; 
	private final int width, height; 
	/// We will convert the image into an array of cells 
	private final Cell[][] cells;
		
	 public abstract class Cell implements Interactable{
		 
		 	Set<Interactable> interact;
		 	private DiscreteCoordinates cellCoord;
		 
			public Cell(int x, int y){
				
				cellCoord = new DiscreteCoordinates(x,y);
				interact = new HashSet<>();
				
			}
			public List<DiscreteCoordinates> getCurrentCells(){
		 		
		 		List<DiscreteCoordinates> coordonees = new ArrayList<DiscreteCoordinates>();
		 		coordonees.add(cellCoord);
		 		return coordonees;
		 	}
			
			private void enter(Interactable entity) {
				interact.add(entity);
			}
			
			private void leave(Interactable entity) {
				interact.remove(entity);
			}
			
			protected boolean canEnter(Interactable entity) {
				if (interact.add(entity)) {
					return true;
				}
				else {
					return false;
				}
			}
			
			protected boolean canLeave(Interactable entity) {
				if (interact.remove(entity)) {
					return true;
				}
				else {
					return false;
				}
			}
	}
	
    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
        // TODO implements me #PROJECT #TUTO
    	
    	//initialisation de behaviorMap
    	behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);

    	//initialisation de width et height
    	width = behaviorMap.getWidth();
    	height = behaviorMap.getHeight();
    	
    	//initialisation du tableau
    	cells = new Cell[height][width];
    }
    
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
    		Cell currentCell = cells[coordinates.get(i).y][coordinates.get(i).x];
    		if (currentCell.canEnter(entity)) {
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

    // TODO implements me #PROJECT #TUTO
    
    public final int getWidth() {
    	
    	return width;
    }
    
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
