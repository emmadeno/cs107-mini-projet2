/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class EnigmeArea extends Area{
	
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
		return "Enigme";
	}

	@Override
	public float getCameraScaleFactor() {
		return 22;
	}

}
