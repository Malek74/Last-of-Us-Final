package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class helpMenu {

	public static void display(){
	
		Stage window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		VBox layout=new VBox(10);
		layout.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		
		Scene scene=new Scene(layout,200,100);


		window.setScene(scene);
		window.showAndWait();
	}
	
	
	public void configButtons(){
		
	}
}
