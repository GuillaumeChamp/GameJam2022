package GameEngine.Entity;

import GameEngine.Player;
import javafx.scene.image.Image;

public class Collectible extends Entity {
    public enum Type {YELLOW, GREEN, BLACK, RED};

    private Type type;

    public Collectible(Type type, double x, double y, double h, double w, Image skin) {
        this.type = type;
        this.xPos = x;
        this.yPos = y;
        this.height = h;
        this.width = w;
        this.skin = skin;
    }

    // getters
    public Type getType() {return type;}
}
