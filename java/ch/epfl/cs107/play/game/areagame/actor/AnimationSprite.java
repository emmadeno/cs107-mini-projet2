package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public interface AnimationSprite {

	default Sprite[] separateSprite(String name,int frame,float width, float height, Positionable parent, int position, Vector anchor) {
		
		Sprite[] spriteAnim = new Sprite[frame];
		for (int i = 0; i<frame;++i ) {
			
			spriteAnim[i]= new Sprite(name,width,height,parent,new RegionOfInterest(position,i*21,16,21), anchor);
		}
		return spriteAnim;
		
	}
	
	default void draw (Canvas canvas,Sprite[] sprite) {
		sprite[0].draw(canvas);
	}
}
