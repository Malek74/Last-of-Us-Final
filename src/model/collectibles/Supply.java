package model.collectibles;

import model.characters.Hero;

public class Supply implements Collectible  {
	
	public Supply() {
		
	}
	
	public void pickUp(Hero h){
		
		h.getSupplyInventory().add(this);

	}
	public void use(Hero h){
		
		h.getSupplyInventory().remove(0);
		// should we add here no available resources exception or the item is already in our inventory?
	}

	
	
	
		
		

}
