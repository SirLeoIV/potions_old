package app;

import engine.elements.Creature;
import engine.elements.CreatureAction;
import engine.elements.Entity;
import engine.elements.Object;
import engine.enums.AttackingTarget;
import javafx.scene.image.Image;

public class Hero extends Creature {

    // actions:
    CreatureAction drinking;

    public Hero(String name, Image image, int health) {
        super(name, image, health);
        drinking = new CreatureAction(name, "Action", 100, 100, 2000, image);
    }

    void drink() {
        doAction(drinking);
    }

    @Override
    public void collide(Entity entity) {
        if (entity instanceof Object) {
            if (((Object) entity).damage > 0 && ((Object) entity).target == AttackingTarget.HERO) {
                editHealth(-((Object) entity).damage);
            }
        }
    }

}
