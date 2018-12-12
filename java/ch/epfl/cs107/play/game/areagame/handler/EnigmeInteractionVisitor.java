/*
 *	Author:      Emmanuelle Denove
 *	Date:        4 Dec 2018
 */

package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Pickup;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Ressources;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.Torch;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	
	/**
	 * Simulates an interaction between Interactors and Objects of Pickup in enigme
	 * @param pickup(Pickup), not null (key, apple)
	 */
	default void interactWith(Pickup pickup) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction between Interactors and Objects of Swichable in enigme
	 * @param switchable(Switchable), not null (pressure switch, lever, torch)
	 */
	default void interactWith(Switchable switchable) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction between Interactors and pressurePlate in enigme
	 * @param pressurePlate(PressurePlate), not null 
	 */
	default void interactWith(PressurePlate pressurePlate) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction between Interactors and cells in enigme
	 * @param cell(EnigmeCell), not null
	 */
	default void interactWith(EnigmeBehavior.EnigmeCell cell) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction between Interactors and Doors in enigme
	 * @param door(Door), not null 
	 */
	default void interactWith(Door door) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction between Interactors and player in enigme
	 * @param player(EnigmePlayer), not null 
	 */
	default void interactWith(EnigmePlayer player) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction beteween Interactors and signalRock in enigme
	 * @param signalRock(SignalRock)
	 */
	default void interactWith(SignalRock signalRock) {
		//by default this interaction is empty
	}
	
	/**
	 * Simulates an interaction beteween Interactors and Ressources in enigme
	 * @param ressource(Ressources)
	 */
	default void interactWith(Ressources ressource) {
		//by default this interaction is empty
	}

}
