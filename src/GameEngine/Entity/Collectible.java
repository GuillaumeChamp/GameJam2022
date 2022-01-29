package GameEngine.Entity;

import GUI.AnimatedImage;
import javafx.scene.image.Image;

public class Collectible extends Entity {
    public enum Type {YELLOW, GREEN, BLACK, RED}

    private Type type;

    public Collectible(Type type) {
        this.type=type;}

    public Collectible(Type type, double x, double y, double h, double w) {
        this.type = type;
        this.xPos = x;
        this.yPos = y;
        this.height = h;
        this.width = w;
        switch (type){
            case RED -> skin= new AnimatedImage(new Image[]{new Image("Resources/Sprites/rouge.png")});
            case GREEN -> skin= new AnimatedImage(new Image[]{new Image("Resources/Sprites/vert.png")});
            case BLACK -> skin= new AnimatedImage(new Image[]{new Image("Resources/Sprites/noir.png")});
            case YELLOW -> skin= new AnimatedImage(new Image[]{new Image("Resources/Sprites/jaune.png")});
        }
    }

    // getters
    public Type getType() {return type;}
}
