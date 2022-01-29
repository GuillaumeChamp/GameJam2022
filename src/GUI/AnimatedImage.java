package GUI;

import javafx.scene.image.Image;

public class AnimatedImage
{
    protected Image[] frames;
    protected double duration;

    public AnimatedImage(Image[] images){
        frames = images;
    }

    public AnimatedImage(String path,int animationId,int XStep,int YStep ){
        try {
            frames = ImageCropper.crop(path,animationId,XStep,YStep);
        }catch (Exception e){
            e.printStackTrace();
            frames[0]= new Image("Resources/Sprites/shark.jpg");
        }
        duration = 0.5;
    }

    public Image getFrame(double time)
    {
        int index = (int)((time % (frames.length * duration)) / duration);
        return frames[index];
    }

    /**
     * Useful to change the texture might be use on trigger or cinematic
     * @param frames new texture of the character
     */
    public void setFrames(Image[] frames) {
        this.frames = frames;
    }
}
