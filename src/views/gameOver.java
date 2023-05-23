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

public class gameOver {
	public static void display(String message) {
		Stage window=new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		Label messageLabel=new Label(message);
		messageLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		Button quit=new Button("quit");
		quit.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
		quit.setOnAction(e->System.exit(0));
		VBox layout=new VBox(10);
		layout.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY,null);
		Background background = new Background(backgroundFill);
		layout.setBackground(background);
		layout.getChildren().addAll(messageLabel,quit);
		Scene scene=new Scene(layout,200,100);
		window.setOnCloseRequest(event ->{
			System.exit(0);			
		});
		


		window.setScene(scene);
		window.showAndWait();
		
	}
}