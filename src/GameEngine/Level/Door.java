package GameEngine.Level;

import GUI.AnimatedImage;
import GameEngine.Constant;
import GameEngine.Entity.Entity;
import javafx.scene.image.Image;

public class Door extends Entity {
    public enum Position {Nord,Sud,Est,Ouest}
    private final Room nextRoom;
    private final Position position;

    /**
     * Create a door
     * @param pos position of the door
     * @param nextRoom room linked
     */
    public Door(Position pos, Room nextRoom){
        this.nextRoom = nextRoom;
        this.position = pos;
        this.height=100;
        this.width=100;
        int imageID=0;
        switch (pos){
            case Nord -> {
                xPos = Constant.ROOMSWITHD/2;
                yPos = 0;
                this.height=20;
                this.width=80;
            }
            case Est -> {
                imageID = 1;
                xPos = Constant.ROOMSWITHD;
                yPos = Constant.ROOMHEIGHT/2;
                this.height=80;
                this.width=20;
            }
            case Sud -> {
                imageID = 2;
                xPos = Constant.ROOMSWITHD/2;
                yPos = Constant.ROOMHEIGHT;
                this.height=20;
                this.width=80;
            }
            case Ouest -> {
                imageID = 3;
                xPos = 0;
                yPos = Constant.ROOMHEIGHT/2;
                this.height=80;
                this.width=20;
            }
        }
        Image source = new Image("Resources/Sprites/p"+imageID+".png");
        Image[] temp = new Image[1];
        temp[0] = source;
        this.skin=new AnimatedImage(temp);
    }

    public Room getNextRoom(){return nextRoom;}
    public Position getPosition() {return position;}
}
