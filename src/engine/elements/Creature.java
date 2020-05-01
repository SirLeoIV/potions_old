package engine.elements;

import app.Arena;
import app.Hero;
import engine.dto.Event;
import engine.dto.ObjectStarter;
import engine.enums.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Creature extends Entity {

    int health;
    int maxHealth;
    long lastHealthUpdate;
    double healthUpdateCooldown;

    boolean inAction;
    long lastActionStart;
    int actionDuration;

    // shooting stuff:
    Image imageFireball;
    boolean shooting;
    public long lastShot;
    public int shootingCooldown;

    CreatureState state;
    CreatureImages images;

    public Creature(String name, Image image, int health) {
        super(name, image);
        this.health = health;
        this.maxHealth = health;
        this.healthUpdateCooldown = 1;
        initCreature(image);
    }

    public Creature(String name, Image image, int health, double healthUpdateCooldown) {
        super(name, image);
        this.health = health;
        this.maxHealth = health;
        this.healthUpdateCooldown = healthUpdateCooldown;
        initCreature(image);
    }

    private void initCreature(Image image) {
        state = CreatureState.STANDING;
        images = new CreatureImages(name, 100, 100, image);
        lastHealthUpdate = System.currentTimeMillis();

        try {
            imageFireball = new Image( new FileInputStream("src/resources/gifs/Fireball/FireballLoop.gif"), 100, 100, true, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        shootingCooldown = 500;
        lastShot = System.currentTimeMillis() - shootingCooldown;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateInAction();
                if (!inAction) {
                    updateState();
                    updateImages();
                    if (shooting) shoot();
                }
            }
        };
        timer.start();
    }

    private void updateState() {
        if (moving && !inAction) {
            if ((moveRight || moveLeft || moveUp || moveDown)
                    && !(moveLeft && moveRight && !moveUp && !moveDown)
                    && !(moveUp && moveDown && !moveRight && !moveLeft)) {
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

    public void editHealth(double delta) {

        long lastUpdatePlusCooldown = lastHealthUpdate + (int) healthUpdateCooldown * 1000;
        long currentTime = System.currentTimeMillis();

        if (currentTime >= lastUpdatePlusCooldown
                && health + delta >= 0
                && health + delta <= maxHealth) {
            health += delta;
            lastHealthUpdate = currentTime;
            System.out.println(health);
            addEvent(new Event(EventType.HEALTH, "", getHealth()));
        }
    }

    public void addEvent(Event event) {
        Scene scene = getParent().getScene();
        Area area = (Area) scene;
        area.events.add(event);
    }

    public void doAction(CreatureAction action) {
        if (!inAction) {
            setState(CreatureState.ACTION);
            inAction = true;
            lastActionStart = System.currentTimeMillis();
            actionDuration = action.timeToExecute;
            setMoving(false);
            setImage(action.startAction(orientation));
        }
    }

    void updateInAction() {
        if (inAction) {
            // if action is done, change image back to old one
            if (!(lastActionStart + actionDuration > System.currentTimeMillis())) {
                setMoving(true);
                setState(CreatureState.STANDING);
                setImage(images.updateImage(state, orientation));
            }
            inAction = lastActionStart + actionDuration > System.currentTimeMillis();
        }
    }

    public void shoot() {
        if (!canShoot()) return;
        doShootingActions();
        lastShot = System.currentTimeMillis();

        int startingX = (int) (getBoundsInParent().getMinX() + getBoundsInParent().getMaxX()) / 2;
        int startingY = (int) (getBoundsInParent().getMinY() + getBoundsInParent().getMaxY()) / 2;
        ObjectOrientation orientation = ObjectOrientation.RIGHT;

        // the unnessecary conditons are left in for a better overview
        if (moveRight && !moveLeft && !moveUp && !moveDown) orientation = ObjectOrientation.RIGHT;
        else if (!moveRight && moveLeft && !moveUp && !moveDown) orientation = ObjectOrientation.LEFT;
        else if (!moveRight && !moveLeft && moveUp && !moveDown) orientation = ObjectOrientation.UP;
        else if (!moveRight && !moveLeft && !moveUp && moveDown) orientation = ObjectOrientation.DOWN;

        else if (moveRight && !moveLeft && moveUp && !moveDown) orientation = ObjectOrientation.UP_RIGHT;
        else if (moveRight && !moveLeft && !moveUp && moveDown) orientation = ObjectOrientation.DOWN_RIGHT;
        else if (!moveRight && moveLeft && moveUp && !moveDown) orientation = ObjectOrientation.UP_LEFT;
        else if (!moveRight && moveLeft && !moveUp && moveDown) orientation = ObjectOrientation.DOWN_LEFT;

        // three keys are pressed
        else if (moveRight && moveLeft && moveUp && !moveDown) orientation = ObjectOrientation.UP;
        else if (moveRight && moveLeft && !moveUp && moveDown) orientation = ObjectOrientation.DOWN;
        else if (moveRight && !moveLeft && moveUp && moveDown) orientation = ObjectOrientation.RIGHT;
        else if (!moveRight && moveLeft && moveUp && moveDown) orientation = ObjectOrientation.LEFT;

        // else -> the orientation of the Creature is used
        else {
            if (getOrientation() == EntityOrientation.RIGHT) orientation = ObjectOrientation.RIGHT;
            else if (getOrientation() == EntityOrientation.LEFT) orientation = ObjectOrientation.LEFT;
            else if (getOrientation() == EntityOrientation.UP) orientation = ObjectOrientation.UP;
            else if (getOrientation() == EntityOrientation.DOWN) orientation = ObjectOrientation.DOWN;
        }

        AttackingTarget target = AttackingTarget.HERO;
        if (this instanceof Hero) {
            target = AttackingTarget.ENEMY;
        }

        System.out.println(target.name());
        Object object = new Object("Fireball", imageFireball, 1, 2, 2000, target, orientation);
        startingX -= object.getBoundsInLocal().getWidth() / 2;
        startingY -= object.getBoundsInLocal().getHeight() / 2;
        createObjectInArena(new ObjectStarter(object, startingX, startingY, orientation));
        doAction(new CreatureAction(name, "Talking", 100, 100, 700, image));
    }

    public void doShootingActions() {}

    public boolean canShoot() {
        return lastShot + shootingCooldown > System.currentTimeMillis();
    }

    void createObjectInArena(ObjectStarter starter) {
        Scene scene = getParent().getScene();
        Arena arena = (Arena) scene;
        arena.createObject(starter);
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

    public void moveLeft(boolean input) {
        if (!inAction || !input) {
            setMoveLeft(input);
        }
    }

    public void moveRight(boolean input) {
        if (!inAction || !input) {
            setMoveRight(input);
        }
    }

    public void moveUp(boolean input) {
        if (!inAction || !input) {
            setMoveUp(input);
        }
    }

    public void moveDown(boolean input) {
        if (!inAction || !input) {
            setMoveDown(input);
        }
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}
