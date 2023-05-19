package views;

import model.characters.Direction;
import model.characters.Fighter;
import engine.Game;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application {

    
    @Override
    

    	public void start(Stage primaryStage) throws Exception {
    		Rectangle rect1 = new Rectangle(20,20,200,150);
    		rect1.setFill(Color.RED);
    		TranslateTransition translate = new TranslateTransition();
    		translate.setByX(100);
    		translate.setDuration(Duration.millis(1000));
//    		translate.setCycleCount(10);
//    		translate.setAutoReverse(true);
    		translate.setNode(rect1);
    		translate.play();
    		Group root = new Group();
    		root.getChildren().add(rect1);
    		Scene scene = new Scene(root,500,400);
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Translation Example");
    		primaryStage.show();
    		}
    
    public static void main(String[] args) {
		launch(args);
	}
}
