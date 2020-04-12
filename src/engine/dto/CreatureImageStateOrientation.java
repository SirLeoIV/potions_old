package engine.dto;

import engine.enums.CreatureState;
import engine.enums.EntityOrientation;
import javafx.scene.image.Image;

public class CreatureImageStateOrientation {

    Image image;
    CreatureState state;
    EntityOrientation orientation;

    public CreatureImageStateOrientation(Image image, CreatureState state, EntityOrientation orientation) {
        this.image = image;
        this.state = state;
        this.orientation = orientation;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public CreatureState getState() {
        return state;
    }

    public void setState(CreatureState state) {
        this.state = state;
    }

    public EntityOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(EntityOrientation orientation) {
        this.orientation = orientation;
    }
}
