package GameEngine;

import javafx.scene.media.AudioClip;

import java.io.File;

public class Constant {
    public static double ROOMHEIGHT=900;
    public static double ROOMSWITHD=1600;
    public static double Xstep = ROOMSWITHD/11;
    public static double Ystep = ROOMHEIGHT/7;
    static int[] seed = {1,1,2,2,1,6};
    public static AudioClip deathSound = new AudioClip(new File("src/Resources/Audio/Clip/MORT.mp3").toURI().toString());
}
