package ch.epfl.cs107.play.game.areagame;

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

	
	public abstract class Cell{
		
		public Cell(int x, int y){
			
			DiscreteCoordinates cellCoord = new DiscreteCoordinates(x,y);
			
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

    // TODO implements me #PROJECT #TUTO

}
