package app;

import engine.dto.Event;
import engine.elements.Creature;
import engine.elements.CreatureAction;
import engine.elements.Entity;
import engine.elements.Object;
import engine.enums.AttackingTarget;
import engine.enums.EventType;
import javafx.scene.image.Image;

public class Hero extends Creature {

    int mana;
    int maxMana;

    // actions:
    CreatureAction drinking;

    public Hero(String name, Image image, int health, int mana) {
        super(name, image, health);
        this.mana = mana;
        this.maxMana = mana;
        drinking = new CreatureAction(name, "Drinking", 100, 100, 2000, image);
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

    @Override
    public boolean canShoot() {
        return (lastShot + shootingCooldown < System.currentTimeMillis() &&
                mana > 0);
    }

    @Override
    public void doShootingActions() {
        editMana(-1);
    }

    public void editMana(double delta) {
        if (mana + delta <= maxMana
                && mana + delta >= 0) {
            mana += delta;
            System.out.println("Mana: " + mana);
            addEvent(new Event(EventType.MANA, "", mana));
        }
    }
}
