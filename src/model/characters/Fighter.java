package model.characters;
import exceptions.*;
import engine.*;

public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	
	public void attack() throws InvalidTargetException , NotEnoughActionsException{
		super.attack();

		if(!(super.isSpecialAction())){
			int actions= super.getActionsAvailable()-1;
			super.setActionsAvailable(actions);
		}
		
	}

	




		

	

	
	
	
	

}
