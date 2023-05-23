package views;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class pauseButton extends Button {

	pauseMenu menu = new pauseMenu();
	public pauseButton(){
		super();
		setPrefSize(30,30);
		setStyle("-fx-background-color: red;");
		setFont(Font.font("Impact",25));
		setText("||");
		this.setOnMouseClicked(event ->{
			pauseMenu.display();
		});
	}
	
	public void configButton(){
		
	}
}
