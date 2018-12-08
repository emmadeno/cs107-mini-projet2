package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal {

	private Logic notS;
	
	public Not(Logic s) {
		
		notS = s;
		
	}
	
	@Override
	public boolean isOn() {
		
		if(notS!= null && !notS.isOn()) {
			
			return true;
		}
		else {
		return false;
		}
	}

}
