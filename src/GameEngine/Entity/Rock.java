package GameEngine.Entity;

import GUI.AnimatedImage;
import javafx.scene.image.Image;

public class Rock extends Entity {

    Image[] rock_frames = {new Image("Resources/Sprites/cailloux.png")};

    public Rock(int x, int y) {
        skin = new AnimatedImage(rock_frames);
        xPos = x;
        yPos = y;
        width = 130;
        height = 130;
    }

}
