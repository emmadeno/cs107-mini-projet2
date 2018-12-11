/*
 *	Author:      Emmanuelle Denove
 *	Date:        11 Dec 2018
 */

package ch.epfl.cs107.play.signal.logic;

public interface LifeLogic extends Logic{
	
	@Override
	default public boolean isOn() {
		
		
		return false;
		
	}

}
