package GameEngine.Entity;

import GameEngine.Player;

public class Item {

    private int ID;
    private String name;
    private int[] cost;
    private double[] effect; // PV ATTF ATTM SPEED RANGE AS
    private String EFF;
    private String description;

    public Item(int ID, String name, int[] cost,double[] effect,String EFF,String description) {
        this.ID = ID;
        this.name = name;
        this.cost = cost;
        this.effect = effect;
        this.EFF = EFF;
        this.description = description;
    }

    public void applyEffect(Player player) {
        player.PV += effect[0];
        player.attack += effect[1];
        player.attack *= effect[2];
        player.speed += effect[3];
        player.range += effect[4];
        player.attackSpeed += effect[5];

        //TODO : manage EFF special effect
    }

    // getters
    public String getName() {return name;}
    public int[] getCost() {return cost;}
}
