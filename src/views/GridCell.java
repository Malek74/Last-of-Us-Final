package views;



import model.characters.Direction;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class GridCell extends Rectangle{
	
	
	private TranslateTransition translate = new TranslateTransition();

	public GridCell(){
		super();
	 
		this.setHeight(65);
		this.setWidth(65);
		this.setStroke(Color.WHITE);
		this.setStrokeWidth(0.5);
		
		
		
	}
	
	public void setImage(String imagePath){
		Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(65);
        imageView.setFitWidth(65);
		
		javafx.scene.paint.ImagePattern pattern = new javafx.scene.paint.ImagePattern(imageView.getImage());
		this.setFill(pattern);
	}
	
	public void move(Direction d){
		translate.setDuration(Duration.millis(1000));
		
		switch (d){
			case UP:
				translate.setByY(-65);
				break;
			case DOWN:
				translate.setByY(65);
				break;
			case RIGHT:
				translate.setByX(65);
				break;
			case LEFT:
				translate.setByX(-65);
				break;
		}
		
		
		
		
		translate.setNode(this);
		translate.play();
	}
}
