package views;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Alert extends Label {
	private PauseTransition pause = new PauseTransition(Duration.seconds(3));
    private Node root;

	
	public Alert(){
		super();
		this.setVisible(false);
		this.setFont(Font.font("Verdana",25));
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setTranslateZ(1);
		this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-border-color: white;");
        
	}
	
	public void popALert(){
		this.setVisible(true);
		this.root=this.getParent();
		root.setMouseTransparent(true);
		
		final EventHandler<KeyEvent> disableKeysHandler = event -> {
            if (this.isVisible()) {
                event.consume();
            }
        };
		root.addEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
        root.addEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);
        
        
		
		pause.setOnFinished(event -> {
			this.setVisible(false);
			root.setMouseTransparent(false);
	        root.removeEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
	        root.removeEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);
			});
        
		pause.playFromStart();
		
	}
	
	

}
