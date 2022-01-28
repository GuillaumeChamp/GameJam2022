package GUI;

import GameEngine.Player;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Controller {

    public static ArrayList<KeyCode> inputString=new ArrayList<>();

    public static void input(KeyCode code){
        //here add exceptional input
        if(code==KeyCode.X) {
            System.out.println("rare event");
            return;
        }
        if (!inputString.contains(code)) inputString.add(code);
    }
    public static void output(KeyCode code){
        inputString.remove(code);
    }

    /**
     * function which move the player according to input and behaviour
     * @param player the player
     */
    public static void action(Player player){

    }
}
