package engine.elements;

import engine.enums.CreatureState;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Creature extends Entity {

    int health;
    long lastHealthUpdate;
    double healthUpdateCooldown;

    CreatureState state;
    CreatureImages images;

    public Creature(String name, Image image, int health) {
        super(name, image);
        this.health = health;
        this.healthUpdateCooldown = 1;
        initCreature(image);
    }

    public Creature(String name, Image image, int health, double healthUpdateCooldown) {
        super(name, image);
        this.health = health;
        this.healthUpdateCooldown = healthUpdateCooldown;
        initCreature(image);
    }

    private void initCreature(Image image) {
        state = CreatureState.STANDING;
        images = new CreatureImages(name, 100, 100, image);
        lastHealthUpdate = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateState();
                updateImages();
            }
        };
        timer.start();
    }

    private void updateState() {
        if (moving) {
            if ((moveRight || moveLeft || moveUp || moveDown)
                    && !(moveLeft && moveRight)
                    && !(moveUp && moveDown)) {
                if (running) {
                    state = CreatureState.RUNNING;
                } else {
                    state = CreatureState.WALKING;
                }
            } else {
                state = CreatureState.STANDING;
            }
        }
    }

    private void updateImages() {
        // only if state or orientation has changed
        if (images.getCurrentImageStateOrientation().getOrientation() != orientation
                || images.getCurrentImageStateOrientation().getState() != state) {
            setImage(images.updateImage(state, orientation));
        }
    }

    @Override
    void collide(Entity entity) {
        if (entity instanceof Object) {
            if (((Object) entity).damage > 0) {
                editHealth(-((Object) entity).damage);
            }
        }
    }

    private void editHealth(double delta) {

        long lastUpdatePlusCooldown = lastHealthUpdate + (int) healthUpdateCooldown * 1000;
        long currentTime = System.currentTimeMillis();

        if (currentTime >= lastUpdatePlusCooldown) {
            health += delta;
            lastHealthUpdate = currentTime;
            System.out.println(health);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public CreatureState getState() {
        return state;
    }

    public void setState(CreatureState state) {
        this.state = state;
    }
}
