package views;

import java.awt.event.MouseEvent;

import engine.Game;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.characters.Hero;
import model.world.CharacterCell;

public class HeroInfo extends Label{

	public HeroInfo (Hero h){
		super();
		String herosDescription= h.getName() + "\nCurrent HP: " + h.getCurrentHp() + "\nActions Left: " + h.getActionsAvailable() ;
		
		this.setText(herosDescription);
		this.setFont(Font.font("Impact",25));
//		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
//       

        
        

    	
    	
        
        this.setOnMouseClicked(event -> {
        	StartGame.activeHero=h;
        	
        	Controller.updateData();
        	
        	
        	
			Gameplay.gridCells[StartGame.activeHero.getLocation().x][StartGame.activeHero.getLocation().y].setFill( Color.rgb(128, 128, 128, 0.5));

        	
        	
        	
        });
	}

        
        public void updateInfo(Hero h){
        	String herosDescription= "Type:" + h.getClass().getSimpleName()+"\nName:"+h.getName() + "\nCurrent HP: " + h.getCurrentHp() + "\nActions Left: " + h.getActionsAvailable() ;
        	this.setText(herosDescription);
        }
}
