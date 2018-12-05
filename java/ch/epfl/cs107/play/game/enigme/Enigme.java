package ch.epfl.cs107.play.game.enigme;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room2;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import play.game.enigme.area.Level1;
import play.game.enigme.area.Level2;
import play.game.enigme.area.Level3;
import play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	private Area Room0, Room1, Room2, Room3;
	private EnigmePlayer player;
	private static final int FRAMESCALE = 22;
	
	public Enigme(Window window, FileSystem filesystem) {
		
		//initialisation 
		this.Room0 = new LevelSelector();
		this.Room1 = new Level1();
		this.Room2 = new Level2();
		this.Room3 = new Level3();
	}


    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem filesystem) {
		super.begin(window, filesystem);
		DiscreteCoordinates initialCoord = new DiscreteCoordinates(5,5);
		this.player = new EnigmePlayer(Room0, Orientation.DOWN, initialCoord);
		addArea(Room0);
		addArea(Room1);
		addArea(Room2);
		addArea(Room3);
		Area currentArea = super.setCurrentArea(Room0.getTitle(), true);
		currentArea.registerActor(player);
		currentArea.setViewCandidate(player);
		
		return true;
	}

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        
        // appeler le interactwith du player
  
        
        
        if (player.getTravPorte()) {
        	
        	System.out.println("passe dans une porte");
        	
        	Door passedDoor = player.passedDoor();
        	player.leaveArea(getCurrentArea());
        	Area currentArea = super.setCurrentArea(passedDoor.getDestination(), true);
        	player.enterArea(currentArea, passedDoor.getInitialCoord());
        	player.setOwnerArea(currentArea);
			currentArea.setViewCandidate(player);
			player.resetTravPorte();
        }
    }


    @Override
    public int getFrameRate() {
        return 24;
    }
}

