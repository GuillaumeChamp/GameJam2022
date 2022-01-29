package GameEngine.Level;

import GameEngine.Entity.Enemy;
import GameEngine.Entity.Entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Room {
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Door> exits;
    private final String fileName = ".//src/Resources/Data/Rooms.csv";

    public Room(int RoomId){
        exits = new ArrayList<>(4);
    }
    public void load(int RoomId){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            for (int i = 0; i < RoomId; i++) {
                line = reader.readLine();
            }
            String[] roomData = line.split(",");
            for (String s : roomData) {
                String[] things = s.split(" ");
                if (things.length!=1) {
                    enemies.add(new Enemy(Integer.parseInt(things[0]),Integer.parseInt(things[1]),Integer.parseInt(things[2])));
                    System.out.println(things[0]+" "+things[1]+" "+things[2]);
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found, can't initialize ");
        } catch (IOException e2) {
            System.out.println("File not found, can't read");
        }
    }
    public void addExit(Door.Position position, Room nextRoom){
        Door door = new Door(position,nextRoom);
        this.exits.add(door);
        this.entities.add(door);
    }

    public ArrayList<Door> getExits() {
        return exits;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Enemy> getEnemies() {return enemies;}
}
