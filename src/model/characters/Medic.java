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

	public void useSpecial() throws NoAvailableResourcesException {
		super.useSpecial();
		heal(this);

	}

	/* HELPERS */
	private static void heal(Character c) {
		c.setCurrentHp(c.getMaxHp());
	}

	
}
