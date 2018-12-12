/*
 *	Author:      Emmanuelle Denove
 *	Date:        3 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior{
	
		
		public EnigmeBehavior(Window window, String fileName) {
			super(window, fileName);
			for (int y = 0; y < super.getHeight(); ++y) {
				for (int x = 0; x < super.getWidth(); ++x) {
	             EnigmeCellType cellType = EnigmeCellType.toType(super.getImage().getRGB(super.getHeight()-1-y, x));
	             super.getCell()[y][x] = new EnigmeCell(x, y, cellType);
				}
			}
		}
		
		public enum EnigmeCellType {
			NULL(0),
			WALL(-16777216),
			DOOR(-65536),
			WATER(-16776961),
			INDOOR_WALKABLE(-1), 
			OUTDOOR_WALKABLE(-14112955);
			
			final int type;
			
			EnigmeCellType(int type) {
				this.type = type;
			}
			
			static EnigmeCellType toType(int type) {
				
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
		
		public class EnigmeCell extends Cell {
			
			//définir les cellules assorties à Demo2Behavior pour dicter les contraintes
			
			private EnigmeCellType type;
			
			private EnigmeCell(int x, int y, EnigmeCellType type) {
				super(x,y);
				this.type = type;
			}
			
			public EnigmeCellType getType() {
				return type;
			}
			
			@Override
			protected boolean canEnter(Interactable entity) {
				if (this.getType() == EnigmeCellType.WALL || this.getType() == EnigmeCellType.NULL || this.getType() == EnigmeCellType.WATER) {
					return false;
				}
				for (Interactable i : interact) {
					if(i.takeCellSpace()) {
						return false;
					}
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
				v.interactWith(this);
			}

			@Override
			protected boolean canLeave(Interactable entity) {
				return true;
			}
			
		}

	}

