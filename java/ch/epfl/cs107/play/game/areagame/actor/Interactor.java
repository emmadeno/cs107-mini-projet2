package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects asking for interaction (i.e. can interact with some Interactable)
 * @see Interactable
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactor {

    public List<DiscreteCoordinates> getCurrentCells();
    
    public List<DiscreteCoordinates> getFieldOfViewCells();
    
    public boolean wantsCellInteraction();
    
    public boolean wantsViewInteraction();
    
    public void interactWith(Interactable other);

}
