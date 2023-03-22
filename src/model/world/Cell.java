package model.world;

public abstract class Cell {
	private boolean isVisible;
	
	public Cell() {
		
	}
	public Cell(boolean visible){
		this.isVisible=visible;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
