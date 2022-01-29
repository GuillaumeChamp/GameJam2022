package GUI;

import GameEngine.Constant;
import GameEngine.Entity.Enemy;
import GameEngine.Entity.Entity;
import GameEngine.Entity.Rock;
import GameEngine.Level.Door;
import GameEngine.Loader.ItemsLoader;
import GameEngine.Player;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GameScene extends Scene {
    private final GraphicsContext gc;
    public static double width;
    public static double height;
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
        try {
            Controller.action(player);
        }catch (Exception e){
            //TODO : trigger game over
        }
        paint(time);
    }

    private void paint(double time){
        //all elements position and size must be linked to the height and the width
        Image room = new Image("Resources/Sprites/Room.png");
        gc.drawImage(room,0,0,width,height);
        gc.setStroke(Color.RED);
        double xRatio = width/Constant.ROOMSWITHD;
        double yRation =height/Constant.ROOMHEIGHT;
        for(Door d : player.currentRoom.getExits()) {
            gc.drawImage(d.getSkin().getFrame(time),0,0,width,height);
            gc.strokeRect(d.getxPos()*xRatio,d.getyPos()*yRation,d.getWidth(),d.getHeight());
        }
        for(Entity e: player.currentRoom.getEntities()){
            gc.drawImage(e.getSkin().getFrame(time),e.getxPos()*xRatio,e.getyPos()*yRation,e.getWidth()*xRatio,e.getHeight()*yRation);
            gc.strokeRect(e.getxPos()*xRatio,e.getyPos()*yRation,e.getWidth()*xRatio,e.getHeight()*yRation);
        }

        for(Rock r: player.currentRoom.getRocks()){
            gc.drawImage(r.getSkin().getFrame(time),r.getxPos()*xRatio,r.getyPos()*yRation,r.getWidth()*xRatio,r.getHeight()*yRation);
            gc.strokeRect(r.getxPos()*xRatio,r.getyPos()*yRation,r.getWidth()*xRatio,r.getHeight()*yRation);
        }

        for(Enemy e: player.currentRoom.getEnemies()){
            gc.drawImage(e.getSkin().getFrame(time),e.getxPos()*xRatio,e.getyPos()*yRation,e.getWidth()*xRatio,e.getHeight()*yRation);
            gc.strokeRect(e.getxPos()*xRatio,e.getyPos()*yRation,e.getWidth()*xRatio,e.getHeight()*yRation);
        }

        gc.drawImage(player.getSkin().getFrame(time),player.getxPos()*xRatio,player.getyPos()*yRation, player.getWidth()*xRatio, player.getHeight()*yRation);
        gc.strokeRect(player.getxPos()*xRatio,player.getyPos()*yRation,player.getWidth()*xRatio,player.getHeight()*yRation);

    }
}
