package views;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class tatos extends Application {

	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();

//creating the data labels
		Label name = new Label("Name:"); // add something to hold the name next to it
		Label type = new Label("Type:"); // add something to hold the type next to it
		Label supplies = new Label("Supplies:"); // add something to hold the count of supplies next to it
		Label vaccines = new Label("Vaccines:"); // add something to hold the count of vaccines next to it
		Label warning = new Label("Warning! You have stumbled upon a trap cell. Your health will now decrease.");
		
//creating the health bar     // we don't need an empty transparent bar in the background
		ProgressBar healthBar = new ProgressBar();
		healthBar.setProgress(1);
		healthBar.setStyle("-fx-accent: green;"); // temporary

		
// creating the buttons
		
		//attack button
		Button attack = new Button("Attack");
		attack.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image attackImage = new Image("attack.jpg");
		ImageView imageView = new ImageView(attackImage);
		attack.setGraphic(imageView);
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		
		//cure button
		
		Button cure = new Button("Cure");
		cure.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image cureImage = new Image("Cure.jpg");
		ImageView imageView1 = new ImageView(cureImage);
		cure.setGraphic(imageView1);
		imageView1.setFitWidth(50);
		imageView1.setFitHeight(50);
		//Use Special Action button
		
		
		//TODO:ADD use special picture
		Button useSpecial = new Button("Use Special Action");
		useSpecial.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image useSpecialImage = new Image("SpecialAction.jpg");
		ImageView imageView2 = new ImageView(useSpecialImage);
		useSpecial.setGraphic(imageView2);
		imageView2.setFitWidth(50);
		imageView2.setFitHeight(50);
		
		//End Turn button
		Button endTurn = new Button("End Turn");
		endTurn.setPrefSize(200, 100);  // button width = 200 and height = 100
		Image endTurnImage = new Image("EndTurn.jpg");
		ImageView imageView3 = new ImageView(endTurnImage);
		endTurn.setGraphic(imageView3);
		imageView3.setFitWidth(50);
		imageView3.setFitHeight(50);
		
		//Exit Game
		Button exitGame = new Button("Exit Game");
		exitGame.setPrefSize(200, 100);  // button width = 200 and height = 100
		
		
		
// handling each button's event (Not done yet)
		
	
		
// adding elements to the window 
		
		root.add(attack,0,0);  // 0,0 are the X,Y coordinates on the window
		root.add(cure,1,0);   //  1,0 are the X,Y coordinates on the window
		root.add(useSpecial,0,1); // 0,1 are the X,Y coordinates on the window
		root.add(endTurn,1,1);   //  1,1 are the X,Y coordinates on the window
		root.add(exitGame,2,2);   //  2,2 are the X,Y coordinates on the window
		root.add(healthBar, 15, 9);
		root.add(name, 15, 10);
		root.add(type, 15, 11);
		root.add(supplies, 20, 10);
		root.add(vaccines, 20, 11);
		root.add(warning, 20, 20);
		Scene s = new Scene(root,1000,600);  // 1000,600 is the window's size
		primaryStage.setScene(s);
		primaryStage.show();}

	
	public static void main(String[] args) {
		launch(args);
	}

}


