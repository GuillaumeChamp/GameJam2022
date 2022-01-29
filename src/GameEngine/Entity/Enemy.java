package GameEngine.Entity;

import GameEngine.Physic.Chase;

public class Enemy implements Chase {

    public int typeNumber;
    public int line;
    public int column;

    public Enemy(int tn, int l, int c) {
        this.typeNumber = tn;
        this.line = l;
        this.column = c;
    }

    @Override
    public void chase() {

    }

}
