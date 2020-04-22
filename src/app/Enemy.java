package app;

import engine.elements.Creature;
import engine.elements.Entity;
import engine.elements.Object;
import engine.enums.AttackingTarget;
import javafx.scene.image.Image;

public class Enemy extends Creature {

    public Enemy(String name, Image image, int health) {
        super(name, image, health);
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof Object) {
            if (((Object) entity).damage > 0 && ((Object) entity).target == AttackingTarget.ENEMY) {
                editHealth(-((Object) entity).damage);
            }
        }
    }
}
