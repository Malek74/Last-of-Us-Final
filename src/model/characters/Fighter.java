package model.characters;
import exceptions.*;

public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	
	public void attack() throws InvalidTargetException , NotEnoughActionsException{
		super.attack();
		//if specialAction is used don't decrement actions
		if(!(super.isSpecialAction())){
			setActionsAvailable(getActionsAvailable()-1);
		}
		
	}

	




		

	

	
	
	
	

}
