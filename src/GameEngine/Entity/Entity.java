package GameEngine.Entity;

import GUI.AnimatedImage;

public abstract class Entity {
    protected double xPos;
    protected double yPos;
    protected double height;
    protected double width;
    protected AnimatedImage skin;

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
