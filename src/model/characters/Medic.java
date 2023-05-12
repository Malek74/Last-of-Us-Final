package model.characters;

import exceptions.*;

public class Medic extends Hero {
	// Heal amount attribute - quiz idea

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);

	}

	public void attack() throws InvalidTargetException ,NotEnoughActionsException{
		super.attack();
		setActionsAvailable(getActionsAvailable()-1);
		
	}

	public void useSpecial() throws NoAvailableResourcesException , InvalidTargetException, NotEnoughActionsException {
		

		if(getTarget()==null){
			super.useSpecial();
			heal(this);
		}
		else{
			if(getTarget() instanceof Zombie){
				throw new InvalidTargetException("Cannot heal a Zombie");
			}
			else{
				if(getAdjacentCells().contains(getTarget().getLocation())){
					super.useSpecial();
					heal(getTarget());
				}
				else{
					throw new InvalidTargetException("Target is not in adjacent cell");
				}
			}
		}

	}

	/* HELPERS */
	private static void heal(Character c) {
		c.setCurrentHp(c.getMaxHp());
	}	
}
