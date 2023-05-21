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

	public  void move (Scene stage ,StackPane grid ){
		stage.setOnKeyPressed(new EventHandler <KeyEvent> (){

			@Override
			public void handle(KeyEvent event){
				switch(event.getCode()){
				
		        case UP :
		            try {
						StartGame.activeHero.move(Direction.UP);
						
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
						StartGame.activeHero.move(Direction.DOWN);
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
						StartGame.activeHero.move(Direction.RIGHT);
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
						StartGame.activeHero.move(Direction.LEFT);
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
						StartGame.activeHero.move(Direction.UP);
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
						StartGame.activeHero.move(Direction.DOWN);
						
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
						StartGame.activeHero.move(Direction.RIGHT);
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
						StartGame.activeHero.move(Direction.LEFT);
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
	

}