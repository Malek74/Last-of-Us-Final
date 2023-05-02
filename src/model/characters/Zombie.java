package model.characters;
import java.awt.Point;

import engine.Game;
import exceptions.*;
import model.world.CharacterCell;


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

	//calls super method and spawns new zombie
	public void onCharacterDeath(){
		super.onCharacterDeath();
		Game.spawnZombie();

		Game.zombies.remove(this);

	}

}


