package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedImage
{
    protected Image[] frames;
    protected double duration;

    public AnimatedImage() {}

    public AnimatedImage(String path){
        //frames = ImageBuilder.build(path);
        frames = new Image[1];
        try {
            frames[0] = new Image(path);
        }catch (Exception e){
            e.printStackTrace();
            frames[0]= new Image("Resources/Sprites/shark.jpg");
        }
        duration = 1;
    }

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }


    /**
     * Useful for animation and cinematic
     * @param duration frame duration on second (I guess)
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Useful to change the texture might be use on trigger or cinematic
     * @param frames new texture of the character
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
}
