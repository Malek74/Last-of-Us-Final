package views;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ActionsList extends GridPane{

	Button endTurn = new Button("End Turn");
	Button attack = new Button("Attack");
	Button cure = new Button("Cure");
	Button useSpecial = new Button("Use Special Action");
	
	
	public ActionsList(){
		super();
		setButtons();
		add(endTurn,0,0);
		add(attack,0,1);
		add(cure,0,2);
		add(useSpecial,0,3);
		setVgap(10);
		
	}
	
	private void setButtons(){
		
		//sets attack button
		attack.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image attackImage = new Image("attack.jpg");
		ImageView imageView = new ImageView(attackImage);
		attack.setGraphic(imageView);
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		
		//sets cure button 
		cure.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image cureImage = new Image("Cure.jpg");
		ImageView imageView1 = new ImageView(cureImage);
		cure.setGraphic(imageView1);
		imageView1.setFitWidth(50);
		imageView1.setFitHeight(50);
		
		//sets useSpecial button
		useSpecial.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image useSpecialImage = new Image("SpecialAction.jpg");
		ImageView imageView2 = new ImageView(useSpecialImage);
		useSpecial.setGraphic(imageView2);
		imageView2.setFitWidth(50);
		imageView2.setFitHeight(50);
		
		//sets end turn button
		endTurn.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image endTurnImage = new Image("EndTurn.jpg");
		ImageView imageView3 = new ImageView(endTurnImage);
		endTurn.setGraphic(imageView3);
		imageView3.setFitWidth(50);
		imageView3.setFitHeight(50);
	};
}
