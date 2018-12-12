/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.enigme.PlayerLives;

public interface Ressources {

	/**
	 * actOnLives method : détermine comment la ressource agit sur l'acteur
	 * @param i : acteur sur lequel la ressource agit
	 */
	public void actOnLives(PlayerLives i);
	
}
