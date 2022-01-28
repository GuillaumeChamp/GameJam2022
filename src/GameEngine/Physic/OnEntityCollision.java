package GameEngine.Physic;

import GameEngine.Entity.Entity;

public interface OnEntityCollision {
    /**
     * Describe how two entity have to react while hitting each other
     * @param entity hit wall
     */
    void onEntityCollision(Entity entity);
}
