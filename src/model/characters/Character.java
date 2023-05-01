package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;


public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	public static  void main(String[] args) throws GameActionException{

		
		Fighter f = new Fighter("'malek'", 100,40,5);
		Zombie z = new Zombie();
		Zombie z1 = new Zombie();
		f.setLocation(new Point(0, 0));
		z.setLocation(new Point(0, 1));
		z1.setLocation(new Point(1, 0));
		Game.map[0][0]= new CharacterCell(f);
		Game.map[0][1]= new CharacterCell(z);
		Game.map[1][0]= new CharacterCell(z1);
		f.setTarget(z);
		System.out.println(Game.map[0][4]);
		System.out.println("--------------------------------------------");
		System.out.println(Game.map[0][0]);
		System.out.println(Game.map[0][1]);
		System.out.println("--------------------------------------------");
		f.attack();
		System.out.println(Game.map[0][0]);
		System.out.println(Game.map[0][1]);
		System.out.println("--------------------------------------------");
		System.out.println(f.getCurrentHp());
	}

	
	public Character() {
	}
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		
		ArrayList<Cell> adjacentCells=this.getAdjacentCells();
		int targetXLocation=target.getLocation().x;
		int targetYLocation=target.getLocation().y;
		
		//TODO: should i do it or not
		if(!(adjacentCells.contains(Game.map[targetXLocation][targetYLocation]))){
			throw new InvalidTargetException("Target is out of reach");
		}

		target.currentHp-=this.attackDmg;

		//removes attacked target (zombie) if he died
		if(target.currentHp<=0){
			target.onCharacterDeath();
		}
		defend(target);
		
	}

	public void defend(Character c) {
		this.currentHp-=(c.getAttackDmg())/2;
	}

	
	public void onCharacterDeath(){
	
		//gets dead character location
		int x=(int) location.getX();
		int y=(int) location.getY();

		//removes dead character from map
		Game.map[x][y]=null;
		} 
	
	/*HELPER METHODS */

	//gets all cells adjacent to character's location
	public   ArrayList<Cell> getAdjacentCells(){
		ArrayList<Cell> adjacentCells= new ArrayList<Cell>();
		int x=(int) location.getX();
		int y= (int) location.getY();

		try {
			adjacentCells.add(Game.map[x-1][y+1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}

		try {
			adjacentCells.add(Game.map[x][y+1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			adjacentCells.add(Game.map[x+1][y+1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			adjacentCells.add(Game.map[x+1][y]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			adjacentCells.add(Game.map[x+1][y-1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}

		try {
			adjacentCells.add(Game.map[x][y-1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			adjacentCells.add(Game.map[x-1][y-1]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			adjacentCells.add(Game.map[x-1][y]);
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
			return adjacentCells;
	}

	//sets visbility of all map
	private static void setMapVisbility(boolean visbility){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				Game.map[i][j].setVisible(visbility);
			}
		}
	}

	//sets visbility of selected list of cells (not all grid)
	private static void setMapVisbility(boolean visbility,ArrayList<Cell>cells){
		
		for(int i=0;i<cells.size();i++){
			cells.get(i).setVisible(visbility);
		}
	}
}
