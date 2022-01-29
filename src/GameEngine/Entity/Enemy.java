package GameEngine.Entity;

import GUI.AnimatedImage;
import GameEngine.Physic.Chase;
import javafx.scene.image.Image;

public class Enemy extends Entity implements Chase {

    private static int wound = 3;

    Image[] boss_frames = {new Image("Resources/Sprites/boss1.png")};
    Image[] can_frames = {new Image("Resources/Sprites/canette.png")};
    Image[] bottle_frames = {new Image("Resources/Sprites/bouteille.png")};
    Image[] tire_frames = {new Image("Resources/Sprites/pneu.png")};
    Image[] censured_frames = {new Image("Resources/Sprites/censured.png")};

    public int typeNumber;
    public int line;
    public int column;
    private int life = 15;

    public Enemy(int tn, int l, int c) {
        this.typeNumber = tn;
        this.line = l;
        this.column = c;
        xPos = l*1600/13;
        yPos = c*900/9;
        width = 80;
        height = 80;
        switch (tn) {
            case 4 : {skin = new AnimatedImage(boss_frames); width=400; height=400; break;}
            case 10 : {skin = new AnimatedImage(tire_frames);break;}
            case 11 : {skin = new AnimatedImage(can_frames);break;}
            case 12 : {skin = new AnimatedImage(bottle_frames);break;}
            default : {skin = new AnimatedImage(censured_frames);break;}
        }
    }

    @Override
    public void chase() {

    }

    public void getsHurt() throws Exception {
        life -= wound;
        if (life<0) {throw new Exception("IsDead");}
    }

}
