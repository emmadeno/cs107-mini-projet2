/*
 *	Author:      Emmanuelle Denove
 *	Date:        25 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room2;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;;

public class Demo2 extends AreaGame {
	
	private Area Room1, Room2;
	private Demo2Player player;
	private static final int FRAMESCALE = 22;
	
	public Demo2() {
		
		//initialisation 
		this.Room1 = new Room1();
		this.Room2 = new Room2();
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public String getTitle() {

		return "Demo2";
	}
	
	public boolean begin(Window window, FileSystem filesystem) {
		super.begin(window, filesystem);
		DiscreteCoordinates initialCoord = new DiscreteCoordinates(5,5);
		this.player = new Demo2Player(Room1, Orientation.DOWN, initialCoord);
		addArea(Room1);
		addArea(Room2);
		Area currentArea = super.setCurrentArea(Room1.getTitle(), true);
		currentArea.registerActor(player);
		currentArea.setViewCandidate(player);
		
		return true;
	}
	
	@Override
	
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if(player.getTravPorte()) {
			
			
			if(getCurrentArea().getTitle().compareTo("LevelSelector")==0) {
				
				DiscreteCoordinates initialCoord = new DiscreteCoordinates(5,2);
				this.player.leaveArea(getCurrentArea());
				Area currentArea = super.setCurrentArea(Room2.getTitle(), true);
				this.player.enterArea(currentArea, initialCoord);
				player.setOwnerArea(currentArea);
				currentArea.setViewCandidate(player);
			}
			
			else if(getCurrentArea().getTitle().compareTo("Level1")==0) {
				
				DiscreteCoordinates initialCoord = new DiscreteCoordinates(5,5);
				this.player.leaveArea(getCurrentArea());
				Area currentArea = super.setCurrentArea(Room1.getTitle(), true);
				this.player.enterArea(currentArea, initialCoord);
				player.setOwnerArea(currentArea);
				currentArea.setViewCandidate(player);
			} 
			
		}
	}

}
