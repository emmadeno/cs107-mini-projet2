/*
 *	Author:      Emmanuelle Denove
 *	Date:        25 Nov 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior{
	
	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		
		// associe a chaque cellule de la grille un type
		for (int y = 0; y < super.getHeight(); ++y) {
			for (int x = 0; x < super.getWidth(); ++x) {
             Demo2CellType cellType = Demo2CellType.toType(super.getImage().getRGB(super.getHeight()-1-y, x));
             super.getCell()[y][x] = new Demo2Cell(x, y, cellType);
			}
		}
	}
	
	public enum Demo2CellType {
		NULL(0),
		WALL(-16777216), // RGB code of black
		DOOR(-65536),	// RGB code of red
		WATER(-16776961),  // RGB code of blue
		INDOOR_WALKABLE(-1), 
		OUTDOOR_WALKABLE(-14112955);
		
		final int type;
		
		/**
		 * @param type(int) : set type 
		 */
		Demo2CellType(int type) {
			this.type = type;
		}
		
		
		/**
		 * toType method : convertit un int en type 
		 * @param type (int) : prend en parametre le integer representant le type de la cellule
		 * @return : le type associe
		 */
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
		
		//définir les cellules assorties à Demo2Behavior pour dicter les contraintes
		
		private Demo2CellType type;
		
		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x,y);
			this.type = type;
		}
		
		public Demo2CellType getType() {
			return type;
		}
		
		@Override
		protected boolean canEnter(Interactable entity) {
			if (this.getType() == Demo2CellType.WALL || this.getType() == Demo2CellType.NULL) {
				return false;
			}
			return true;
		}

		@Override
		public boolean takeCellSpace() {
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			return true;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			// interactions are not used in Demo2
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}
		
	}

}

