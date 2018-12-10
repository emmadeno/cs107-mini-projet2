/*
 *	Author:      Emmanuelle Denove
 *	Date:        25 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme.area.demo2;
import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Room1 extends Area{
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		Demo2Behavior behavior = new Demo2Behavior(window, getTitle());
		super.begin(window, fileSystem);
		super.setBehavior(behavior);
		super.registerActor(new Background(this));
		return true;
	}
	
	@Override
	public String getTitle() {
		return "LevelSelector";
	}
	@Override
	public float getCameraScaleFactor() {
		return 22;
	}

}
