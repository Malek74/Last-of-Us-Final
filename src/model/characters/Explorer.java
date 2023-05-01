package model.characters;
import exceptions.*;
import engine.*;

public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
		int actions= super.getActionsAvailable()-1;
		super.setActionsAvailable(actions);
		
		}

		public void useSpecial() throws NoAvailableResourcesException{
			super.useSpecial();
			Character.setMapVisbility(true);
			
		}
	


	
}
