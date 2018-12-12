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
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Enigme0;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Enigme1;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Level1;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Level2;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.Level3;
import ch.epfl.cs107.play.game.enigme.area.enigmeArea.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	private Area Room0, Room1, Room2, Room3, Room4, Room5, Room6;
	private EnigmePlayer player;
	private static final int FRAMESCALE = 22;
	
	public Enigme() {
		
		//initialisation 
		   Room0 = new LevelSelector();
		   Room1 = new Level1();
		   Room2 = new Level2();
		   Room3 = new Level3();
		   Room4 = new Enigme0();
		   Room5 = new Enigme1();
		   Room6 = new Enigme2();
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
		addArea(Room4);
		addArea(Room5);
		addArea(Room6);
		Area currentArea = super.setCurrentArea(Room0.getTitle(), true);
		currentArea.registerActor(player);
		currentArea.setViewCandidate(player);
		
		return true;
	}

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
 
        if (player.getTravPorte()) {
        	
        	Door passedDoor = player.passedDoor();
        	changeArea(passedDoor.getDestination(), passedDoor.getInitialCoord());
			player.resetTravPorte();
        }
        
     //si le joueur n'a plus de vies, le jeu est reset
        if(!player.isOn()) {
        	
    		resetGame();
    		
        	}
  }
    
   @Override
   protected void resetGame() {
	   super.resetGame();
	   Room0 = new LevelSelector();
	   Room1 = new Level1();
	   Room2 = new Level2();
	   Room3 = new Level3();
	   Room4 = new Enigme0();
	   Room5 = new Enigme1();
	   Room6 = new Enigme2();
	   this.begin(getWindow(), getFileSystem());
   }
   
   /**
    * changeArea method : permet de changer d'aire
    * @param destination (String) : nom de l'aire de destination
    * @param coord (DiscreteCoordinates): coordonnées d'arivée dans l'aire de destination
    */
   private void changeArea(String destination, DiscreteCoordinates coord) {
	   player.leaveArea(getCurrentArea());
	   Area currentArea = super.setCurrentArea(destination, false);
   	   player.enterArea(currentArea, coord);
       player.setOwnerArea(currentArea);
	   currentArea.setViewCandidate(player);
   }


    @Override
    public int getFrameRate() {
        return 24;
    }
    

}

