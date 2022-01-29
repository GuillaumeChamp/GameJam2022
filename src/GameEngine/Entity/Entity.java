package GameEngine.Entity;

import GUI.AnimatedImage;
import GameEngine.Constant;
import javafx.scene.image.Image;

public abstract class Entity {
    protected double xPos;
    protected double yPos;
    protected double height= Constant.Xstep;
    protected double width= Constant.Ystep;
    protected AnimatedImage skin = new AnimatedImage(new Image[]{new Image("Resources/Sprites/censured.png")});

    public AnimatedImage getSkin() {
        return skin;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
