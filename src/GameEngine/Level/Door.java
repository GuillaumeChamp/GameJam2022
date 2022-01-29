package GameEngine.Level;

import GUI.AnimatedImage;
import GameEngine.Entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Door extends Entity {
    public enum Position {Nord,Sud,Est,Ouest}
    private final Room nextRoom;
    private final Position position;

    public Door(Position pos, Room nextRoom){
        this.skin=new AnimatedImage("Resources/Sprites/door.png");
        this.nextRoom = nextRoom;
        this.position = pos;
        int imageID=0;
        switch (pos){
            case Nord -> {
                xPos = width/2;
                yPos = 0;
            }
            case Est -> {
                imageID = 1;
                xPos = width;
                yPos = height/2;
            }
            case Sud -> {
                imageID = 2;
                xPos = width/2;
                yPos = height;
            }
            case Ouest -> {
                imageID = 3;
                xPos = 0;
                yPos = height/2;
            }
        }
        Image source = new Image("Resources/Sprites/door.png");
        PixelReader reader = source.getPixelReader();
        Image[] temp = new Image[1];
        temp[0] = new WritableImage(reader, 1600*imageID, 0, 1600, 900);

    }

    public Room getNextRoom(){return nextRoom;}
    public Position getPosition() {return position;}
}
