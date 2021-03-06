package GameEngine;

import GUI.AnimatedImage;
import GameEngine.Entity.*;
import GameEngine.Level.*;
import GameEngine.Level.Door;
import GameEngine.Level.Floor;
import GameEngine.Level.Room;
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

    private final AnimatedImage idle = new AnimatedImage("Resources/Sprites/albear.png",4,300,300);
    private final AnimatedImage up = new AnimatedImage("Resources/Sprites/albear.png",1,300,300); //300*300
    private final AnimatedImage down = new AnimatedImage("Resources/Sprites/albear.png",0,300,300);
    private final AnimatedImage right = new AnimatedImage("Resources/Sprites/albear.png",2,300,300);
    private final AnimatedImage left = new AnimatedImage("Resources/Sprites/albear.png",3,300,300);

    private final ArrayList<Item> items = new ArrayList<>();

    public boolean displayWorkBench = false;

    public Player() {
        xPos = Constant.ROOMSWITHD/2;
        yPos = Constant.ROOMHEIGHT*3/4;
        width = 80;
        height = 80;
        this.skin=idle;
    }

    @Override
    public void detectCollision(ArrayList<Entity> entities) throws Exception {
        Rectangle2D playerHitbox = new Rectangle2D(xPos,yPos,width,height);
        for (Entity e: entities) {
            Rectangle2D EntityHitbox = new Rectangle2D(e.getxPos(),e.getyPos(),e.getWidth(),e.getHeight());
            if (EntityHitbox.intersects(playerHitbox)) onEntityCollision(e);
            if (e.getClass()==WorkBench.class) {displayWorkBench=true;}
        }
    }

    @Override
    public void onEntityCollision(Entity entity) throws Exception {
        if (entity.getClass() == Collectible.class) {
            switch (((Collectible) entity).getType()) {
                case YELLOW -> yellowStack++;
                case GREEN -> greenStack++;
                case BLACK -> blackStack++;
                case RED -> PV++;
            }
            this.currentRoom.getEntities().remove(entity);
        }
        if (entity.getClass() == WorkBench.class) {
            System.out.println("Workbench found\n");
        }
        if (entity.getClass() == Door.class) {
            changeRoom((Door) entity);
        }
        if (entity.getClass() == Enemy.class) {
            this.takeDamage();
        }
    }

    private void takeDamage() throws Exception {
        this.PV = this.PV - 0.5;
        if (this.PV<=0) throw new Exception("Game Over");
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
        int speedFactor=20;
        //todo : make a smooth acceleration
        double oldX = xPos;
        double oldY = yPos;
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
        for (Rock r: this.currentRoom.getRocks()) {
            Rectangle2D playerHitbox = new Rectangle2D(xPos,yPos,width,height);
            Rectangle2D rockArea = new Rectangle2D(r.getxPos(),r.getyPos(),r.getWidth(),r.getHeight());
            if (playerHitbox.intersects(rockArea)){
                this.yPos = oldY;
                this.xPos = oldX;
                break;
            }
        }

        if(yPos<=5) yPos=5;
        if(yPos>=Constant.ROOMHEIGHT) yPos=Constant.ROOMHEIGHT;
        if(xPos<=5) xPos=5;
        if(xPos>=Constant.ROOMSWITHD) xPos=Constant.ROOMSWITHD;
    }

    public void attack(){
        double boxSide = range*10;
        Rectangle2D hitbox = new Rectangle2D(0,0,1,1);
        if (skin.equals(up))
                hitbox = new Rectangle2D(this.xPos-50, this.yPos-50, 150, 50);
        if (skin.equals(right))
                hitbox = new Rectangle2D(this.xPos+50, this.yPos-50, 50, 150);
        if (skin.equals(down))
                hitbox = new Rectangle2D(this.xPos-50, this.yPos+50, 150, 50);
        if (skin.equals(left))
                hitbox = new Rectangle2D(this.xPos-50, this.yPos-50, 50, 150);
        for (Enemy e : currentRoom.getEnemies()) {
            try{
                if (hitbox.intersects(e.getxPos(),e.getyPos(),e.getWidth(),e.getHeight())) {
                    e.getsHurt(this.attack);
                }
            } catch (Exception exc) {
                currentRoom.getEnemies().remove(e);
                Collectible.Type type = e.getType();
                currentRoom.getEntities().add(new Collectible(type,e.getxPos(),e.getyPos(),e.getHeight(),e.getWidth()));
                Constant.deathSound.play();
            }
        }
    }

    public void changeRoom(Door door) {
        this.currentRoom = door.getNextRoom();
        switch (door.getPosition()){
            case Nord ->
                yPos = Constant.ROOMHEIGHT-this.height;
            case Sud ->
                yPos = 0+this.height;
            case Est ->
                xPos = 0+this.width;
            case Ouest ->
                xPos = Constant.ROOMSWITHD-this.width;
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
