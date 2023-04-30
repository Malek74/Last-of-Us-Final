package model.characters;
import exceptions.*;
import engine.*;

public class Explorer extends Hero {
	

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;	
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException{
		super.attack();
		int actions= super.getActionsAvailable()-1;
		super.setActionsAvailable(actions);
		
		}

		public void useSpecial() throws NoAvailableResourcesException{
			super.useSpecial();
			setMapVisbility(true);
			
		}
	

		
	/*HELPERS */
	//sets visbility of all map
	private static void setMapVisbility(boolean visbility){
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				Game.map[i][j].setVisible(visbility);
			}
		}
	}

	
}
