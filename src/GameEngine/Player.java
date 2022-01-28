package GameEngine;

import GameEngine.Entity.Collectible;
import GameEngine.Entity.Entity;
import GameEngine.Entity.Item;
import GameEngine.Entity.WorkBench;
import GameEngine.Level.*;
import GameEngine.Physic.*;

import java.util.ArrayList;

public class Player extends Entity implements OnWallCollision, OnEntityCollision {

    public double PV = 3;
    public double attack = 3;
    public double speed = 1;
    public double range = 3;
    public double attackSpeed = 1; // per second

    private int yellowStack;
    private int greenStack;
    private int blackStack;

    private double xSpeed;
    private double ySpeed;

    private ArrayList<Item> items = new ArrayList<>();

    @Override
    public void onEntityCollision(Entity entity) {
        if (entity.getClass() == Collectible.class) {
            switch (((Collectible) entity).getType()) {
                case YELLOW:
                    yellowStack++;
                    break;
                case GREEN:
                    greenStack++;
                    break;
                case BLACK:
                    blackStack++;
                    break;
                case RED:
                    PV++;
                    break;
            }
        }
        if (entity.getClass() == WorkBench.class) {
            System.out.println("Workbench found\n");
        }
    }

    @Override
    public void onWallCollision(Block block) {

    }

    public void get(Item item) {
        this.items.add(item);
        yellowStack -= item.getCost()[0];
        greenStack -= item.getCost()[1];
        blackStack -= item.getCost()[2];
        item.applyEffect(this);

    }

    public void move(String direction) {

        switch (direction) {
            case "up" :
                yPos -= speed;
                break;
            case "right" :
                xPos += speed;
                break;
            case "down" :
                yPos += speed;
                break;
            case "left" :
                xPos -= speed;
                break;
            default :
                break;
        }

    }

    // getters
    public ArrayList<Item> getItems() {return items;}
    public int getYellowStack() {return yellowStack;}
    public int getGreenStack() {return greenStack;}
    public int getBlackStack() {return blackStack;}

}
