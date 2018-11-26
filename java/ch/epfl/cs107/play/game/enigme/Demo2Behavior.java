/*
 *	Author:      Emmanuelle Denove
 *	Date:        25 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior{
	
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		System.out.print("Hi");
		for (int y = 0; y < super.getHeight(); y++) {
			for (int x = 0; x < super.getWidth(); x++) {
             Demo2CellType cellType = Demo2CellType.toType(super.getImage().getRGB(super.getHeight()-1-y, x));
             super.getCell()[x][y] = new Demo2Cell(x, y, cellType);
			}
		}
	}
	
	public enum Demo2CellType {
		NULL(0),
		WALL(-16777216),
		DOOR(-65536),
		WATER(-16776961),
		INDOOR_WALKABLE(-1), 
		OUTDOOR_WALKABLE(-14112955);
		
		final int type;
		
		Demo2CellType(int type) {
			this.type = type;
		}
		
		static Demo2CellType toType(int type) {
			
			switch(type) {
			case -16777216 :
				return WALL;
			case -65536 :
				return DOOR;
			case -16776961 :
				return WATER;
			case -1 :
				return INDOOR_WALKABLE;
			case -14112955 :
				return OUTDOOR_WALKABLE;
			default :
				return NULL;
			
			}
	
			
		}
	}
	
	public class Demo2Cell extends Cell {
		
		private Demo2CellType type;
		
		public Demo2Cell(int x, int y, Demo2CellType type) {
			super(x,y);
			this.type = type;
		}
		
	}

}

