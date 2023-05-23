package views;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.characters.*;
import exceptions.*;


public class Controller{
	static Alert alert;
	static int h;

	public  void move (Gameplay stage ,StackPane grid ){
		stage.setOnKeyPressed(new EventHandler <KeyEvent> (){

			@Override
			public void handle(KeyEvent event){
				switch(event.getCode()){
				
		        case UP :
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.UP);
						checkTrapCell(h);
						stage.updateMap();
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
						
						
					} catch (MovementException e) {
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
						
						e.printStackTrace();
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case DOWN:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.DOWN);
						checkTrapCell(h);
						stage.updateMap();
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case RIGHT:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.RIGHT);
						checkTrapCell(h);
						stage.updateMap();
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case LEFT:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.LEFT);
						stage.updateMap();
						checkTrapCell(h);
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		            
		        case W :
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.UP);
						checkTrapCell(h);
						stage.updateMap();
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case S:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.DOWN);
						stage.updateMap();
						checkTrapCell(h);
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
						
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case D:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.RIGHT);
						stage.updateMap();
						checkTrapCell(h);
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
					} catch (MovementException e) {
						
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}
		            break;
		        case A:
		            try {
		            	h= StartGame.activeHero.getCurrentHp();
						StartGame.activeHero.move(Direction.LEFT);
						stage.updateMap();
						checkTrapCell(h);
						StartGame.herosList.updateHerosList();
						StartGame.inventory.updateInventory(StartGame.activeHero);
						
					} catch (MovementException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					} catch (NotEnoughActionsException e) {
						// TODO Auto-generated catch block
						alert= new Alert(grid,e.getMessage());
						alert.popALert(grid);
					}		            
		            break;
		        default:
		            System.out.println("HELLO");
		            break;
		    
						
				}
				
			}
			
		});
	}
	
	public static void checkTrapCell(int health){
		if(health!=StartGame.activeHero.getCurrentHp()){
			alert= new Alert(StartGame.grid,"YOU MOVED INT A TRAP CELL HEALTH DECREASED BY: " + (StartGame.activeHero.getCurrentHp()-health));
			alert.popALert(StartGame.grid);
		}
	}
	
	public static void updateData(){
		StartGame.gameScreen.updateMap();
		StartGame.herosList.updateHerosList();
		StartGame.inventory.updateInventory(StartGame.activeHero);
	}

}