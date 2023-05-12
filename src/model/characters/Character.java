package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import model.world.Cell;
import model.world.CharacterCell;
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
		if(currentHp < 0) {
			this.currentHp = 0;
			
		}
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;

		if(this.currentHp<=0){
			onCharacterDeath();
		}
	}

	
	public int getAttackDmg() {
		return attackDmg;
	}

	//method allows character to attack his target 
	public void attack() throws InvalidTargetException,NotEnoughActionsException{
		
		ArrayList<Point> adjacentCells=getAdjacentCells();
				
		//handles exception that target is not in adjacent cells
		System.out.println(target.getLocation() +"men hena " + target.getClass() );
		System.out.println(getAdjacentCells());
		if(!(contains(adjacentCells, target.getLocation()))){
			throw new InvalidTargetException("Target is out of reach");
		}
		target.setCurrentHp(target.getCurrentHp()-getAttackDmg());
		target.defend(this);
	}

	//method allows attacked character to defend himcself
	//TODO: character parameter shouldn't be target (DONE)
	public void defend(Character c) {
		c.setCurrentHp(c.getCurrentHp()-(getAttackDmg()/2));
	}

	//method that handles map and other variables when character dies
	public void onCharacterDeath(){
	
		//gets dead character location
		int x=(int) location.getX();
		int y=(int) location.getY();

		//removes dead character from map
		Game.map[x][y]=new CharacterCell(null);
		} 
	
	/*HELPER METHODS */

	//gets all cells adjacent to character's location
	public ArrayList<Point> getAdjacentCells(){
		ArrayList<Point> adjacentCells= new ArrayList<Point>();
		int hor=(int) location.getX();
		int ver= (int) location.getY();

		try {
			Game.map[ver-1][hor+1]=Game.map[ver-1][hor+1];
			adjacentCells.add(new Point(hor+1, ver-1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver][hor+1]=Game.map[ver][hor+1];
			adjacentCells.add(new Point(hor+1, ver));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver+1][hor+1]=Game.map[ver+1][hor+1];
			adjacentCells.add(new Point(hor+1, ver+1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver+1][hor]=Game.map[ver+1][hor];
			adjacentCells.add(new Point(hor, ver+1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver+1][hor-1]=Game.map[ver+1][hor-1];
			adjacentCells.add(new Point(hor-1, ver+1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver][hor-1]=Game.map[ver][hor-1];
			adjacentCells.add(new Point(hor-1, ver));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver-1][hor-1]=Game.map[ver-1][hor-1];
			adjacentCells.add(new Point(hor-1, ver-1));
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Game.map[ver-1][hor]=Game.map[ver-1][hor];
			adjacentCells.add(new Point(hor, ver-1));
		} catch (Exception e) {
			// TODO: handle exception
		}


			return adjacentCells;
	}

	//sets visbility of all map whether to visible(true) or invisible (false)
	public static void setMapVisbility(boolean visbility){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				Game.map[i][j].setVisible(visbility);
			}
		}
	}

	//sets visbility of selected list of cells (not all map)
	//TODO:7ases feeh 7aga 8alat fel setting beta3 co-ordinates
	public static void setMapVisbility(boolean visbility,ArrayList<Point>cells){
		
		for(int i=0;i<cells.size();i++){
			Game.map[(int)cells.get(i).getX()][(int)cells.get(i).getY()].setVisible(visbility);
		}
	}
	public boolean contains(ArrayList<Point> cells,Point location){
		for (Point point : cells) {
			if(point.x==location.x && point.y==location.y){
				return true;
			}
		}
		return false;
	}
}
