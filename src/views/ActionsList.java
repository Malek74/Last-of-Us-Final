package views;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ActionsList extends HBox{

	Button endTurn = new GameButtons("End Turn");
	Button attack = new GameButtons("Attack");
	Button cure = new GameButtons("Cure");
	Button useSpecial = new GameButtons("Use Special Action");
	Button removeTarget= new GameButtons("Remove Target");
	
	
	public ActionsList(){
		super();
		setButtons();
		setSpacing(10);
		setButtons();
		getChildren().add(attack);
		getChildren().add(cure);
		getChildren().add(useSpecial);
		getChildren().add(endTurn);
		setAlignment(Pos.CENTER);
		setWidth(400);
		
	}
	
	private void setButtons(){
		
		//sets attack button
//		attack.setPrefSize(200, 100);  // button width = 200 and height = 100
//		Image attackImage = new Image("attack.jpg");
//		ImageView imageView = new ImageView(attackImage);
//		attack.setGraphic(imageView);
//		imageView.setFitWidth(50);
//		imageView.setFitHeight(50);
		attack.setOnMouseClicked(event ->{
			try {
				
				if(StartGame.activeHero.getTarget()!=null&&StartGame.activeHero.getTarget().getCurrentHp()<=0){
					StartGame.activeHero.setTarget(null);
				}
				StartGame.activeHero.attack();
				
				
				
				Controller.updateData();
			} catch (Exception e) {
				Alert alert= new Alert(StartGame.grid,e.getMessage());
				alert.popALert();
				
				e.printStackTrace();
			}
		});
		
		//sets cure button 
//		
//		Image cureImage = new Image("Cure.jpg");
//		ImageView imageView1 = new ImageView(cureImage);
//		cure.setGraphic(imageView1);
//		imageView1.setFitWidth(50);
//		imageView1.setFitHeight(50);
		cure.setOnMouseClicked(event ->{
			try {
				StartGame.activeHero.cure();
				Controller.updateData();
			} catch (Exception e) {
				Alert alert= new Alert(StartGame.grid,e.getMessage());
				alert.popALert();
				
				e.printStackTrace();
			}
		});
		
		//sets useSpecial button
//		useSpecial.setPrefSize(200, 100);  // button width = 200 and height = 100
//		Image useSpecialImage = new Image("SpecialAction.jpg");
//		ImageView imageView2 = new ImageView(useSpecialImage);
//		useSpecial.setGraphic(imageView2);
//		imageView2.setFitWidth(50);
//		imageView2.setFitHeight(50);
		useSpecial.setOnMouseClicked(event ->{
			try {
				StartGame.activeHero.useSpecial();
				Controller.updateData();
				
			} catch (Exception e) {
				Alert alert= new Alert(StartGame.grid,e.getMessage());
				alert.popALert();
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		});
		
		//sets end turn button
//		endTurn.setPrefSize(200, 100);  // button width = 200 and height = 100
//		Image endTurnImage = new Image("EndTurn.jpg");
//		ImageView imageView3 = new ImageView(endTurnImage);
//		endTurn.setGraphic(imageView3);
//		imageView3.setFitWidth(50);
//		imageView3.setFitHeight(50);
		
		endTurn.setOnMouseClicked(event ->{
			try {
				Game.endTurn();
				System.out.println("HELLLo");
				Controller.updateData();
				
			} catch (Exception e) {
				Alert alert= new Alert(StartGame.grid,e.getMessage());
				alert.popALert();
				
				e.printStackTrace();
			}
		});
	
	
	removeTarget.setOnMouseClicked(event -> {
		StartGame.activeHero.setTarget(null);
	});
	
	
	}
	
	
	
	
	
}

