package GUI;

import GameEngine.Player;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class Controller {
    public static ArrayList<KeyCode> inputString;
    public static void input(KeyCode code){
        //here add exceptional input with if(code==KeyCode.X) ... break;
        if (!inputString.contains(code)) inputString.add(code);
    }
    public static void output(KeyCode code){
        inputString.remove(code);
    }

    public static void action(Player player){

    }
}
