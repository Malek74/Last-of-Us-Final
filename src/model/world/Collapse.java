package model.world;

public class Collapse extends Disaster {
	private int damagedCells = 3;
	
	public int getDamagedCells() {
		return damagedCells;
	}

	public Collapse () {
		super("Collapse ");
	}
}
	
