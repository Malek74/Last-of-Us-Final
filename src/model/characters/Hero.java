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
				throw new NotEnoughActionsException("No Enough Actions");
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
			movedToTrap(cell,newLocation) ;
				

			//checks and handles if new cell contains collectible
			movedToCollectible(cell,newLocation);
			
			
			//checks and handles if new cell contains character 
			if(movedToCharacter(cell)){
				setLocation(newLocation);
				Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
			}
			else{
				if(!(cell instanceof TrapCell || cell instanceof CollectibleCell)){
				throw new MovementException("Cannot move to a cell occupied by another character");
			}
		}

			
			//empties old cell
			Game.map[x][y]= new CharacterCell(null);



			//TODO:how to know cell is empty or when can hero move to cell (DONE)
			//TODO: cannot move in a cell occupied by character (DONE)
			//TODO: if user moves to trap he gets damage and trap dissapears (DONE)
			//TODO: if user moves to supply cell he picks it up 
			
			//sets new cell's adjacent cells to be visible 
			ArrayList<Point> cells=this.getAdjacentCells();
			super.setMapVisbility(true, cells);
			

			setActionsAvailable(getActionsAvailable()-1);
			Game.map[(int)newLocation.getX()][(int)newLocation.getY()].setVisible(true);

		}
		
		public void useSpecial() throws NoAvailableResourcesException{

			if(supplyInventory.size()==0){
				throw new NoAvailableResourcesException("No supplies available to perform action");
			}

			//TODO:handle if special action is already activated  (Not Handled)
			supplyInventory.remove(supplyInventory.size()-1);
			this.setSpecialAction(true);
			//TODO:use Collectibles use
		}

		public void cure() throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{
			//handles exception that hero has no vaccines
			if(vaccineInventory.size()==0){
				throw new NoAvailableResourcesException("No Vaccines available");
			}

			//handles exception that target is not a Zombie
			if(!(this.getTarget() instanceof Zombie)){
				throw new InvalidTargetException("Target is not a Zombie");
			}

			if(getActionsAvailable()<=0){
				throw new NotEnoughActionsException("Not Enough action pts");
			}

			ArrayList<Point> adjacentCells = this.getAdjacentCells();

			
		
			//gets co-ordinates of target
			int x= (int) getTarget().getLocation().getX();
			int y= (int) getTarget().getLocation().getY();

			//check that target is within reach
			if(!(adjacentCells.contains(getTarget().getLocation()))){
				throw new InvalidTargetException("Target is out of reach");
			}

			//replace zombie with a new hero and remove hero from zombies list
			Game.zombies.remove(getTarget());
			Game.map[x][y]= new CharacterCell(Game.availableHeroes.get(0));
			Game.heroes.add(Game.availableHeroes.remove(0));			

			//TODO:use collectible interface
		}
		

		/*HELPERS */

		//checks if cell Hero moved into is Trap if so damage Hero
		public void movedToTrap(Cell cell,Point newLocation){
			if(cell instanceof TrapCell){
				
				//sets new cell
				Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
				setLocation(newLocation);

				TrapCell trap = (TrapCell) cell;
				setCurrentHp(getCurrentHp()-trap.getTrapDamage());

				
			}
			
		}

		public void movedToCollectible(Cell cell , Point newLocation){
			if(cell instanceof CollectibleCell){
				CollectibleCell collectible= (CollectibleCell) cell;
				//TODO: use Collectible pickup
				//sets new cell 
				Game.map[(int) newLocation.getX()][(int) newLocation.getY()]= new CharacterCell(this);
				setLocation(newLocation);
			}

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
			return new Point(newY, newX);
		}
}

