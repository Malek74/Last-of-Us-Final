package views;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application {

    private Scene scene1, scene2;
    private Button switchButton;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create the scenes
       Group root = new Group();
       Text text = new Text("Hello World!");
       text.setX(10);
       text.setY(10);
       text.setOnMouseEntered(e -> text.setFill(Color.RED));
       text.setOnMouseExited(e -> text.setFill(Color.BLACK));

       Scene s= new Scene(root,600,600);
       s.setFill(Color.ALICEBLUE);
       root.getChildren().add(text);
       primaryStage.setScene(s);

       primaryStage.show();
}
    public static void main(String[] args) {
		launch(args);
	}
}
