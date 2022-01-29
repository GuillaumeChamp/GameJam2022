package GameEngine.Entity;

import GUI.AnimatedImage;
import GameEngine.Physic.Chase;
import javafx.scene.image.Image;

public class Enemy extends Entity implements Chase {

    Image[] boss_frames = {new Image("Resources/Sprites/boss1.png")};
    Image[] can_frames = {new Image("Resources/Sprites/canette.png")};
    Image[] bottle_frames = {new Image("Resources/Sprites/bouteille.png")};
    Image[] tire_frames = {new Image("Resources/Sprites/pneu.png")};
    Image[] censured_frames = {new Image("Resources/Sprites/censured.png")};

    public int mobId;
    public int line;
    public int column;
    private int life = 15;
    private Collectible.Type type;

    public Enemy(int tn, int l, int c) {
        this.mobId = tn;
        this.line = l;
        this.column = c;
        xPos = l*1600/13;
        yPos = c*900/9;
        width = 80;
        height = 80;
        switch (tn) {
            case 4 : {skin = new AnimatedImage(boss_frames);
                type = Collectible.Type.RED;
                this.height=400;
                this.width=400;
                break;}
            case 10 : {skin = new AnimatedImage(tire_frames);
                type = Collectible.Type.BLACK;
                break;}
            case 11 : {skin = new AnimatedImage(can_frames);
                type = Collectible.Type.YELLOW;
                break;}
            case 12 : {skin = new AnimatedImage(bottle_frames);
                type = Collectible.Type.GREEN;
                break;}
            default : {skin = new AnimatedImage(censured_frames);
                type = Collectible.Type.BLACK;break;}
        }
    }

    @Override
    public void chase() {

    }

    public Collectible.Type getType() {
        return type;
    }

    public void getsHurt(double damage) throws Exception {
        life -= damage;
        if (life<=0) {throw new Exception("IsDead");}
    }

}
