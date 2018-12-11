package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public interface AnimationSprite {
	/**
	 * separate Sprite method : separe les différents sprites présents dans une meme image
	 * @param name(String): nom du fichier
	 * @param frame(int): nombre d'images dans une colonne
	 * @param width(float): largueur du pers
	 * @param height(float): hauteur du personnage
	 * @param parent(Positionable): entitée associée au sprite
	 * @param position(int): position à laquelle s'interesser dans l'image png
	 * @return (Sprite[]) : retourne un tableau de 4 sprites
	 */
	default Sprite[] separateSprite(String name,int frame,float width, float height, Positionable parent, int position) {
		
		Sprite[] spriteAnim = new Sprite[frame];
		for (int i = 0; i<frame;++i ) {
			
			spriteAnim[i]= new Sprite(name,width,height,parent,new RegionOfInterest(position,i*21,16,21), Vector.ZERO);
		}
		return spriteAnim;
		
	}
	/**
	 * draw method: remplace la methode draw du personnage
	 * @param canvas
	 * @param sprite(Sprite[]): tableau de sprite associé à l'orientation
	 * @param moves(int) pour pouvoir changer d'image dans le sprite et animer l'acteur
	 */
	default void draw (Canvas canvas,Sprite[] sprite, int moves) {
		
		sprite[moves].draw(canvas);
	}
}
