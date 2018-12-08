package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal{

	private Logic one;
	private Logic two;
	
	public Or(Logic one, Logic two) {
		
		this.one = one;
		this.two = two;
		
	}
	
	@Override
	public boolean isOn() {
		
		if(one!=null && two!= null) {
			if(one.isOn() || two.isOn()) {
				
				return  true;
			}
		}
		
		return false;
	}
	
	

}
