package model.characters;
import java.awt.Point;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;
	
	//constructor
	public Character(String name,int maxHp,int attackDmg) {
		this.name=name;
		this.maxHp=maxHp;
		this.attackDmg=attackDmg;
	}

	//getters
	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
}
