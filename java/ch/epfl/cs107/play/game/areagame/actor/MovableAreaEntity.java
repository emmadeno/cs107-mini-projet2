package ch.epfl.cs107.play.game.areagame.actor;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
	
	//Indicate if actor is moving
    private boolean isMoving;
    
    //Indicates how many frames the current move is supposed to take
    private int framesForCurrentMove;
    
    //The target cell where the mainCell will be after the motion
    private DiscreteCoordinates targetMainCellCoordinates;
    

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * @return (List<DiscreteCoordinates>) : cellules que l'acteur va quitter
     */
    protected final List<DiscreteCoordinates> getLeavingCells(){
    	
    	return getCurrentCells();
    }
    
    /**
     * @return (List<DiscreteCoordinates>) : cellules vers lesquelles l'acteur va se déplacer
     */
    protected final List<DiscreteCoordinates> getEnteringCells(){
    	
    	List<DiscreteCoordinates> coordList = new ArrayList<DiscreteCoordinates>();
    
    	for (DiscreteCoordinates coordinate : getCurrentCells()) {
    		if (coordinate.x > 0 && coordinate.y > 0 && 
    			coordinate.x <= getArea().getWidth() && 
    			coordinate.y <= getArea().getHeight()) {
    		    coordList.add(coordinate.jump(this.getOrientation().toVector()));
    		}
    	}
    	
    	return coordList;
    	
    }
    
    
    
    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        
    	isMoving = false;
    	framesForCurrentMove = 0;
    	targetMainCellCoordinates = getCurrentMainCellCoordinates();
    }

    /**
     * move method : amorce un déplacement et retourne true si l'acteur n'est pas déjà en train de bouger
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected boolean move(int framesForMove){
    
    	// si il n'est pas entrain de se déplacer ou qu'il a atteint sa cible
    	if (!isMoving || getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) { 
    		
    		// restrictions de l'aire
    		boolean canLeave = this.getArea().leaveAreaCells(this, getLeavingCells());
    		boolean canEnter = this.getArea().enterAreaCells(this, getEnteringCells());
    		if (canLeave && canEnter) {
    		
    			if (framesForMove < 1) {
    				framesForCurrentMove = 1;
    			}
    			else {
    				framesForCurrentMove = framesForMove;
    			}
    			
    			Vector orientation = getOrientation().toVector();
    			    			
    			targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
    			isMoving = true;
    	}
    		return true;
    	}
    	
        return false;
    }


    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        if (isMoving && !getCurrentMainCellCoordinates().equals(targetMainCellCoordinates)) {
        	Vector distance = getOrientation().toVector();
        	distance = distance.mul(1.0f / framesForCurrentMove);
        	setCurrentPosition(getPosition().add(distance));
        }
        else {
        	resetMotion();
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        return null;
    }
    
    @Override
    protected void setOrientation(Orientation orientation) {
    	if(!isMoving) {
    		super.setOrientation(orientation);
    	}
    }
    
    protected boolean getIsMoving() {
    	return isMoving;
    }
}
