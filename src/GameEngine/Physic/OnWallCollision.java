package GameEngine.Physic;

import GameEngine.Level.Block;

public interface OnWallCollision {
    /**
     * Describe how the entity have to react while hitting a wall
     * @param block hit wall
     */
    void onWallCollision(Block block);
}
