package views;

import model.characters.Hero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HeroInventory extends Label {
	public HeroInventory(Hero h){
		super();
		
		String heroInventory= "Supplies: "+h.getSupplyInventory().size() + "\nVaccines: " +h.getVaccineInventory().size();
		
		this.setText(heroInventory);
		this.setFont(Font.font("Verdana",25));
//		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));        
	}
	
	public void updateInventory(Hero h){
		String heroInventory= "Supplies: "+h.getSupplyInventory().size() + "\nVaccines: " +h.getVaccineInventory().size();
		this.setText(heroInventory);
		
	}

}
