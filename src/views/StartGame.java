package views;


import java.io.File;
import java.util.ArrayList;
import model.characters.*;
import model.world.CharacterCell;
import javafx.scene.control.Label;
import engine.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartGame extends Application{
	Stage mainStage = new Stage();
	Image back= new Image("background.jpg") ;
	static String path="soundTrack.mp3";
	static Media media= new Media (new File(path).toURI().toString());
	static MediaPlayer player = new MediaPlayer(media);
	BackgroundImage loadingBackground= new BackgroundImage(back,  BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
	Background loadingBackgroundRoot = new Background(loadingBackground);
	
	ArrayList <Label> attributes= new ArrayList<Label>();
	
	static BorderPane gameRoot= new BorderPane();
	
	static Gameplay gameScreen = new Gameplay(gameRoot);
	public static StackPane grid = new StackPane();
	static HerosList herosList = new HerosList();
	ActionsList actions = new ActionsList(); 
	GridPane rightBar=new GridPane();
	static HeroInventory inventory ;
	static Hero activeHero;
	Controller controller = new Controller();
	
	
	Button displayHerosBtn= new Button("Heroes");
	Button displayActionsBtn= new Button("Actions");
	
	
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		

		BorderPane root = new BorderPane();
		HBox heros= new HBox();
		StackPane root2 = new StackPane();
		
		Scene startScreen= new Scene(root,600,600);
		VBox startGame= new VBox();
		
		Text title = new Text();
		
		
		Button startBtn = new GameButtons("START GAME");
		GameButtons quitGame= new GameButtons ("QUIT GAME");
		
		Scene chooseHero= new Scene (root2,600,600);
		
		
		Alert hoverInstruction = new Alert();
		hoverInstruction.setText("Hover over character to know his attributes ");
		
		
		
		
		//title of game
		mainStage.setTitle("The last Of Us - Legacy");
		
		
		
		//sets up start game button and its listener
		
		startBtn.setOnAction(event ->{
			
			
			
			try {
				Game.loadHeroes("M:\\GUC\\Last_Of_Us_Legacy\\Heroes.csv");
			} catch (Exception e) {
				System.out.println("Cannot Load Heros");
				
			}
			
			//sets scene of choosing hero
			mainStage.setScene(chooseHero);
			mainStage.show();
			mainStage.setFullScreen(true);
						
			GridPane attributesTable= attributesPanel();
			attributesTable.setAlignment(Pos.CENTER);
			

			GridPane options= herosPanel();
			heros.getChildren().add(options);
			
			root2.getChildren().add(heros);
			root2.getChildren().add(hoverInstruction);
			
			hoverInstruction.popALert();		
		});
		
		
		//sets up quit button 
		
		quitGame.setOnAction(event ->{
			System.exit(0);
		});
		
		//set alignment of start game root children
		quitGame.setAlignment(Pos.CENTER);
		startGame.setAlignment(Pos.CENTER);
		heros.setAlignment(Pos.CENTER);
		
		
		//sets title of game
		title.setText("THE\nLAST\nOF US");
		title.setFont(Font.font("Impact",75));
		title.setFill(Color.WHITE);
		
		
		

		// Set autoPlay to true
		player.setAutoPlay(true);
		
		//sets start game root 
		startGame.setSpacing(5);
		startGame.getChildren().add(title);
		startGame.getChildren().add(startBtn);
		startGame.getChildren().add(quitGame);
		
		//set stage with loading screen
		mainStage.setFullScreen(true);
		root.setBackground(loadingBackgroundRoot);
		heros.setBackground(loadingBackgroundRoot);
		root.setCenter(startGame);
		
		
	 	
		mainStage.setScene(startScreen);
		mainStage.show();	
		
		
		
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
	        herosNames.get(i).setFont(Font.font("Impact",50));
	        int x=i;
	        herosNames.get(x).setOnMouseEntered(event -> {
				 attributes.get(x).setVisible(true);
					 				 
			 });
	        herosNames.get(x).setOnMouseExited(event -> {
				 attributes.get(x).setVisible(false);
					 
			 });
	        
	        herosNames.get(x).setOnMouseClicked(event ->{
	        	Game.startGame(Game.availableHeroes.get(x));
	        	activeHero=Game.heroes.get(0);
	        	//TODO:DOn't forget to delete
	        	activeHero.setActionsAvailable(10000);
	        	
//	        	createButtonsFunctionality();
	        	rightBar.add(displayActionsBtn, 0, 0);
	        	rightBar.add(displayHerosBtn, 1, 0);
	        	
	        	
	        	
	        	gameScreen.updateMap();
	        	gameScreen.map.setAlignment(Pos.CENTER);
	            grid.getChildren().add(gameScreen.map);
	        	
	        	
	        	
	        	inventory= new HeroInventory(Game.availableHeroes.get(x));
	        	
	        	
	        	
	        	
	        	gameRoot.setBackground(loadingBackgroundRoot);
	        	
	        	
	        	controller.move(gameScreen,grid);
	        	gameRoot.setCenter(grid);
	        	gameRoot.setRight(herosList);
	        	gameRoot.setLeft(inventory);
	        	gameRoot.setBottom(actions);
	        	
	        	mainStage.setScene(gameScreen);
	        	mainStage.setFullScreen(true);
	        	
	        });
	        
	       
	    }
	    
	    for (int i = 0; i < Game.availableHeroes.size(); i++) {
	        type = Game.availableHeroes.get(i).getClass();

	        int x=i;
	        
	        ImageView imageView = new ImageView();
	        imageView.setFitWidth(100);
	        imageView.setFitHeight(100);
	        imageView.setOnMouseEntered(event -> {
				 attributes.get(x).setVisible(true);
				 
			 });
	        imageView.setOnMouseExited(event -> {
				 attributes.get(x).setVisible(false);
			 });
	        
	        imageView.setOnMouseClicked(event ->{
	        	Game.startGame(Game.availableHeroes.get(x));
	        	activeHero=Game.heroes.get(0);
	        	
	        	
	        	
	        	herosList.updateHerosList();
	        	
	        	
	        	
	        	
	        	
	        	gameScreen.updateMap();
	        	gameScreen.map.setAlignment(Pos.CENTER);
	            grid.getChildren().add(gameScreen.map);
	        	
	        	
	        	
	        	inventory= new HeroInventory(Game.availableHeroes.get(x));
	        	
	        	
	        	gameRoot.setCenter(grid);
	        	
	        	
	        	gameRoot.setBackground(loadingBackgroundRoot);
	        	
	        	
	        	controller.move(gameScreen,grid);
	        	gameRoot.setCenter(gameScreen.map);
	        	gameRoot.setRight(herosList);
	        	gameRoot.setLeft(inventory);
	        	
	        	mainStage.setScene(gameScreen);
	        	mainStage.setFullScreen(true);
	        });
	            
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
		    		 attributes.get(i).setFont(Font.font("Impact",25));	
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

//	private void createButtonsFunctionality(){
//		
//		
//		displayActionsBtn.setStyle("-fx-background-color: white;");
//		displayActionsBtn.setOnMouseClicked(event ->{
//			rightBar.getChildren().remove(herosList);
//		});
//		
//		displayHerosBtn.setStyle("-fx-background-color: white;");
//		displayHerosBtn.setOnMouseClicked(event ->{
//			rightBar.getChildren().remove(actions);
//			herosList.updateHerosList();
//			rightBar.add(herosList,0,1,2,1);
//		});
//		
//		
//		
//	}
//	
	
	
	
	
}
