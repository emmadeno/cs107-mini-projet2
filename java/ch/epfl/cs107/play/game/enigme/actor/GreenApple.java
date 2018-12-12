/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.PlayerLives;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class GreenApple extends Apple {
	
	private Sprite greenApple = new Sprite("apple.2", 1, 1.f, this);

	public GreenApple(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
	}
	
	@Override
	public void actOnLives(PlayerLives i) {
		i.addLife();
	}
	
	@Override
	public void draw(Canvas canvas) {
		greenApple.draw(canvas);
	}
	
	@Override
	public String getText() {
		return "Une vie de plus ! ";
	}
	
	

}
