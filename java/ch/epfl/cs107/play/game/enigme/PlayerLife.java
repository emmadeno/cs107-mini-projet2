/*
 *	Author:      Emmanuelle Denove
 *	Date:        11 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PlayerLife extends EnigmePlayer implements Logic{
	
	private List<Logic> lives;
	private Map <Integer, Sprite> lifeImages;
	private Sprite life1Image, life2Image, life3Image;

	public PlayerLife(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		
		lifeImages = new HashMap<Integer, Sprite>();
		
		life1Image = new Sprite("heart1", 0.5f, 0.5f, this);
		life2Image = new Sprite("heart1", 0.5f, 0.5f, this);
		life3Image = new Sprite("heart1", 0.5f, 0.5f, this);
		
		life1Image.setAnchor(new Vector(-0.3f, 1f));
		life2Image.setAnchor(new Vector(0.25f, 1f));
		life3Image.setAnchor(new Vector(0.8f, 1f));
		
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
			return true;
		}
		
		return false;
}
	
	public boolean removeLife() {
		if(lives.size() > 0) {
			lives.remove(lives.size() - 1);
			return true;
		}
		return false;
	}
	
	public boolean addLife() {
		if(lives.size() < 3) {
			lives.add(TRUE);
			return true;
		}
		return false;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		for(int i = 1; i <= lives.size(); ++i) {
			lifeImages.get(i).draw(canvas);
		}
	}
	
	
	

}
