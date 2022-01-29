package GameEngine.Level;

import java.util.Random;

public class Floor {
    Room[][] rooms;
    Random r = new Random();

    public Floor(){
        rooms = new Room[9][9];
        int[][] floorArray = {
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}};
        int[][] floorSeed = createSeed();

        floorArray[floorSeed[0][0]][floorSeed[0][1]] = 2;
        floorArray[floorSeed[1][0]][floorSeed[1][1]] = 3;
        floorArray[floorSeed[2][0]][floorSeed[2][1]] = 4;

        pathfinding(floorArray,floorSeed[0]);
        pathfinding(floorArray,floorSeed[1]);
        pathfinding(floorArray,floorSeed[2]);
        //Array généré

        for (int i=1;i<9;i++){
            for (int j=1;j<9;j++) {
                rooms[i][j] = new Room(floorArray[i][j]);
                this.addExit(floorArray,rooms,i,j);
            }
        }
    }

    private void addExit(int[][] seed,Room[][] rooms,int i,int j){
        if (seed[i][j-1]!=0) rooms[i][j].addExit(Door.position.Nord,rooms[i][j-1]);
        if (seed[i][j+1]!=0) rooms[i][j].addExit(Door.position.Sud,rooms[i][j+1]);
        if (seed[i-1][j]!=0) rooms[i][j].addExit(Door.position.Est,rooms[i-1][j]);
        if (seed[i+1][j]!=0) rooms[i][j].addExit(Door.position.Ouest,rooms[i+1][j]);
    }

    private void pathfinding(int[][] floor, int[] salle) {
        int[] off = {4 - salle[0], 4 - salle[1]};
        int i;
        while ((off[0] != 0 || off[1] != 0)) {
            i = r.nextInt(2);
            if (off[i] > 0) {
                off[i]--;
            }
            if (off[i] < 0) {
                off[i]++;
            }
            if (floor[4 - off[0]][4 - off[1]] > 0 && floor[4 - off[0]][4 - off[1]] < 10) {
                if (off[i] > 0) {
                    off[i]++;
                }
                if (off[i] < 0) {
                    off[i]--;
                }
            } else {
                floor[4 - off[0]][4 - off[1]] = (r.nextInt(5)) + 10;
            }
        }
    }

    private int[][] createSeed(){
        int[][] seed = {{0,0},{0,0},{0,0}};
        int j;
        while ((seed[0][0] == seed[1][0] && seed[0][1] == seed[1][1])
                || (seed[1][0] == seed[2][0] && seed[1][1] == seed[2][1])
                || (seed[0][0] == seed[2][0] && seed[0][1] == seed[2][1])){
            for (int i = 0; i < 3; i++){
                j = (r.nextInt(7)+1);
                seed[i][0] = j;
                if (seed[i][0]==1 || seed[i][0]==7){
                    j = (r.nextInt(7)+1);
                }
                else{
                    j = ((r.nextInt(2))*6)+1;
                }
                seed[i][1] = j;
            }
        }
        return seed;
    }
}
