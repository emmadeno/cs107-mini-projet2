/*
 *	Author:      Emmanuelle Denove
 *	Date:        8 Dec 2018
 */

package ch.epfl.cs107.play.signal.logic;

public abstract class LogicSignal implements Logic {
	
	@Override
	public final float getIntensity() {
		if (this.isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}
	
	@Override
	public final float getIntensity(float t) {
		return getIntensity(); 
	}

}
