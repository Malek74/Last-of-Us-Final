package model.characters;

import java.awt.Point;
import java.util.ArrayList;

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

		ArrayList <Point> cells = this.getAdjacentCells(); 

		for(int i=0;i<cells.size();i++){
			if(Game.map[cells.get(i).x][cells.get(i).y] instanceof CharacterCell && ((CharacterCell)Game.map[cells.get(i).x][cells.get(i).y] ).getCharacter()!=null && ((CharacterCell)Game.map[cells.get(i).x][cells.get(i).y] ).getCharacter() instanceof Hero){
				setTarget(((CharacterCell)Game.map[cells.get(i).x][cells.get(i).y] ).getCharacter());
				break;
			}	
		}

		if(!(this.getTarget() instanceof Hero)){
			throw new InvalidTargetException("Target is not a Hero");
		}
		super.attack();
	}

	//calls super method and spawns new zombie
	public void onCharacterDeath(){
		
		super.onCharacterDeath();
		Game.spawnZombie();		
		Game.zombies.remove(this);

	}

}

 
