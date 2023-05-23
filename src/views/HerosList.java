package views;


import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class HerosList extends GridPane {
	
	
	public HerosList(){
		super();
		updateHerosList();
		this.setPadding(new Insets(10));
//		this.add(new Text("MALEK"), 0, 0);
	}
	
	 public void updateHerosList(){
		 
		 Text Title=new Text();
		 Title.setText("HEROS");
		 Title.setVisible(true);
		 Title.setFill(Color.WHITE);
		 Title.setFont(new Font("Impact",50));
		 this.getChildren().clear();
		for(int i=0;i<Game.heroes.size();i++){
			
			this.add(Title, 0, 0);
			HeroInfo label= new HeroInfo(Game.heroes.get(i));
			label.updateInfo(Game.heroes.get(i));
			this.add(label, 0, i+1);
			this.setAlignment(Pos.TOP_RIGHT);
			
			this.setVgap(5);
		}
	}
	
	
	
}
