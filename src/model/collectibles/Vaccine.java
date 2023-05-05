package model.collectibles;

import model.characters.Hero;



public class Vaccine implements Collectible {

	public Vaccine() {
		
	}
	public void pickuUp(Hero h){
		h.getVaccineInventory().add(this);

	}
	public void use(Hero h){
		h.getVaccineInventory().remove(0);

	}


}
