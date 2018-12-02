package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

    // TODO implements me #PROJECT #TUTO

    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
	
	/// an AreaEntity knows its own Area 
	private Area ownerArea; 
	/// Orientation of the AreaEntity in the Area 
	private Orientation orientation; 
	/// Coordinate of the main Cell linked to the entity 
	private DiscreteCoordinates currentMainCellCoordinates;

    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

        super(position.toVector());
        // TODO implements me #PROJECT #TUTO
        ownerArea = area;
        this.orientation = orientation;
        currentMainCellCoordinates = position;
    }


    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        // TODO implements me #PROJECT #TUTO
        return currentMainCellCoordinates;
    }
    
    // redéfinition de la methode setcurrentposition de Entity
    protected void setCurrentPosition(Vector v){
        
        if (DiscreteCoordinates.isCoordinates(v)) {
        	DiscreteCoordinates position;
        	final Vector vect = v.round();
        	position = new DiscreteCoordinates((int)vect.getX(),(int)vect.getY());
        	currentMainCellCoordinates = new DiscreteCoordinates((int)vect.getX(),(int)vect.getY()); 
        	super.setCurrentPosition(vect);
        }
        
    }
    
    protected Area getArea() {
    	return ownerArea;
    }
    
    // getter setter pour l'orientation
    protected Orientation getOrientation() {
    	return orientation;
    }
    
    protected void setOrientation(Orientation orientation) {
    	this.orientation = orientation;
    }
    
    public void setOwnerArea(Area area) {
    	ownerArea = area;
    }

}
