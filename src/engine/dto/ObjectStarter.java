package engine.dto;

import engine.elements.Object;
import engine.enums.ObjectOrientation;

public class ObjectStarter {

    Object object;
    int startingX;
    int startingY;
    ObjectOrientation orientation;

    public ObjectStarter(Object object, int startingX, int startingY, ObjectOrientation orientation) {
        this.object = object;
        this.startingX = startingX;
        this.startingY = startingY;
        this.orientation = orientation;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getStartingX() {
        return startingX;
    }

    public void setStartingX(int startingX) {
        this.startingX = startingX;
    }

    public int getStartingY() {
        return startingY;
    }

    public void setStartingY(int startingY) {
        this.startingY = startingY;
    }

    public ObjectOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(ObjectOrientation orientation) {
        this.orientation = orientation;
    }
}
