package GameEngine.Physic;

import GameEngine.Entity.Entity;

import java.util.ArrayList;

public interface OnEntityCollision {

    void detectCollision(ArrayList<Entity> entities) throws Exception;
    /**
     * Describe how two entity have to react while hitting each other
     * @param entity hit wall
     */
    void onEntityCollision(Entity entity) throws Exception;
}
