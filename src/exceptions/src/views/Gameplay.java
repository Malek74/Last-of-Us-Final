package views;

import engine.Game;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Gameplay extends Scene {

	GridPane map =new GridPane();
	GridCell[][] gridCells= new GridCell [15][15];
	

	public Gameplay(Parent root) {
		super(root);
		
		
		// TODO Auto-generated constructor stub
	}
	
	public void updateMap(){
		
		int row=14;
		int col=14;
		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				gridCells[i][j]= new GridCell();
				
			}
		}		
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				
				if(Game.map[i][j] instanceof CharacterCell){
					if(( (CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero){
						if(Game.map[i][j].isVisible()){
							gridCells[i][j].setImage("explorer.png");
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setImage("blurry.jpeg");
							map.add(gridCells[i][j],j,row-i);
						}
					}
					if(( (CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie){
						if(Game.map[i][j].isVisible()){
							gridCells[i][j].setImage("zombie.png");
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setImage("blurry.jpeg");
							map.add(gridCells[i][j],j,row-i);
						}
					}
					if(((CharacterCell) Game.map[i][j]).getCharacter() == null){
						if(Game.map[i][j].isVisible()){
							gridCells[i][j].setImage("grass.jpeg");
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setImage("blurry.jpeg");
							map.add(gridCells[i][j],j,row-i);
						}
				}
				}
					
				if(Game.map[i][j] instanceof CollectibleCell ){
					
				if(((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Vaccine){
					if(Game.map[i][j].isVisible()){
						gridCells[i][j].setImage("vaccine.png");
						map.add(gridCells[i][j],j,row-i);
					}
					else{
						gridCells[i][j].setImage("blurry.jpeg");
						map.add(gridCells[i][j],j,row-i);
					}
				}
				
				if(((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Supply){
					if(Game.map[i][j].isVisible()){
						gridCells[i][j].setFill(Color.RED);
						map.add(gridCells[i][j],j,row-i);
					}
					else{
						gridCells[i][j].setImage("blurry.jpeg");
						map.add(gridCells[i][j],j,row-i);
					}
				}
				
					
				}
				
				if(Game.map[i][j] instanceof TrapCell){
					if(Game.map[i][j].isVisible()){
						gridCells[i][j].setImage("grass.jpeg");
						map.add(gridCells[i][j],j,row-i);
					}
					else{
						gridCells[i][j].setFill(Color.BLACK);
						map.add(gridCells[i][j],j,row-i);
					}
				}
			
		}		
		
		}
		}

	
}
	
	
	 



	
