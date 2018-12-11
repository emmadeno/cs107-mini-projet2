package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

public class MultipleAnd extends LogicSignal{
	
	private List<Logic> signaux = new LinkedList<>();

	public MultipleAnd(List<Logic> s) {
		
		signaux = s;
		
	}

	@Override
	public boolean isOn() {
		
		if(testAnd()) {
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * testAnd method : itère sur la liste de signaux 
	 * @return(boolean) : return false si un des signaux n'est pas activé
	 */
	private boolean testAnd() {
		
		for(int i = 0; i<signaux.size(); ++i) {
			if(!signaux.get(i).isOn()) {
				return false;
			}
		}
		return true;
	}
	
	

}
