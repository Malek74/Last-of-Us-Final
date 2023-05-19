package views;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Styling extends Scene {
    
	
    public Styling(Parent root) {
        super(root,600,400,Color.ALICEBLUE);
    }

    public Styling(Parent root, double width, double height) {
        super(root, width, height);
    }

    public Styling(Parent root, Paint fill) {
        super(root, fill);
    }

    public Styling(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }

    public Styling(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public Styling(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing sceneAntialiasing) {
        super(root, width, height, depthBuffer, sceneAntialiasing);
    }
    
    
}
