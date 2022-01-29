package GameEngine.Level;

import GameEngine.Entity.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Room {

    private ArrayList<Rock> rocks = new ArrayList<>();
    private ArrayList<WorkBench> workbenches = new ArrayList<>();
    private ArrayList<Recycling> recyclers = new ArrayList<>();
    private ArrayList<Enemy> bosses = new ArrayList<>();
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
                String[] elements = s.split(" ");
                if (elements.length!=1) {
                    switch (Integer.parseInt(elements[0])) {
                        case 1 : {rocks.add(new Rock()); break;}
                        case 2 : {workbenches.add(new WorkBench(new Item[3])); break;}
                        case 3 : {recyclers.add(new Recycling(Integer.parseInt(elements[1]), Integer.parseInt(elements[2]))); break;}
                        case 10 :
                        case 11 :
                        case 12 : {enemies.add(new Enemy(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]))); break;}
                    }
                    System.out.println(elements[0] + " " + elements[1] + " " + elements[2]);
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
    }

    public ArrayList<Door> getExits() {
        return exits;
    }

    public ArrayList<Enemy> getEnemies() {return enemies;}
}
