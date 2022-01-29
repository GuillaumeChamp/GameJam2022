package GameEngine.Level;

import GameEngine.Entity.Entity;

import java.util.ArrayList;

public class Room {
    ArrayList<Entity> enemies;
    ArrayList<Door> exit;

    public Room(int RoomId){
        exit = new ArrayList<>(4);
    }
    public void load(int RoomId){
        //TODO Cyrielle
        //data = read csv
        //add enemies
        //add exit
    }
    public void addExit(Door.position position,Room nextRoom){
        Door door = new Door(position,nextRoom);
        this.exit.add(door);
    }
}
