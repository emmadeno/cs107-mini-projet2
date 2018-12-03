/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea{
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		super.registerActor(new Background(this));
		return true;
	}
	
	@Override
	public String getTitle() {
		return "Level3";
	}

}
