/*
 *	Author:      Emmanuelle Denove
 *	Date:        25 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room2;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;;

public class Demo2 extends AreaGame {
	
	public Demo2() {
		super.begin(super.getWindow(), super.getFileSystem());
		Area Room1 = new Room1();
		Area Room2 = new Room2();
		Room1.begin(getWindow(), getFileSystem());
		Room2.begin(getWindow(), getFileSystem());
		super.addArea(Room1);
		super.addArea(Room2);
		super.setCurrentArea("LevelSelector", true);
	}

	@Override
	public int getFrameRate() {
		// TODO Auto-generated method stub
		return 24;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Demo2";
	}

}
