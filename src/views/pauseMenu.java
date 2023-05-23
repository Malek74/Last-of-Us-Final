package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class pauseMenu  {

	private static VBox root= new VBox();
	
	
	static GameButtons sound=new GameButtons("Sound: ") ;
	static GameButtons quit= new GameButtons("QUIT");
	static GameButtons help= new GameButtons("HOW TO PLAY ");
	static GameButtons resume= new GameButtons("RESUME");
	static boolean playing = true;
	
	public static void display(){
	
		Stage window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		
		
		
		quit.setOnAction(e->System.exit(0));
		resume.setOnAction(event ->{
			window.hide();
		});
		VBox layout=new VBox(10);
		layout.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		layout.getChildren().addAll(resume,sound,quit);
		Scene scene=new Scene(layout,200,100);


		window.setScene(scene);
		window.showAndWait();
	}
	
	
	public void configButtons(){
		
	}
}
