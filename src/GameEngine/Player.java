package GameEngine;

import GUI.AnimatedImage;
import GUI.GameScene;
import GameEngine.Entity.*;
import GameEngine.Level.*;
import GameEngine.Physic.OnEntityCollision;
import GameEngine.Physic.OnWallCollision;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class Player extends Entity implements OnWallCollision, OnEntityCollision {

    public Floor currentFloor = new Floor(new int[]{0});
    public Room currentRoom = currentFloor.getRooms()[4][4];

    public double PV = 3;
    public double attack = 3;
    public double speed = 1;
    public double range = 3;
    public double attackSpeed = 1; // per second

    private int yellowStack = 50;
    private int greenStack = 50;
    private int blackStack = 50;

    private AnimatedImage idle = new AnimatedImage("Resources/Sprites/albear.png",4,300,300);
    private AnimatedImage up = new AnimatedImage("Resources/Sprites/albear.png",1,300,300); //300*300
    private AnimatedImage down = new AnimatedImage("Resources/Sprites/albear.png",0,300,300);
    private AnimatedImage right = new AnimatedImage("Resources/Sprites/albear.png",2,300,300);
    private AnimatedImage left = new AnimatedImage("Resources/Sprites/albear.png",3,300,300);

    private ArrayList<Item> items = new ArrayList<>();

    public Player() {
        xPos = 50;
        yPos = 50;
        width = 70;
        height = 70;
        this.skin=idle;
    }

    @Override
    public void onEntityCollision(Entity entity) {
        if (entity.getClass() == Collectible.class) {
            switch (((Collectible) entity).getType()) {
                case YELLOW -> yellowStack++;
                case GREEN -> greenStack++;
                case BLACK -> blackStack++;
                case RED -> PV++;
            }
        }
        if (entity.getClass() == WorkBench.class) {
            System.out.println("Workbench found\n");
        }
        if (entity.getClass() == Door.class) {
            changeRoom((Door) entity);
        }
        if (entity.getClass() == Enemy.class) {
            // TODO : degats sur le player
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
        int speedFactor=30;
        //todo : make a smooth acceleration
        switch (direction) {
            case "up" -> {
                yPos -= speed * speedFactor;
                skin = up;
            }
            case "right" -> {
                xPos += speed * speedFactor;
                skin = right;
            }
            case "down" -> {
                yPos += speed * speedFactor;
                skin = down;
            }
            case "left" -> {
                xPos -= speed * speedFactor;
                skin = left;
            }
            default -> skin = idle;
        }

    }

    public void hit(String direction){
        //int boxSide = 50;
        Rectangle2D hitbox = new Rectangle2D(0,0,1,1);
        switch (direction) {
            case "up" -> {
                hitbox = new Rectangle2D(this.xPos-50, this.yPos-50, 150, 50);
            }
            case "right" -> {
                hitbox = new Rectangle2D(this.xPos+50, this.yPos+50, 50, 150);
            }
            case "down" -> {
                hitbox = new Rectangle2D(this.xPos-50, this.yPos+50, 150, 50);
            }
            case "left" -> {
                hitbox = new Rectangle2D(this.xPos+50, this.yPos-50, 50, 150);
            }
        }
        for (Enemy e : currentRoom.getEnemies()) {
            try{
                if (hitbox.intersects(e.getxPos(),e.getyPos(),e.getWidth(),e.getHeight())) {
                    e.getsHurt();
                }
            } catch (Exception exc) {
                System.out.println("Enemy dead");
                currentRoom.getEnemies().remove(e);
            }
        }
    }

    public void changeRoom(Door door) {
        this.currentRoom = door.getNextRoom();
        switch (door.getPosition()){
            case Nord -> {
                xPos = (GameScene.width-this.width)/2;
                yPos = GameScene.height-this.height;
            }
            case Sud -> {
                xPos = (GameScene.width-this.width)/2;
                yPos = 0;
            }
            case Est -> {
                xPos = 0;
                yPos = (GameScene.height-this.height)/2;
            }
            case Ouest -> {
                xPos = GameScene.width-this.width;
                yPos = (GameScene.height-this.height)/2;
            }
        }
    }

    // getters
    public ArrayList<Item> getItems() {return items;}
    public int getYellowStack() {return yellowStack;}
    public int getGreenStack() {return greenStack;}
    public int getBlackStack() {return blackStack;}
    public AnimatedImage getSkin() {return skin;}

    // setters
    public void setYellowStack(int ys) {yellowStack=ys;}
    public void setGreenStack(int gs) {greenStack=gs;}
    public void setBlackStack(int bs) {blackStack=bs;}
}
