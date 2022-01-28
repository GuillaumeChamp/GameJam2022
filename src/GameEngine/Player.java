package GameEngine;

import GameEngine.Entity.Entity;
import GameEngine.Level.Block;
import GameEngine.Physic.OnWallCollision;

public class Player extends Entity implements OnWallCollision {

    /**
     * Describe how the entity have to react while hitting a wall
     * @param block hit wall
     */
    @Override
    public void onWallCollision(Block block) {

    }
}
