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
	
	private Area Room1, Room2;
	
	public Demo2(Window window, FileSystem filesystem) {
		
		//initialisation 
		super.begin(window, filesystem);
		this.Room1 = new Room1();
		this.Room2 = new Room2();
		Room1.begin(getWindow(), getFileSystem());
		Room2.begin(getWindow(), getFileSystem());
		addArea(Room1);
		addArea(Room2);
		super.setCurrentArea(Room2.getTitle(), true);
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
