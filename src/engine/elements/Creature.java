package engine.elements;

import engine.enums.CreatureState;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Creature extends Entity {

    int health;
    long lastHealthUpdate;
    double healthUpdateCooldown;

    Image imageWaiting;
    Image imageStanding;
    Image imageGoing;
    Image imageRunning;
    Image imageAttacking;

    CreatureState state;

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
        setImages(image, image, image, image, image);
        lastHealthUpdate = System.currentTimeMillis();
        state = CreatureState.STANDING;

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
            if (moveRight || moveLeft || moveUp || moveDown) {
                state = CreatureState.GOING;
                if (running) {
                    state = CreatureState.RUNNING;
                }
            } else {
                state = CreatureState.STANDING;
            }
        }
    }

    private void updateImages() {
        if (state == CreatureState.WAITING) {
            setImage(imageWaiting);
        }
        if (state == CreatureState.STANDING) {
            setImage(imageStanding);
        }
        if (state == CreatureState. GOING) {
            setImage(imageGoing);
        }
        if (state == CreatureState.RUNNING) {
            setImage(imageRunning);
        }
        if (state == CreatureState.ATTACKING) {
            setImage(imageAttacking);
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

    public void setImages(
        Image imageWaiting,
        Image imageStanding,
        Image imageGoing,
        Image imageRunning,
        Image imageAttacking) {
        this.imageWaiting = imageWaiting;
        this.imageStanding = imageStanding;
        this.imageGoing = imageGoing;
        this.imageRunning = imageRunning;
        this.imageAttacking = imageAttacking;
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
