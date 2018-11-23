package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

 public class Demo1 implements Game {
	 
	 private Actor actor1;		
	 private Actor actor2;
	 private Window window;
	 private FileSystem fileSystem;
	
	public String getTitle() {
		
		return "Demo1";
	}
	
	public int getFrameRate() {
		
		return 24;
	}
	
	public boolean begin(Window window, FileSystem fileSystem) {
		
		this.window = window;
		this.fileSystem = fileSystem;
		
		Transform viewTransform = Transform.I.scaled(2).translated(Vector.ZERO);
		window.setRelativeTransform(viewTransform);
		float radius = 0.2f;
		
		// creation des acteurs
		
		actor1 = new GraphicsEntity(Vector.ZERO ,
				new ShapeGraphics(new Circle(radius), null ,
						Color.RED , 0.005f)) ;
		actor2 = new MovingRock(new Vector(0.3f,0.1f), "Hello, I'm a moving rock");
		
		return true;
	}
	
	public void end() {}
	public void update(float deltaTime) {
		
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		// dessin des acteurs dans la fenêtre de jeu
		actor1.draw(window);
		actor2.draw(window);
		
		if(downArrow.isDown()) { // the rock is moving down
			
			actor2.update(0);
		}
		
		if(actor2.getPosition().getLength() <= 0.21f) { // the rock touch the circle
			
			TextGraphics boum = new TextGraphics("BOUM !!!", 0.07f, Color.RED);
			boum.draw(window);
		}
		
		
	}

}
