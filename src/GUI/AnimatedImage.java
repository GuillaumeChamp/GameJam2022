package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimatedImage
{
    protected ImageView[] frames;
    protected double duration;

    public AnimatedImage(String path){
        //frames = ImageBuilder.build(path);
        frames = new ImageView[1];
        try {
            frames[0] = new ImageView(path);
            frames[0].setPreserveRatio(true);
        }catch (Exception e){
            e.printStackTrace();
            frames[0]= new ImageView("./Resources/shark.jpg");
        }
        duration = 1;
    }

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index].getImage();
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
    public void setFrames(ImageView[] frames) {
        this.frames = frames;
    }
}
