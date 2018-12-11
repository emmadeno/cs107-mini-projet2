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
		
		if(one!=null && two!= null) { // si les signaux ne sont pas nul
			if(one.isOn() || two.isOn()) { // si l'un ou l'autre ou les deux sont On return true
				return  true;
			}
		}
		
		return false;
	}
	
	

}
