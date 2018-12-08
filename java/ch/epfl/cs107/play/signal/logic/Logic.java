/*
 *	Author:      Emmanuelle Denove
 *	Date:        8 Dec 2018
 */

package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
	
Logic TRUE = new Logic() { 
	@Override
		public boolean isOn() { 
			return true;
		} 
};

Logic FALSE = new Logic() { 
	@Override
		public boolean isOn() { 
			return false;
		} 
};
	
	
	boolean isOn();
	
	default float getIntensity() {
		if (this.isOn()) {
			return 1.0f;
		}
		else {
			return 0.0f;
		}
	}
	
	@Override
	default float getIntensity(float t) {
		return getIntensity(); 
	}
	

}
