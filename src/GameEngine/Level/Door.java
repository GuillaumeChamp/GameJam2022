package GameEngine.Level;

import GUI.AnimatedImage;
import GUI.GameScene;
import GameEngine.Entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Door extends Entity {
    public enum Position {Nord,Sud,Est,Ouest}
    private final Room nextRoom;
    private final Position position;

    public Door(Position pos, Room nextRoom){
        this.nextRoom = nextRoom;
        this.position = pos;
        this.height=100;
        this.width=100;
        int imageID=0;
        switch (pos){
            case Nord -> {
                xPos = GameScene.width/2;
                yPos = 0;
                this.height=20;
                this.width=80;
            }
            case Est -> {
                imageID = 1;
                xPos = GameScene.width;
                yPos = GameScene.height/2;
                this.height=80;
                this.width=20;
            }
            case Sud -> {
                imageID = 2;
                xPos = GameScene.width/2;
                yPos = GameScene.height;
                this.height=20;
                this.width=80;
            }
            case Ouest -> {
                imageID = 3;
                xPos = 0;
                yPos = GameScene.height/2;
                this.height=80;
                this.width=20;
            }
        }
        Image source = new Image("Resources/Sprites/door.png");
        PixelReader reader = source.getPixelReader();
        Image[] temp = new Image[1];
        temp[0] = new WritableImage(reader, 1600*imageID, 0, 1600, 900);
        this.skin=new AnimatedImage(temp);
    }

    public Room getNextRoom(){return nextRoom;}
    public Position getPosition() {return position;}
}
