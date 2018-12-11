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
	
	 	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
	    	
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
	 
	@Override
	public void update(float deltaTime) {
	        
	    	purgeRegistration();
	    	updateCamera();
	    	
	    	//update des actors
	    	for (Actor actor : actors) {
	    		actor.draw(window);
	    		actor.update(deltaTime);
	    	}
	    	
	    	// gestion des interactions
	    	for(Interactor interactor : interactors) {
	    		
	    		if(interactor.wantsCellInteraction()) {
	    			
	    			areaBehavior.cellInteractionOf(interactor);
	    			
	    		}
	    		if (interactor.wantsViewInteraction()) {
	    			
	    			areaBehavior.viewInteractionOf(interactor);
	    			
	    		}
	    	}
	    }

	/**
	 * updateCamera method : permet à la caméra de suivre l'acteur lorsqu'il se déplace
	 */
	private void updateCamera () {
	    	
	    	if (viewCandidate != null) {
	    		viewCenter = viewCandidate.getPosition();
	    	}
	    	Transform viewTransform = Transform.I.scaled(this.getCameraScaleFactor()).translated(viewCenter); // scaled permet de changer la dimension de la fenêtre
	    	window.setRelativeTransform(viewTransform);
	    	
	    }
	
	/**
	 * setBehavior method : set areaBehavior
	 * @param ab(AreaBehavior) : areabehavior associe à l'aire
	 */
	protected final void setBehavior(AreaBehavior ab) {
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
    	
    	//test si l'acteur est un Interactor
    	if(a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.add((Interactor) a);
    	}
    	
    	//test si l'acteur est un interactable
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
    	
    	// si l'acteur est un interactor
    	if(a instanceof Interactor) {
    		errorOccured = errorOccured || !interactors.remove((Interactor) a);
    	}
    	
    	//test si l'acteur est un interactable
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


    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
        return true;
    }
    
   /**
    * purgeRegistration method : update actor and interactable lists every time the game is updated
    */
    
    private final void purgeRegistration() {
    	
    	boolean veto = false; 
    	
    	// ajoute dans l'aire les registeredActors
    	for (int i = 0; i < registeredActors.size(); i++) {
    		addActor(registeredActors.get(i), veto);
    	}
    	
    	// enleve de l'aire les unregisteredActors
    	for (int i = 0; i < unregisteredActors.size(); i++) {
    		removeActor(unregisteredActors.get(i), veto);
    	}
    	
    	//mets tout les interactablesToEnter dans l'aire
    	for (Interactable key : interactablesToEnter.keySet()) {
    		areaBehavior.enter(key, interactablesToEnter.get(key));
    	}
    	// enleve tout les interactablesToLeave de l'aire
    	for (Interactable key : interactablesToLeave.keySet()) {
    		areaBehavior.leave(key, interactablesToLeave.get(key));
    	}
    	
    	registeredActors.clear();
    	unregisteredActors.clear();
    	interactablesToEnter.clear();
    	interactablesToLeave.clear();
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        // Do nothing by default
    	purgeRegistration();
    }

    /**
     * setViewCandidate method : set viewCandidate to a
     * @param a (Actor) : actor that is suppose to be the ViewCandidate
     */
    public final void setViewCandidate(Actor a){ 
    	this.viewCandidate = a;
    }

    public boolean hasBegun() {
    	return hasBegun;
    }
    
    /**
     * leaveAreaCells method : enregistre une entity dans interactablesToLeave + test si entity peut quitter des cellules
     * @param entity(Interactable) 
     * @param coordinates(DiscreteCoordinates)
     * @return(boolean) : true si l'aire permet à entity de quitter les cellules
     */
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	if (areaBehavior.canLeave(entity, coordinates)) { // teste si la grille associée à l'aire permet de quitter les cell de coordinates
    		interactablesToLeave.put(entity, coordinates);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    /**
     * enterAreaCells method : enregistre une entity dans interactablesToEnter + test si entity peut investir des cellules
     * @param entity(Interactable) 
     * @param coordinates(DiscreteCoordinates)
     * @return(boolean) : true si l'aire permet à entity d'investir les cellules
     */
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
    
    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }
    
}
