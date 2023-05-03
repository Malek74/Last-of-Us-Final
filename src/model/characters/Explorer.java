package model.characters;
import exceptions.*;


public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	
	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
		setActionsAvailable(getActionsAvailable()-1);
		
		}

		public void useSpecial() throws NoAvailableResourcesException{
			super.useSpecial();
			Character.setMapVisbility(true);
			
		}
	


	
}
