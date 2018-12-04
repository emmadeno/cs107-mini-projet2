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
    // TODO implements me #PROJECT #TUTO
	
	//occupe une liste de cellules
	public List<DiscreteCoordinates> getCurrentCells();
	
	// rends la cellule non traversable si il l'occupe
	public boolean takeCellSpace();
	
	//indique si il accepte les interactions distantes
	public boolean isViewInteractable();
	
	// indique si il accepte les interactions de contact
	public boolean isCellInteractable();
	
	void acceptInteraction(AreaInteractionVisitor v);
	
}
