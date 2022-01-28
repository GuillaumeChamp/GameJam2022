package GUI;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameScene extends Scene {
    private GraphicsContext gc;
    private static double width;
    private static double height;
    private Canvas canvas;

    public GameScene(Parent parent, Canvas canvas,double width,double height) {
        super(parent, width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.canvas=canvas;
        GameScene.width = width;
        GameScene.height = height;
        this.addResizeable();
    }

    /**
     * Add the resizeable behaviour to the GameScene
     */
    private void addResizeable(){
        this.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            width = (double) newSceneWidth;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
        this.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            height = (double) newSceneHeight;
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
    }

    /**
     * Function which paint all element
     * @param time current time, used to animated image
     */
    public void paint(double time){
        AnimatedImage skin = new AnimatedImage("Resources/Sprites/shark.jpg");
        //all elements position and size must be linked to the height and the width
        gc.drawImage(skin.getFrame(time),width/2,height/2,width/10,height/10);
    }
}
