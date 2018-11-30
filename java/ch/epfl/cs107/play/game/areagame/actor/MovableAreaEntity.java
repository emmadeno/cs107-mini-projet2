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

    //methodes permettant de savoir quells cellules sont quittees ou atteintes par l'acteur
    protected final List<DiscreteCoordinates> getLeavingCells(){
    	
    	return getCurrentCells();
    }
    
    protected final List<DiscreteCoordinates> getEnteringCells(){
    	
    	List<DiscreteCoordinates> coordList = new ArrayList<DiscreteCoordinates>();
    
    	for (DiscreteCoordinates coordinate : getCurrentCells()) {
    		if (coordinate.x <= getArea().getWidth() && coordinate.y <= getArea().getHeight()) {
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
     * 
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected boolean move(int framesForMove){
        
    	if (!isMoving || this.getPosition() == targetMainCellCoordinates.toVector()) {
    		boolean canEnter = this.getArea().enterAreaCells(this, getEnteringCells());
    		boolean canLeave = this.getArea().leaveAreaCells(this, getLeavingCells());
    		
    		if (!canLeave || !canEnter) {
    			return false;
    		}
    		else {
    			if (framesForMove <= 1) {
    				framesForCurrentMove = framesForMove;
    			}
    			else {
    				framesForCurrentMove = 1;
    			}
    			
    			Vector orientation = getOrientation().toVector();
    			targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
    			
    			return true;
    			
    		}
    	}
    	
        return false;
    }


    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        if (this.getPosition() != targetMainCellCoordinates.toVector() && isMoving) {
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
}
