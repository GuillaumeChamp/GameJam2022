package GameEngine.Loader;

import GameEngine.Entity.Item;

import java.io.*;
import java.util.*;

public class ItemsLoader {

    private ArrayList<Item> allItems = new ArrayList<>();

    public void fillItems(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine(); // the first line doesn't need to be read
            line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                int ID = Integer.parseInt(data[0]);
                String[] costS = Arrays.copyOfRange(data,2,5);
                int[] cost = {0,0,0};
                cost[0]=Integer.parseInt(costS[0]);
                cost[1]=Integer.parseInt(costS[1]);
                cost[2]=Integer.parseInt(costS[2]);
                String[] effectS = Arrays.copyOfRange(data,5,11);
                double[] effect = {0,0,0,0,0,0};
                effect[0]=Double.parseDouble(effectS[0]);
                effect[1]=Double.parseDouble(effectS[1]);
                effect[2]=Double.parseDouble(effectS[2]);
                effect[3]=Double.parseDouble(effectS[3]);
                effect[4]=Double.parseDouble(effectS[4]);
                effect[5]=Double.parseDouble(effectS[5]);

                if (data.length == 13) {
                    allItems.add(new Item(ID, data[1], cost, effect, data[11], data[12]));
                } else {
                    allItems.add(new Item(ID, data[1], cost, effect, data[11],""));
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found, can't initialize ");
        } catch (IOException e2) {
            System.out.println("File not found, can't read");
        }
    }
}
