/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public abstract class PlayerLives extends MovableAreaEntity implements Logic{
	
	private List<Logic> lives;
	private Map <Integer, Sprite> lifeImages;
	private Sprite life1Image, life2Image, life3Image;

	public PlayerLives(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		
		lifeImages = new HashMap<Integer, Sprite>();
		
		life1Image = new Sprite("heart1", 0.5f, 0.5f, this);
		life2Image = new Sprite("heart1", 0.5f, 0.5f, this);
		life3Image = new Sprite("heart1", 0.5f, 0.5f, this);
		
		life1Image.setAnchor(new Vector(-0.3f, 1.25f));
		life2Image.setAnchor(new Vector(0.25f, 1.25f));
		life3Image.setAnchor(new Vector(0.8f, 1.25f));
		
		lifeImages.put(1, life1Image);
		lifeImages.put(2, life2Image);
		lifeImages.put(3, life3Image);

		lives = new ArrayList<Logic>();
		lives.add(TRUE);
		lives.add(TRUE);
		lives.add(TRUE);

	}
	

	@Override
	public boolean isOn() {
		
		if(lives.size() > 0) {
			return true; //entity is on if it has more than one life
		}
		
		return false;
}
	
/**
 * removeLife method : removes a life
 */
	public void removeLife() {
		if(lives.size() > 0) { //tests if entity still has lives left
			lives.remove(lives.size() - 1);
		}
	}
	
	/**
	 * addLife method : adds a life
	 */
	
	public void addLife() {
		if(lives.size() < 3) { //tests if entity has less than maximum lives
			lives.add(TRUE);
		}

	}
	
	@Override
	public void draw(Canvas canvas) {
		for(int i = 1; i <= lives.size(); ++i) {
			lifeImages.get(i).draw(canvas); //dessine le sprite associé
		}
	}
	
	/**
	 * resetLife method : resets all the player's lives
	 */
	public void resetLives() {
		int size = lives.size();
		for(int i = size; i < 3; i++) {
			lives.add(TRUE);
		}
	}
	
	/**
	 * kill method : removes all the player's lives
	 */
	public void kill() {
		while(lives.size() > 0) {
			lives.remove(lives.size() - 1);
		}
	}
	

}
