package engine.elements;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Animation{

    String objectName;
    String animationName;
    public Image animation;
    public int duration;
    private int width;
    private int height;

    public Animation(String objectName, String animationName, Image animation, int duration, int width, int height) {
        this.objectName = objectName;
        this.animationName = animationName;
        this.animation = animation;
        this.duration = duration;
        this.width = width;
        this.height = height;
    }

    public Animation(String objectName, String animationName, int duration, int width, int height) throws FileNotFoundException {
        this.objectName = objectName;
        this.animationName = animationName;
        this.duration = duration;
        this.width = width;
        this.height = height;
        initAnimation();
    }

    void initAnimation() throws FileNotFoundException {
        String path = "src/resources/gifs/" + objectName + "/" + objectName + animationName + ".gif";
        animation = new Image(new FileInputStream(path), width, height, true, false);
    }

}
