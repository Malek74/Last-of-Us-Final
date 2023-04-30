package model.characters;
import exceptions.*;

public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	
	public void attack() throws InvalidTargetException , NotEnoughActionsException{
		super.attack();

		//handles if special is used or not 
		if(!(super.isSpecialAction())){
			int actions= super.getActionsAvailable()-1;
			super.setActionsAvailable(actions);
		}
		
	}

	




		

	

	
	
	
	

}
