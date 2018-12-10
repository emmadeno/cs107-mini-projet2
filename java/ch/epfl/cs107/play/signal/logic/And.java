package ch.epfl.cs107.play.signal.logic;

public class And extends LogicSignal {

	private Logic one;
	private Logic two;
	
	public And (Logic one, Logic two) {
		
		this.one = one;
		this.two = two;
		
	}
	
	@Override
	public boolean isOn() {
		
		if(one != null && two != null) {
			if(one.isOn() && two.isOn()) {
				return true;
			}	
		}
		
		return false;
		
	}
	

}
