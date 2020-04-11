package engine.dto;

import engine.elements.Entity;

public class Collision {

    Entity entity1;
    Entity entity2;

    public Collision(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }

    public Entity getEntity1() {
        return entity1;
    }

    public void setEntity1(Entity entity1) {
        this.entity1 = entity1;
    }

    public Entity getEntity2() {
        return entity2;
    }

    public void setEntity2(Entity entity2) {
        this.entity2 = entity2;
    }
}
