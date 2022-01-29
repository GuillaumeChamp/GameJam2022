package GameEngine.Level;

import GameEngine.Entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Door extends Entity {
    enum position {Nord,Sud,Est,Ouest}
    private final Room nextRoom;
    private final position position;

    public Door(position pos, Room nextRoom){
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
        temp[0] = new WritableImage(reader, 40*imageID, 0, 40, 40);
        skin.setFrames(temp);
    }

    public void nextRoom(){
    }
}
