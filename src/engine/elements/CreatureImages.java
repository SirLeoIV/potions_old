package engine.elements;

import engine.dto.CreatureImageStateOrientation;
import engine.enums.CreatureState;
import engine.enums.EntityOrientation;
import javafx.scene.image.Image;

import java.io.FileInputStream;

public class CreatureImages {

    final String standing = "standing";
    final String waiting = "waiting";
    final String walking = "walking";
    final String running = "running";
    final String action = "action";

    final String right = "_right";
    final String left = "_left";
    final String up = "_up";
    final String down = "_down";

    final String gif = ".gif";

    String name;
    int width;
    int height;

    Image baseImage;
    CreatureImageStateOrientation currentImageStateOrientation;

    Image waiting_right;
    Image waiting_left;
    Image waiting_up;
    Image waiting_down;

    Image standing_right;
    Image standing_left;
    Image standing_up;
    Image standing_down;

    Image walking_right;
    Image walking_left;
    Image walking_up;
    Image walking_down;

    Image running_right;
    Image running_left;
    Image running_up;
    Image running_down;

    Image action_right;
    Image action_left;
    Image action_up;
    Image action_down;

    public CreatureImages(String name, int width, int height, Image baseImage) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.baseImage = baseImage;
        currentImageStateOrientation = new CreatureImageStateOrientation(baseImage, CreatureState.STANDING, EntityOrientation.RIGHT);

        String path = "src/resources/gifs/" + name + "/";
        String namePrefix = "/" + name + "_";

        waiting_right = getImage(path + waiting + namePrefix + waiting + right + gif);
        waiting_left = getImage(path + waiting + namePrefix + waiting + left + gif);
        waiting_up = getImage(path + waiting + namePrefix + waiting + up + gif);
        waiting_down = getImage(path + waiting + namePrefix + waiting + down + gif);

        standing_right = getImage(path + standing + namePrefix + standing + right + gif);
        standing_left = getImage(path + standing + namePrefix + standing + left + gif);
        standing_up = getImage(path + standing + namePrefix + standing + up + gif);
        standing_down = getImage(path + standing + namePrefix + standing + down + gif);

        walking_right = getImage(path + walking + namePrefix + walking + right + gif);
        walking_left = getImage(path + walking + namePrefix + walking + left + gif);
        walking_up = getImage(path + walking + namePrefix + walking + up + gif);
        walking_down = getImage(path + walking + namePrefix + walking + down + gif);

        running_right = getImage(path + running + namePrefix + running + right + gif);
        running_left = getImage(path + running + namePrefix + running + left + gif);
        running_up = getImage(path + running + namePrefix + running + up + gif);
        running_down = getImage(path + running + namePrefix + running + down + gif);

        action_right = getImage(path + action + namePrefix + action + right + gif);
        action_left = getImage(path + action + namePrefix + action + left + gif);
        action_up = getImage(path + action + namePrefix + action + up + gif);
        action_down = getImage(path + action + namePrefix + action + down + gif);
    }

    private Image getImage(String path) {
        try {
            return new Image(new FileInputStream(path), width, height, false, false);
        } catch (Exception e1) {
            try {
                path = path.replace("waiting", "standing");
                path = path.replace("walking", "standing");
                path = path.replace("running", "standing");
                path = path.replace("action", "standing");
                return new Image(new FileInputStream(path), width, height, false, false);
            } catch (Exception e2) {
                return baseImage;
            }
        }
    }

    public Image updateImage(CreatureState state, EntityOrientation orientation) {
        Image result = baseImage;

        if (state == CreatureState.WAITING) {
            if (orientation == EntityOrientation.LEFT) result = waiting_left;
            else if (orientation == EntityOrientation.RIGHT) result = waiting_right;
            else if (orientation == EntityOrientation.UP) result = waiting_up;
            else if (orientation == EntityOrientation.DOWN) result = waiting_down;
        }
        else if (state == CreatureState.STANDING) {
            if (orientation == EntityOrientation.LEFT) result = standing_left;
            else if (orientation == EntityOrientation.RIGHT) result = standing_right;
            else if (orientation == EntityOrientation.UP) result = standing_up;
            else if (orientation == EntityOrientation.DOWN) result = standing_down;
        }
        else if (state == CreatureState.WALKING) {
            if (orientation == EntityOrientation.LEFT) result = walking_left;
            else if (orientation == EntityOrientation.RIGHT) result = walking_right;
            else if (orientation == EntityOrientation.UP) result = walking_up;
            else if (orientation == EntityOrientation.DOWN) result = walking_down;
        }
        else if (state == CreatureState.RUNNING) {
            if (orientation == EntityOrientation.LEFT) result = running_left;
            else if (orientation == EntityOrientation.RIGHT) result = running_right;
            else if (orientation == EntityOrientation.UP) result = running_up;
            else if (orientation == EntityOrientation.DOWN) result = running_down;
        }
        else if (state == CreatureState.ACTION) {
            if (orientation == EntityOrientation.LEFT) result = action_left;
            else if (orientation == EntityOrientation.RIGHT) result = action_right;
            else if (orientation == EntityOrientation.UP) result = action_up;
            else if (orientation == EntityOrientation.DOWN) result = action_down;
        }
        currentImageStateOrientation = new CreatureImageStateOrientation(result, state, orientation);
        return result;
    }

    public CreatureImageStateOrientation getCurrentImageStateOrientation() {
        return currentImageStateOrientation;
    }
}
