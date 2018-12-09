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
	
	private boolean testAnd() {
		
		int nbTests = 0;
		int multipleAnd = 0;
		for(int i = 0; i<signaux.size(); ++i) {
			
			nbTests+=i;
		}
		
		for(int k = 0; k<signaux.size()-1; ++k) {
			for(int j = k+1; j<signaux.size(); ++j) {
				
				if(new And(signaux.get(k),signaux.get(j)).isOn()) {
					
					++multipleAnd;
				}
				
			}
			
		}
		
		if(nbTests == multipleAnd) {
			return true;
		}
		else {return false;}
	}
	
	

}
