package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

	// Context objects
	private Window window;
	private FileSystem fileSystem;
	private boolean hasBegun;
	
	// list of actors inside the area
	private List<Actor> actors;
	private List<Actor> registeredActors; 
	private List<Actor> unregisteredActors;
	
	//List of interactables inside the area
	private List<Interactor> interactors;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter ;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave ;
	
	// Camera Parameter
	// actor on which the view is centered 
	private Actor viewCandidate;
	// effective center of the view 
	private Vector viewCenter;
	
	// The behavior Map
	private AreaBehavior areaBehavior;
	

	public final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab; // set le areaBehavior du jeu courrant
		
	}
	
	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
    	
    	// Here decisions at the area level to decide if an actor
    	// must be added or not
    	boolean errorOccured = !actors.add(a) ;
    	if(a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.add((Interactor) a);
    	}
    	
    	if(a instanceof Interactable) {
    		errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
    	}
    	
    	if(errorOccured && !forced) {
    		
    	System.out.println("Actor " + a + " cannot be completely added , so remove it from where it was") ;
    	removeActor(a, true) ;
    	
    	}

    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
  
    	boolean errorOccured = !actors.remove(a) ;
    	
    	if(a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.remove((Interactor) a);
    	}
    	
    	if(a instanceof Interactable) {
    		errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
    	}
    	
    	if(errorOccured && !forced) {
    		
        	System.out.println("Actor " + a + " cannot be completely removed") ;
        	removeActor(a, true) ;
        	
        	}
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){

    	registeredActors.add(a);
        return true;
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){

    	unregisteredActors.add(a);
        return true;
    }

    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){

    	int width = areaBehavior.getWidth();
        return width;
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){

    	int height = areaBehavior.getHeight();
        return height;
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        
    	Keyboard keyboard = window.getKeyboard();
    	
    	return keyboard;
    	
    }

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        // TODO implements me #PROJECT #TUTO
    	
    	//initialisation des parametres
    	this.window = window;
    	this.fileSystem = fileSystem;
    	actors = new LinkedList<>();
    	registeredActors = new LinkedList<>();
    	unregisteredActors = new LinkedList<>();
    	interactablesToEnter = new HashMap<>();
    	interactablesToLeave = new HashMap<>();
    	interactors = new LinkedList<>();
    	viewCandidate = null;
    	viewCenter = Vector.ZERO;
    	
    	hasBegun = true;
    	
    	
        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }
    
    private final void purgeRegistration() {
    	
    	boolean veto = false; // ?
    	
    	for (int i = 0; i < registeredActors.size(); i++) {
    		addActor(registeredActors.get(i), veto);
    	}
    	for (int i = 0; i < unregisteredActors.size(); i++) {
    		removeActor(unregisteredActors.get(i), veto);
    	}
    	
    	for (Interactable key : interactablesToEnter.keySet()) {
    		areaBehavior.enter(key, interactablesToEnter.get(key));
    	}
    	for (Interactable key : interactablesToLeave.keySet()) {
    		areaBehavior.leave(key, interactablesToLeave.get(key));
    	}
    	
    	registeredActors.clear();
    	unregisteredActors.clear();
    	interactablesToEnter.clear();
    	interactablesToLeave.clear();
    }

    @Override
    public void update(float deltaTime) {
        
    	purgeRegistration();
    	updateCamera();
    	for (Actor actor : actors) {
    		actor.draw(window);
    		actor.update(deltaTime);
    	}
    	
    	for(Interactor interactor : interactors) {
    		
    		if(interactor.wantsCellInteraction()) {
    			
    			//System.out.println("rentre dans boucle cellinteraction");
    			
    			areaBehavior.cellInteractionOf(interactor);
    			
    		}
    		if (interactor.wantsViewInteraction()) {
    			
    			System.out.println("VIEW");
    			areaBehavior.viewInteractionOf(interactor);
    			
    		}
    	}
    }


    private void updateCamera () {
    	
    	if (viewCandidate != null) {
    		viewCenter = viewCandidate.getPosition();
    	}
    	Transform viewTransform = Transform.I.scaled(22).translated(viewCenter); // scaled permet de changer la dimension de la fenêtre
    	window.setRelativeTransform(viewTransform);
    	
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        // Do nothing by default
    	purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }
    
    public final void setViewCandidate(Actor a){ 
    	this.viewCandidate = a;
    }

    public boolean hasBegun() {
    	return hasBegun;
    }
    
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	if (areaBehavior.canLeave(entity, coordinates)) { // teste si la grille associée à l'aire permet de quitter les cell de coordinates
    		interactablesToLeave.put(entity, coordinates);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	if (areaBehavior.canEnter(entity, coordinates)) { // si entité peut inverstir les cellules
    		interactablesToEnter.put(entity, coordinates);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public AreaBehavior getAreaBehavior() {
    	return areaBehavior;
    }
    
    
}
