package model.characters;
import exceptions.*;


public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}

	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		if(!(this.getTarget() instanceof Hero)){
			throw new InvalidTargetException("Target is not a Hero");
		}
	} 

}


