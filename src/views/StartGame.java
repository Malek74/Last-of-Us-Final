package views;


import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import model.characters.*;
import javafx.scene.control.Label;
import engine.Game;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartGame extends Application{
	
	Image back= new Image("background.jpg") ;
	String path="soundTrack.mp3";
	Media media= new Media (new File(path).toURI().toString());
	BackgroundImage loadingBackground= new BackgroundImage(back,  BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
	Background loadingBackgroundRoot = new Background(loadingBackground);
	
	ArrayList <Label> attributes= new ArrayList<Label>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		BorderPane root = new BorderPane();
		HBox root2= new HBox();
		
		Scene startScreen= new Scene(root,600,600);
		VBox startGame= new VBox();
		MediaPlayer player = new MediaPlayer(media);
		Text title = new Text();
		Button startBtn = new Button("START GAME");
		
		Scene chooseHero= new Scene (root2,600,600);
		
		primaryStage.setTitle("The last Of Us - Legacy");
		
		boolean clicked=false;
		
		startBtn.setStyle("-fx-background-color: white;");
		startBtn.setOnAction(event ->{
			primaryStage.setScene(chooseHero);
			primaryStage.show();
			primaryStage.setFullScreen(true);
			try {
				Game.loadHeroes("M:\\GUC\\Last_Of_Us_Legacy\\Heroes.csv");
			} catch (Exception e) {
				System.out.println("Cannot Load Heros");
				
			}
			System.out.println(Game.availableHeroes.get(0).getAttackDmg());
			GridPane attributesTable= attributesPanel();
			attributesTable.setAlignment(Pos.CENTER);

			GridPane options= herosPanel();
			root2.getChildren().add(options);
			
			
			
//			root2.getChildren().add(attributesTable);
//			

//			attributesTable.setAlignment(Pos.CENTER);
//			root2.setRight(attributesTable);
		});
		
		
		
		
		
		
		
		startGame.setAlignment(Pos.CENTER);
		root2.setAlignment(Pos.CENTER);
		
		
		title.setText("THE\nLAST\nOF US");
		title.setFont(Font.font("Verdana",50));
		title.setFill(Color.WHITE);
		
		
		
		
		player.setAutoPlay(true);
		
		startGame.getChildren().add(title);
		startGame.getChildren().add(startBtn);
		
		primaryStage.setFullScreen(true);
		root.setBackground(loadingBackgroundRoot);
		root2.setBackground(loadingBackgroundRoot);
		root.setCenter(startGame);
		
		
	 	
        
		
		
        
		
		primaryStage.setScene(startScreen);
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private GridPane herosPanel() {
	    GridPane heroTable = new GridPane();
	    Class type;

	    heroTable.setAlignment(Pos.CENTER);
	    heroTable.setVgap(10);
	    heroTable.setHgap(20);

	    ArrayList<Text> herosNames = new ArrayList<>();
	    

	    for (int i = 0; i < Game.availableHeroes.size(); i++) {
	        herosNames.add(new Text(Game.availableHeroes.get(i).getName()));
	        herosNames.get(i).setFill(Color.WHITE);
	        herosNames.get(i).setFont(Font.font("Verdana",50));
	        int x=i;
	        herosNames.get(x).setOnMouseEntered(event -> {
				 attributes.get(x).setVisible(true);
					 
				 
			 });
	        herosNames.get(x).setOnMouseExited(event -> {
				 attributes.get(x).setVisible(false);
					 
			 });
	        
	       
	    }
	    
	    for (int i = 0; i < Game.availableHeroes.size(); i++) {
	        type = Game.availableHeroes.get(i).getClass();

	        ImageView imageView = new ImageView();
	        imageView.setFitWidth(100);
	        imageView.setFitHeight(100);
	        
	        if (type.equals(Fighter.class)) {
	            imageView.setImage(new Image("fighter.png"));
	            heroTable.add(imageView, 0, i);
	            heroTable.add(herosNames.get(i), 1, i);
	            heroTable.add(attributes.get(i), 2, i);
	        } else if (type.equals(Medic.class)) {
	            imageView.setImage(new Image("medic.jpg"));
	            heroTable.add(imageView, 0, i);
	            heroTable.add(herosNames.get(i), 1, i);
	            heroTable.add(attributes.get(i), 2, i);
	        } else if (type.equals(Explorer.class)) {
	            imageView.setImage(new Image("explorer.png"));
	            heroTable.add(imageView, 0, i);
	            heroTable.add(herosNames.get(i), 1, i);
	            heroTable.add(attributes.get(i), 2, i);
	        }
	    }
	   
		
	    return heroTable;
	}

	private GridPane attributesPanel(){
		
		GridPane attr= new GridPane();
		
		
		  for (int i = 0; i < Game.availableHeroes.size(); i++){
		    	String text= getAttributes(i);
		    		 attributes.add(new Label());
		    		 attributes.get(i).setText(text);
		    		 attributes.get(i).setTextFill(Color.WHITE);
		    		 attributes.get(i).setFont(Font.font("Verdana",25));	
		    		 attributes.get(i).setVisible(false);
		    		 attributes.get(i).setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		    		 
		    }
		  
		  for(int i=0;i<attributes.size();i++){
			  attr.add(attributes.get(i), 0, i);
		  }
		  return attr;
	}
	private String getAttributes(int index){
		return " Health: " + Game.availableHeroes.get(index).getCurrentHp() + "\n Max Actions: " + Game.availableHeroes.get(index).getActionsAvailable() + "\n Attack Damages: " + Game.availableHeroes.get(index).getAttackDmg();
	}

	
}
