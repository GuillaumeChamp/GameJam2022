package GameEngine.Entity;

import GameEngine.Physic.Chase;

public class Enemy extends Entity implements Chase {

    private static int wound = 3;

    public int typeNumber;
    public int line;
    public int column;
    private int life = 15;

    public Enemy(int tn, int l, int c) {
        this.typeNumber = tn;
        this.line = l;
        this.column = c;
    }

    @Override
    public void chase() {

    }

    public void getsHurt() throws Exception {
        life -= wound;
        if (life<0) {throw new Exception("IsDead");}
    }

}
