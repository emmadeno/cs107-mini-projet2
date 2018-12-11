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
	
	/// an AreaEntity knows its own Area 
	private Area ownerArea; 
	/// Orientation of the AreaEntity in the Area 
	private Orientation orientation; 
	/// Coordinate of the main Cell linked to the entity 
	private DiscreteCoordinates currentMainCellCoordinates;

	/**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
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
      
        return currentMainCellCoordinates;
    }
    
    @Override
    protected void setCurrentPosition(Vector v){
        
        if (DiscreteCoordinates.isCoordinates(v)) { // si assez proche de coordonnées discrètes
        	final Vector vect = v.round(); //coord arrondies
        	currentMainCellCoordinates = new DiscreteCoordinates((int)vect.getX(),(int)vect.getY()); 
        	super.setCurrentPosition(vect);
        }
        else {
        	super.setCurrentPosition(v);
        }
        
    }
    
    /**
     * 
     * @return l'aire associe a l'acteur
     */
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
    
    /**
     * 
     * @param area(Area) : change l'ownerArea de l'acteur lorsqu'il change d'aire
     */
    public void setOwnerArea(Area area) {
    	ownerArea = area;
    }

}
