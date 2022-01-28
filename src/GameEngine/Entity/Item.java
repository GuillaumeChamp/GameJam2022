package GameEngine.Entity;

import GameEngine.Player;

public class Item {

    private String noun;
    private int[] cost;

    public Item(String noun, int[] cost) {
        this.noun = noun;
        this.cost = cost;
    }

    public void applyEffect(Player player) {
        switch (noun) {
            case "noun" :
                player.PV++;
                break;
            default :
                break;
        }
    }

    // getters
    public String getNoun() {return noun;}
    public int[] getCost() {return cost;}
}
