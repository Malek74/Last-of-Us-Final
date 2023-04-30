package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import engine.*;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}

		public boolean isSpecialAction() {
			return specialAction;
		}

		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}

		public int getActionsAvailable() {
			return actionsAvailable;
		}

		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}

		public int getMaxActions() {
			return maxActions;
		}

		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}

		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}

		public void attack() throws InvalidTargetException,NotEnoughActionsException{
			if(!(this.getTarget() instanceof Zombie)){
				throw new InvalidTargetException("Target is not a Zombie");
			}
			
			
			if(actionsAvailable==0){
				throw new NotEnoughActionsException("No Enough Actions");
			}
			
			super.attack();


		}

		//calls super method and removes Hero from Heroes' pool
		public void onCharacterDeath(){
			super.onCharacterDeath();
			Game.heroes.remove(this);
		}

		public void move(Direction d) throws MovementException , NotEnoughActionsException{
			if(actionsAvailable==0){
				throw new NotEnoughActionsException("No Actions left");
			}
			
			//gets user x & y co-ordinates
			int x=(int) this.getLocation().x;
			int y=(int) this.getLocation().y;
			int newX=x;
			int newY=y;
			
			//handles the input from user and upgrades x & y co-ordinates if input movement is valid
			switch (d){
				case UP: if(y==14){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newY++;
				}
				break;
				case DOWN: if(y==0){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newY--;
				}
				break;
				case LEFT:if(x==0){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newX--;
				}
				break;
				case RIGHT:if(x==14){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newX++;
				}
				break;
			}

			
			Game.map[x][y]=null;

			//TODO:how to know cell is empty or when can hero move to cell
			
			//sets new user location
			this.setLocation(new Point(newX,newY));
			
			ArrayList<Cell> cells=this.getAdjacentCells();
			setMapVisbility(true, cells);
		}
		
		public void useSpecial() throws NoAvailableResourcesException{

			if(supplyInventory.size()==0){
				throw new NoAvailableResourcesException("No supplies available to perform action");
			}

			//TODO:handle if special action is already activated 
			supplyInventory.remove(supplyInventory.size()-1);
			this.setSpecialAction(true);
		}

		public void cure() throws NoAvailableResourcesException, InvalidTargetException{
			if(vaccineInventory.size()==0){
				throw new NoAvailableResourcesException("No Vaccines available");
			}

			if(!(this.getTarget() instanceof Zombie)){
				throw new InvalidTargetException("Target is not a Zombie");
			}
			ArrayList<Cell> cells = this.ge
			

			//TODO:know if there is a zombie to cure in adjacent cells and do we have to set target
		}
		


		/*HELPERS */
		
		//sets visbility of selected list of cells (not all grid)
		private static void setMapVisbility(boolean visbility,ArrayList<Cell>cells){
		
		for(int i=0;i<cells.size();i++){
			cells.get(i).setVisible(visbility);
		}
}
}
