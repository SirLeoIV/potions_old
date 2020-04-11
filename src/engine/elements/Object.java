package engine.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Object extends Entity {

    int damage;

    public Object(String name, Image image, int damage) {
        super(name, image);
        this.damage = damage;

        initObject();
    }

    private void initObject() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {}
        };
        timer.start();
    }

}
