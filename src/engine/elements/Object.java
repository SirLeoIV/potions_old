package engine.elements;

import engine.enums.ObjectOrientation;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Object extends Entity {

    int damage;
    int speed;
    long birthTime;
    int lifeTime;

    public Object(Object object) {
        super(object.name, object.getImage());
        damage = object.damage;
        speed = object.speed;
        lifeTime = object.lifeTime;

        initObject();
    }

    public Object(String name, Image image, int damage, int speed, int lifeTime) {
        super(name, image);
        this.damage = damage;
        this.speed = speed;
        this.lifeTime = lifeTime;

        initObject();
    }

    private void initObject() {
        birthTime = System.currentTimeMillis();
        System.out.println(birthTime);
    }

    public void startMoving(ObjectOrientation orientation) {
        int x = 0;
        int y = 0;
        if (orientation == ObjectOrientation.LEFT) x -= speed * 3;
        else if (orientation == ObjectOrientation.RIGHT) x += speed * 3;
        else if (orientation == ObjectOrientation.UP) y -= speed * 3;
        else if (orientation == ObjectOrientation.DOWN) y += speed * 3;
        else if (orientation == ObjectOrientation.UP_LEFT) { x -= speed * 2; y -= speed * 2; }
        else if (orientation == ObjectOrientation.UP_RIGHT) { x += speed * 2; y -= speed * 2; }
        else if (orientation == ObjectOrientation.DOWN_LEFT) { x -= speed * 2; y += speed * 2; }
        else if (orientation == ObjectOrientation.DOWN_RIGHT) { x += speed * 2; y += speed * 2; }

        int finalX = x;
        int finalY = y;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // if statement to avoid null-pointer
                if (birthTime + lifeTime > System.currentTimeMillis()) {
                    moveBy(finalX, finalY);
                }
            }
        };
        timer.start();
    }

}
