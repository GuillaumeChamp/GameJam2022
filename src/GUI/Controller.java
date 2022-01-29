package GUI;

import GameEngine.Player;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Controller {

    public static ArrayList<KeyCode> inputString=new ArrayList<>();

    private static boolean hits = false;

    public static void input(KeyCode code){
        if (!inputString.contains(code)) inputString.add(code);
    }
    public static void output(KeyCode code){
        inputString.remove(code);
        hits = false;
    }

    /**
     * function which move the player according to input and behaviour
     * @param player the player
     */
    public static void action(Player player) throws Exception {
        if (inputString.contains(KeyCode.Z)) {
            player.move("up");
        }
        if (inputString.contains(KeyCode.D)) {
            player.move("right");
        }
        if (inputString.contains(KeyCode.S)) {
            player.move("down");
        }
        if (inputString.contains(KeyCode.Q)) {
            player.move("left");
        }
        if (inputString.contains(KeyCode.K))
            player.attack();

        player.detectCollision(player.currentRoom.getEntities());
    }
}
