package GUI;

import GameEngine.Loader.ItemsLoader;
import GameEngine.Player;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameScene extends Scene {
    private final GraphicsContext gc;
    private static double width;
    private static double height;
    private final Canvas canvas;
    private Player player;

    public GameScene(Parent parent, Canvas canvas,double width,double height) {
        super(parent, width, height);
        this.gc = canvas.getGraphicsContext2D();
        this.canvas=canvas;
        GameScene.width = width;
        GameScene.height = height;
        this.addResizeable();
        this.addController();
        ItemsLoader il = new ItemsLoader();
        il.fillItems(".//src/Resources/Data/Items.csv");
        player = new Player();

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
     * Adding controller
     */
    private void addController(){
        this.setOnKeyPressed(e->Controller.input(e.getCode()));
        this.setOnKeyReleased(e->Controller.output(e.getCode()));
    }
    /**
     * Function which update the screen and entity
     * @param time current time, used to animated image
     */
    public void tick(double time){
        paint(time);
        Controller.action(player);

    }

    private void paint(double time){
        //all elements position and size must be linked to the height and the width
        AnimatedImage skin = new AnimatedImage("Resources/Sprites/shark.jpg");
        Image room = new Image("Resources/Sprites/Room.png");
        gc.drawImage(room,0,0,width,height);
        gc.drawImage(skin.getFrame(time),width/2,height/2,width/10,height/10);


    }
}
