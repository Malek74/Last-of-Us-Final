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
		this.currentHp=maxHp;
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

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp>maxHp){
			this.currentHp=maxHp;
		}
		else{
			if(currentHp<0){
				currentHp=0;
			}
			else{
				this.currentHp=currentHp;
			}
		}
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	
}
