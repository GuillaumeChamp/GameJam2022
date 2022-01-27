import Audio.BackgroundMusic;
import GUI.GameScene;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Render extends Application {

    final long startNanoTime = System.nanoTime();
    Group root = new Group();

    final double defaultWidth = Screen.getPrimary().getBounds().getWidth();
    final double defaultHeight = Screen.getPrimary().getBounds().getHeight();

    Canvas canvas = new Canvas(defaultWidth, defaultHeight);
    BackgroundMusic sound = new BackgroundMusic("default");

    public void start(Stage theStage) {
        theStage.setTitle("GameJam2022");
        theStage.setMaximized(true);
        theStage.getIcons().add(new Image("Resources/Sprites/shark.jpg"));

        root.getChildren().add(canvas);

        GameScene scene = new GameScene(root, canvas, defaultWidth, defaultHeight);
        theStage.setScene(scene);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                scene.paint(t);
            }
        }.start();
        theStage.show();
    }

}