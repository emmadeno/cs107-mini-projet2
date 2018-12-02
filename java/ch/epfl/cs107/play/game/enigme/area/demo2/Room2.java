/*
 *	Author:      Emmanuelle Denove
 *	Date:        26 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme.area.demo2;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Room2 extends Area{
		
		@Override
		public boolean begin(Window window, FileSystem fileSystem) {
			super.begin(window, fileSystem);
			super.setBehavior(new Demo2Behavior(window, getTitle()));
			super.registerActor(new Background(this));
			return true;
		}
		
		@Override
		public String getTitle() {
			return "Level1";
		}
		@Override
		public float getCameraScaleFactor() {
			return 22;
		

	}

}
