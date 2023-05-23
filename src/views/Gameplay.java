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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Gameplay extends Scene {

	GridPane map =new GridPane();
	static GridCell[][] gridCells= new GridCell [15][15];
	ImageView imageView= new ImageView();
	Image grass =new Image ("grass.jpeg");
	Image fighter = new Image("fighter.png");
	Image medic = new Image("medic.jpg");
	Image explorer = new Image("explorer.png");
	Image zombie = new Image("zombie.png");
	Image vaccine = new Image("vaccine.png");
	ImagePattern pattern;

	
	
	

	

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
				int x=i;
				int y=j;
				if(Game.map[i][j] instanceof CharacterCell){
					
					
					
					if(( (CharacterCell) Game.map[i][j]).getCharacter() instanceof Hero){
						
							gridCells[i][j].setOnMouseClicked(event ->{
							
							
							
								StartGame.activeHero.setTarget(( (CharacterCell) Game.map[x][y]).getCharacter());
							
							
						});
							
						if(Game.map[i][j].isVisible()){
							imageView.setFitHeight(65);
					        imageView.setFitWidth(65);
					        imageView.setImage(explorer);
					        pattern=new ImagePattern(imageView.getImage());
					        
							gridCells[i][j].setFill(pattern);
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setFill(Color.BLACK);
							map.add(gridCells[i][j],j,row-i);
						}
					}
					if(( (CharacterCell) Game.map[i][j]).getCharacter() instanceof Zombie){
						
						gridCells[i][j].setOnMouseClicked(event ->{
							StartGame.activeHero.setTarget(( (CharacterCell) Game.map[x][y]).getCharacter());
							
							
							
						});
						if(Game.map[i][j].isVisible()){

							imageView.setFitHeight(65);
					        imageView.setFitWidth(65);
					        imageView.setImage(zombie);
					        pattern=new ImagePattern(imageView.getImage());
					        
							gridCells[i][j].setFill(pattern);
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setFill(Color.BLACK);
							map.add(gridCells[i][j],j,row-i);
						}
					}
					if(((CharacterCell) Game.map[i][j]).getCharacter() == null){
						if(Game.map[i][j].isVisible()){
							imageView.setFitHeight(65);
					        imageView.setFitWidth(65);
					        imageView.setImage(grass);
					        pattern=new ImagePattern(imageView.getImage());
					        
							gridCells[i][j].setFill(pattern);
							map.add(gridCells[i][j],j,row-i);
						}
						else{
							gridCells[i][j].setFill(Color.BLACK);
							map.add(gridCells[i][j],j,row-i);
						}
				}
				}
					
				if(Game.map[i][j] instanceof CollectibleCell ){
					
				if(((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Vaccine){
					if(Game.map[i][j].isVisible()){
						imageView.setFitHeight(65);
				        imageView.setFitWidth(65);
				        imageView.setImage(vaccine);
				        pattern=new ImagePattern(imageView.getImage());
				        
						gridCells[i][j].setFill(pattern);
						map.add(gridCells[i][j],j,row-i);
					}
					else{
						gridCells[i][j].setFill(Color.BLACK);
						map.add(gridCells[i][j],j,row-i);
					}
				}
				
				if(((CollectibleCell)Game.map[i][j]).getCollectible() instanceof Supply){
					if(Game.map[i][j].isVisible()){
						gridCells[i][j].setFill(Color.RED);
						map.add(gridCells[i][j],j,row-i);
					}
					else{
						gridCells[i][j].setFill(Color.BLACK);
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
	
	
	 



	
