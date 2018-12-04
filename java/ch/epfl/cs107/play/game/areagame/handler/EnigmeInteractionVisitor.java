/*
 *	Author:      Emmanuelle Denove
 *	Date:        4 Dec 2018
 */

package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	
	default void interactWith(Apple apple) {
		//by default this interaction is empty
	}
	
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		//by default this interaction is empty
	}
	
	default void interactWith(Door door) {
		//by default this interaction is empty
	}
	
	default void interactWith(EnigmePlayer player) {
		//by default this interaction is empty
	}

}
