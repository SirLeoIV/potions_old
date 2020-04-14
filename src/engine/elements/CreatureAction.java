package engine.elements;

import engine.enums.EntityOrientation;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class CreatureAction {

    final String right = "Right";
    final String left = "Left";
    final String up = "Up";
    final String down = "Down";

    final String gif = ".gif";

    String name;
    int width;
    int height;

    String creatureName;
    Image baseImage;

    String action_right;
    String action_left;
    String action_up;
    String action_down;

    int timeToExecute;

    public CreatureAction(String creatureName, String name, int width, int height, int timeToExecute, Image baseImage) {
        this.creatureName = creatureName;
        this.name = name;
        this.width = width;
        this.height = height;
        this.timeToExecute = timeToExecute;
        this.baseImage = baseImage;

        String path = "src/resources/gifs/" + creatureName + "/action/";
        String creatureNamePrefix = "/" + creatureName;

        action_right = path + creatureNamePrefix + name + right + gif;
        action_left = path + creatureNamePrefix + name + left + gif;
        action_up = path + creatureNamePrefix + name + up + gif;
        action_down = path + creatureNamePrefix + name + down + gif;
    }

    private Image getImage(String path) {
        try {
            return new Image(new FileInputStream(path), width, height, false, false);
        } catch (Exception e1) {
            e1.printStackTrace();
            return baseImage;
        }
    }

    public Image startAction(EntityOrientation orientation) {
        Image result = baseImage;

        if (orientation == EntityOrientation.LEFT) result = getImage(action_left);
        else if (orientation == EntityOrientation.RIGHT) result = getImage(action_right);
        else if (orientation == EntityOrientation.UP) result = getImage(action_up);
        else if (orientation == EntityOrientation.DOWN) result = getImage(action_down);

        return result;
    }

}
