package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.characters.Hero;

public class HeroInfo extends Label{

	public HeroInfo (Hero h){
		super();
		String herosDescription= h.getName() + "\nCurrent HP: " + h.getCurrentHp() + "\nActions Left: " + h.getActionsAvailable() ;
		
		this.setText(herosDescription);
		this.setFont(Font.font("Verdana",25));
//		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
//        this.setStyle("-fx-border-color: white;");
        
        
        
        
	}
}
