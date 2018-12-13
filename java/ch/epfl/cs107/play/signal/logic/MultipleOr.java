/*
 *	Author:      Emmanuelle Denove
 *	Date:        12 Dec 2018
 */

package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class MultipleOr extends LogicSignal{
	
	private List<Logic> signaux = new LinkedList<>();

	public MultipleOr(List<Logic> s) {
		
		signaux = s;
		
	}

	@Override
	public boolean isOn() {
		
		if(testOr()) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * testAnd method : itère sur la liste de signaux 
	 * @return(boolean) : return false si un des signaux n'est pas activé
	 */
	private boolean testOr() {
		
		for(int i = 0; i<signaux.size(); ++i) {
			if(signaux.get(i).isOn()) {
				return true;
			}
		}
		return false;
	}

}
