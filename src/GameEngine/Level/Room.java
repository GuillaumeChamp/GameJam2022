package GameEngine.Level;

import GameEngine.Entity.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Room {

    private final ArrayList<Rock> rocks = new ArrayList<>();
    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<Door> exits;

    public Room(int RoomId){
        exits = new ArrayList<>(4);
        this.load(RoomId);
    }

    public void load(int RoomId){
        try {
            String fileName = ".//src/Resources/Data/Rooms.csv";
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            for (int i = 0; i < RoomId; i++) {
                line = reader.readLine();
            }
            String[] roomData = line.split(",");
            for (String s : roomData) {
                String[] elements = s.split(" ");
                if (elements.length>1) {
                    switch (Integer.parseInt(elements[0])) {
                        case 1 : {rocks.add(new Rock(Integer.parseInt(elements[2])*1600/13, Integer.parseInt(elements[1])*900/9)); break;}
                        case 2 : {entities.add(new WorkBench(new Item[3],Integer.parseInt(elements[2])*1600/12, Integer.parseInt(elements[1])*900/8)); break;}
                        case 3 : {entities.add(new Recycling(Integer.parseInt(elements[2])*1600/12, Integer.parseInt(elements[1])*900/8)); break;}
                        case 4 :
                        case 10 :
                        case 11 :
                        case 12 : {enemies.add(new Enemy(Integer.parseInt(elements[0]), Integer.parseInt(elements[2]), Integer.parseInt(elements[1]))); break;}
                    }
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

    public ArrayList<Rock> getRocks() {
        return rocks;
    }
}
