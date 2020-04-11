package engine.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.sql.Timestamp;

public class Creature extends Entity {

    int health;
    Timestamp lastHealthUpdate;
    double healthUpdateCooldown;

    public Creature(String name, Image image, int health) {
        super(name, image);
        this.health = health;
        this.healthUpdateCooldown = 1;
        initCreature();
    }

    public Creature(String name, Image image, int health, double healthUpdateCooldown) {
        super(name, image);
        this.health = health;
        this.healthUpdateCooldown = healthUpdateCooldown;
        initCreature();
    }

    private void initCreature() {
        lastHealthUpdate = new Timestamp(System.currentTimeMillis());

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {}
        };
        timer.start();

    }

    @Override
    void collide(Entity entity) {
        if (entity instanceof Object) {
            if (((Object) entity).damage > 0) {
                health -= ((Object) entity).damage;
                System.out.println(health);
            }
        }
    }
}
