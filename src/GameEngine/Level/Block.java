package GameEngine.Level;

import javafx.scene.image.Image;

public class Block {
    protected double xPos;
    protected double yPos;
    protected Image skin;
    protected double height = 32;
    protected double width = 32;

    public Block(double xPos, double yPos, Image skin) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.skin = skin;
    }

    public double[] getPos(){
        return new double[]{xPos,yPos};
    }
}
