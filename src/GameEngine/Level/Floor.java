package GameEngine.Level;

public class Floor {
    Room[][] rooms;

    public Floor(int[] seed){
        rooms = new Room[9][9];
        int[][] floorSeed = {
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,4,0,0,0,0,0,0},
                {0,0,10,0,0,0,0,0,0},
                {0,0,11,12,1,0,0,0,0},
                {0,0,0,0,0,13,14,2,0},
                {0,0,0,0,0,0,3,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}};
        //Todo : use seed
        for (int i=1;i<9;i++){
            for (int j=1;j<9;j++) {
                rooms[i][j] = new Room(floorSeed[i][j]);
                this.addExit(floorSeed,rooms,i,j);
            }
        }
    }
    private void addExit(int[][] seed,Room[][] rooms,int i,int j){
        if (seed[i][j-1]!=0) rooms[i][j].addExit(Door.position.Nord,rooms[i][j-1]);
        if (seed[i][j+1]!=0) rooms[i][j].addExit(Door.position.Sud,rooms[i][j+1]);
        if (seed[i-1][j]!=0) rooms[i][j].addExit(Door.position.Est,rooms[i-1][j]);
        if (seed[i+1][j]!=0) rooms[i][j].addExit(Door.position.Ouest,rooms[i+1][j]);
    }
}
