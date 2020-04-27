package engine.elements;

import engine.enums.AttackingTarget;
import engine.enums.ObjectOrientation;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class Object extends Entity {

    public int damage;
    int speed;
    long birthTime;
    int lifeTime;
    public AttackingTarget target;
    ObjectOrientation orientation;

    Animation startingAnimation;
    Animation endingAnimation;

    public Object(Object object) {
        super(object.name, object.getImage());
        damage = object.damage;
        speed = object.speed;
        lifeTime = object.lifeTime;
        target = object.target;
        startingAnimation = object.startingAnimation;
        endingAnimation = object.endingAnimation;
        orientation = object.orientation;

        initObject();
    }

    public Object(String name, Image image, int damage, int speed, int lifeTime, AttackingTarget target, ObjectOrientation orientation) {
        super(name, image);
        this.damage = damage;
        this.speed = speed;
        this.lifeTime = lifeTime;
        this.target = target;
        this.orientation = orientation;

        initObject();
    }

    private void initObject() {
        birthTime = System.currentTimeMillis();
        try {
            String path = "src/resources/gifs/" + name + "/" + name + "Loop.gif";
            image = new Image( new FileInputStream(path), 100, 100, true, false);
        } catch (Exception e) {
            startingAnimation = null;
        }
        try {
            String animationName = "Init";
            startingAnimation = new Animation(name, animationName, 700, 100, 100);
        } catch (Exception e) {
            startingAnimation = null;
        }
        try {
            String animationName = "Ending";
            endingAnimation = new Animation(name, animationName, 900, 100, 100);
        } catch (Exception e) {
            endingAnimation = null;
        }
        if (orientation == ObjectOrientation.UP) setRotate(-90);
        else if (orientation == ObjectOrientation.DOWN) setRotate(90);
        else if (orientation == ObjectOrientation.LEFT) setScaleX(-1);
        else if (orientation == ObjectOrientation.RIGHT) setScaleX(1);
        else if (orientation == ObjectOrientation.UP_LEFT) setRotate(-135);
        else if (orientation == ObjectOrientation.UP_RIGHT) setRotate(-45);
        else if (orientation == ObjectOrientation.DOWN_LEFT) setRotate(135);
        else if (orientation == ObjectOrientation.DOWN_RIGHT) setRotate(45);
    }

    public void startMoving(ObjectOrientation orientation) {

        int x = orientation.x * speed;
        int y = orientation.y * speed;

        int startingDuration = 0;
        if (startingAnimation != null) {
            setImage(startingAnimation.animation);
            startingDuration = startingAnimation.duration;
        }
        int finalStartingDuration = startingDuration;

        int endingDuration = 0;
        if (endingAnimation != null) {
            endingDuration = endingAnimation.duration;
        }
        int finalEndingDuration = endingDuration;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // first if statement to avoid null-pointer
                if (birthTime + lifeTime > System.currentTimeMillis()) {
                    // only do stuff if starting animation has finished
                    if (birthTime + finalStartingDuration < System.currentTimeMillis()) {
                        setImage(image);
                        // check if ending animation should be played
                        if (birthTime + lifeTime - finalEndingDuration > System.currentTimeMillis()
                                && finalEndingDuration != 0) {
                            setImage(endingAnimation.animation);
                        } else {
                            moveBy(x, y);
                        }
                    }
                }
            }
        };
        timer.start();
    }

}
