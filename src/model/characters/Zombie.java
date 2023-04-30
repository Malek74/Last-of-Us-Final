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
			//QUESTION: Zombie never used the set target method should there be a method where the zombie targets the Hero in its adjacent cell? If so should it target all heros in adjacent cells or just attack once?
			throw new InvalidTargetException("Target is not a Hero");
		}
	}

	//calls super method and spawns new zombie
	public void onCharacterDeath(){
		super.onCharacterDeath();
		Point location = Game.randomPoint();
		Game.map[(int) location.getX()][(int)location.getY()]= new CharacterCell(new Zombie());

	}

}


