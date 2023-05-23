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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Alert extends Label {
	private PauseTransition pause = new PauseTransition(Duration.seconds(3));
	private Node root;

	// sddd
	public Alert() {
		super();
		this.setVisible(false);
		this.setFont(Font.font("Impact", 25));
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setTranslateZ(5);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setStyle("-fx-border-color: white;");

	}

	public Alert(StackPane grid, String text) {
		super();
		this.setVisible(false);
		this.setFont(Font.font("Verdana", 25));
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		this.setTextFill(Color.WHITE);
		this.setTranslateZ(1);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
		this.setStyle("-fx-border-color: white;");
		grid.getChildren().add(this);
		this.setText(text);

	}

	public void popALert(StackPane root) {
		this.setVisible(true);
		System.out.println("JELLO");
		root.setMouseTransparent(true);

		final EventHandler<KeyEvent> disableKeysHandler = event -> {
			if (this.isVisible()) {
				event.consume();
			}
		};
		root.addEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
		root.addEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);
		StartGame.grid.addEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
		StartGame.grid.addEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);
		StartGame.gameScreen.addEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
		StartGame.gameScreen.addEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);

		pause.setOnFinished(event -> {
			this.setVisible(false);
			root.setMouseTransparent(false);
			root.removeEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
			root.removeEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);

			StartGame.grid.removeEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
			StartGame.grid.removeEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);

			StartGame.gameScreen.removeEventFilter(KeyEvent.KEY_PRESSED, disableKeysHandler);
			StartGame.gameScreen.removeEventFilter(KeyEvent.KEY_RELEASED, disableKeysHandler);

		});

		pause.playFromStart();

	}

	public void popALert() {
		this.setVisible(true);
		this.root = this.getParent();
		System.out.println("JELLO");
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
