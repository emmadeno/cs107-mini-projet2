package ch.epfl.cs107.play.game.demo1.actor;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Window;

public class MovingRock extends GraphicsEntity {

	private final TextGraphics text;
	
	public MovingRock(Vector position, String text) {
		
		// appel du constructeur de la super classe
		super(position , new ImageGraphics(ResourcePath.getSprite("rock.3"),
				0.1f,0.1f, null , Vector.ZERO , 1.0f, -Float.MAX_VALUE));
		
		// initialisation de text
		this.text = new TextGraphics(text, 0.04f, Color.BLUE);
		
		this.text.setParent(this);// accrocher le texte à rock
		this.text.setAnchor(new Vector(-0.15f, 0.15f));// position du texte par rapport à rock
	}
	
	@Override
	public void draw (Canvas canvas) { // redefinition de draw pour draw text
		
		super.draw(canvas);
		text.draw(canvas);
	}
	
	@Override
	public void update(float deltaTime){ // faire bouger la pierre
	
		setCurrentPosition(getPosition().sub(0.005f, 0.005f)) ;
		}

	
	
}
