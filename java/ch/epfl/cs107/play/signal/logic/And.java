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
		
		if(one != null && two != null) { // si les deux signaux à tester ne sont pas nul 
			if(one.isOn() && two.isOn()) {// si ils sont TOUT LES DEUX allumés
				return true;
			}	
		}
		
		return false;
		
	}
	

}
