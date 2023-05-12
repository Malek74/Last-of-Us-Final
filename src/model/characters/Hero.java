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
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.*;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		public static void main(String[] args) throws MovementException, NotEnoughActionsException {
			Fighter f = new Fighter("malek", 100, 10, 10);
			Game.startGame(f);
			Game.map[1][0]= new CollectibleCell(new Vaccine());
			f.move(Direction.RIGHT);

			
			System.out.println( Game.map[0][1].isVisible());
		}
		
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
			
			//handles exception that target is not a Zombie
			if(!(this.getTarget() instanceof Zombie)){
				throw new InvalidTargetException("Target is not a Zombie");
			}
			
			//handles exception that Hero doesn't have any actions left
			if(actionsAvailable==0){
				throw new NotEnoughActionsException("No Enough Actions to attack");
			}
			
			super.attack();


		}

		//calls super (Character) method and removes Hero from Heroes' pool
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
			
			//handles the input from user and gets new x & y co-ordinates if input movement is valid
			Point newLocation= validMove(d, x, y);

			//gets new cell user wants to move to
			Cell cell = Game.map[(int) newLocation.getX()][(int) newLocation.getY()];	
			
			//checks and handles if new cell is a trap cell
			if(!movedToTrap(cell,newLocation)){
				//checks and handles if new cell contains collectible
				if(!movedToCollectible(cell,newLocation)){
					//checks and handles if new cell contains character 
					if(movedToCharacter(cell)){
						if(getCurrentHp()>0){
							setLocation(newLocation);
							Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
						}
					}
					else{
						if(cell instanceof CharacterCell){
						throw new MovementException("Cannot move to a cell occupied by another character");
					}
				}
				}
			}
			
			
			
			//empties old cell
			Game.map[x][y]= new CharacterCell(null);

			
			//sets new cell's adjacent cells to be visible 
			if(getCurrentHp()>0){
				ArrayList<Point> cells=this.getAdjacentCells();
				super.setMapVisbility(true, cells);
				setActionsAvailable(getActionsAvailable()-1);
				Game.map[(int)newLocation.getX()][(int)newLocation.getY()].setVisible(true);
			}

		}
		
		public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{


			if(supplyInventory.size()==0){
				throw new NoAvailableResourcesException("No supplies available to perform action");
			}


			supplyInventory.get(supplyInventory.size()-1).use(this);
			this.setSpecialAction(true);

			//TODO:use Collectibles use
		}

		public void cure() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{
			if(actionsAvailable<=0){
				throw new NotEnoughActionsException("Hero doesn't have action points left");
			}

			if(vaccineInventory.size()<=0){
				throw new NoAvailableResourcesException("No Vaccines in Inventory");
			}
			
			if(!(getTarget() instanceof Zombie)){
				throw new InvalidTargetException("Target is not a Zombie");   
			}
			
			if(!(getAdjacentCells().contains(getTarget().getLocation()))){
				throw new InvalidTargetException("Zombie is not adjacent");
			}
			setActionsAvailable(getActionsAvailable()-1);
			vaccineInventory.get(vaccineInventory.size()-1).use(this);

		
		}
		

		/*HELPERS */

		//checks if cell Hero moved into is Trap if so damage Hero
		public boolean movedToTrap(Cell cell,Point newLocation){
			
			if(cell instanceof TrapCell){
				TrapCell trap = (TrapCell) cell;
				setCurrentHp(getCurrentHp()-trap.getTrapDamage());
				Game.map[newLocation.x][newLocation.y]=new CharacterCell(null);

				//sets new cell
				if(getCurrentHp()>0){
					Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
					setLocation(newLocation);
				}
				
				return true;
				
			}
			return false;
			
		}

		public boolean movedToCollectible(Cell cell , Point newLocation){

			if(cell instanceof CollectibleCell){
				CollectibleCell collectible= (CollectibleCell) cell;
				collectible.getCollectible().pickUp(this);
				Game.map[(int) newLocation.getX()][(int) newLocation.getY()]=new CharacterCell(null);

				//sets new cell 
				if(getCurrentHp()>0){
					Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
					setLocation(newLocation);
				}
				return true;
			}
			return false;

		}

		//checks if cell hero moves to has character 
		public boolean movedToCharacter(Cell cell){
			if(cell instanceof CharacterCell){
				CharacterCell characterCell= (CharacterCell) cell;
				if(characterCell.getCharacter()!=null){
					return false;
				}
				return true;
			}
			return false;
		}

		//checks that cell Hero wants to move to is within game map borders
		public Point validMove(Direction d , int x , int y) throws MovementException{
			
			int newX=x;
			int newY=y;
			switch (d){
				case UP: if(x==14){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newX++;
				}
				break;
				case DOWN: if(x==0){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newX--;
				}
				break;
				case LEFT:if(y==0){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newY--;
				}
				break;
				case RIGHT:if(y==14){
					throw new MovementException("Invalid Movement , Cannot move out of Border");
				}
				else{
					newY++;
				}
				break;
			}
			return new Point(newX, newY);
		}
}

