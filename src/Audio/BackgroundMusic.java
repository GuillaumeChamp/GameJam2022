package Audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class BackgroundMusic implements Runnable{
    File file;
    final Media media;
    final MediaPlayer music;

    public BackgroundMusic(String name){
        try{
            file = new File("src/Resources/Audio/Music/" + name + ".mp3");
        }
        catch (Exception e){
            file=null;
            System.out.println(name);
            e.printStackTrace();
        }
        media = new Media(file.toURI().toString());
        music = new MediaPlayer(media);
        this.run();
    }
    @Override
    public void run() {
        music.setCycleCount(100);
        music.setVolume(music.getVolume()/8);
        music.setOnReady(()-> {
            music.play();
            music.setOnEndOfMedia(music::play);
        });
    }
}
