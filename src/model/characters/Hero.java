package model.characters;

import java.util.ArrayList;

import model.collectibles.Supply;
import model.collectibles.Vaccine;

public abstract class Hero extends Character{
	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	
	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name,maxHp,attackDmg);
		this.maxActions=maxActions;
		actionsAvailable = maxActions;
		vaccineInventory= new ArrayList<>();
		supplyInventory= new ArrayList<>();
	}
	
//getters
	public int getActionsAvailable() {
		return actionsAvailable;
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

	public boolean isSpecialAction() {
		return specialAction;
	}

// setters
	public void setActionsAvailable(int numOfActions) {
		actionsAvailable = numOfActions;
	}

	public void setSpecialAction(boolean SpecialActionFlag){
		specialAction = SpecialActionFlag;
	}


}
