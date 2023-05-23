package views;


import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;


public class HerosList extends GridPane {
	
	
	public HerosList(){
		super();
		updateHerosList();
		
//		this.add(new Text("MALEK"), 0, 0);
	}
	
	 public void updateHerosList(){
		 this.getChildren().clear();
		for(int i=0;i<Game.heroes.size();i++){
			
			
			HeroInfo label= new HeroInfo(Game.heroes.get(i));
			label.updateInfo(Game.heroes.get(i));
			this.add(label, 0, i);
			this.setAlignment(Pos.TOP_RIGHT);
			
			this.setVgap(5);
		}
	}
	
	
	
}
