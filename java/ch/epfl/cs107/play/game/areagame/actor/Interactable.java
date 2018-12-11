package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
	
	/**
	 * 
	 * @return (DiscreteCoordinates) une liste de coordonnées qui indique 
	 * 								la position de l'acteur
	 */
	public List<DiscreteCoordinates> getCurrentCells();
	
	/**
	 * takeCellSpace method : rend la cellule non traversable si il l'occupe
	 * @return (boolean) true si n'est pas traversable
	 */
	public boolean takeCellSpace();
	
	/**
	 * isViewInteractable method: indique si l'acteur accepte les interactions à distance
	 */
	public boolean isViewInteractable();
	
	/**
	 * @return(boolean) true si il accepte les interactions de contact
	 */
	public boolean isCellInteractable();
	
	void acceptInteraction(AreaInteractionVisitor v);
	
}
