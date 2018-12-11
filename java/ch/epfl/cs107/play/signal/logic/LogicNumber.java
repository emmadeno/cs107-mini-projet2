package ch.epfl.cs107.play.signal.logic;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.enigme.actor.Door;

public class LogicNumber extends LogicSignal{

	private float nb;
	private List<Logic> e = new LinkedList<>();
	
	public LogicNumber(float nb, List<Logic> ensemble) {
		this.nb = nb;
		e = ensemble;
	}
	
	
	@Override
	public boolean isOn() {
		
		if(e.size()>12 || nb<0 || nb> Math.pow(2, e.size())) {
		
			return false;
		}
			if(signalNumber() == nb) {
				return true;
			}
		return false;
	}
	
	/**
	 * signalNumber method : calcule le nombre binaire associé à la combinaison de signaux
	 * 						et le transforme en float
	 * @return(float) : nombre calculé par la combinaison de signaux
	 */
	private float signalNumber() {
		
		int[] valeur = new int [e.size()];
		int signalNumber = 0;
		
		//on regarde si le signal est allumé et si oui on associe la valeur num 1
		for(int i = 0; i<e.size(); ++i) {
			
			if(e.get(i).isOn()) {
				valeur[i] = 1;
			}
			else { valeur[i] = 0; }
		}
		
		
		//on fait la somme pour calculer le nombre signal number
		for(int j = 0; j<valeur.length; ++j) {
			
			signalNumber+= (Math.pow(2, j))*valeur[j];
		}
		
		
		return (float) signalNumber;
		
	}
	
	

}
