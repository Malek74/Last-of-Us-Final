package views;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameButtons extends Button{

	public GameButtons(String text){
		super(text);
		setStyle("-fx-background-color: red;");
		setFont(Font.font("Impact",50));
		setTextFill(Color.BLACK);
		
		
		
	}
}
