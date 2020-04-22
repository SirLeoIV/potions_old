package engine.elements;

import engine.dto.Event;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class StyleNode extends ImageView {

    public String name;
    public Image baseImage;
    int xPosition;
    int yPosition;
    public int width;
    public int height;

    public StyleNode(String name, Image baseImage, int xPosition, int yPosition, int width, int height, boolean visible) {
        this.name = name;
        this.baseImage = baseImage;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;

        setImage(baseImage);
        setVisible(visible);
        relocate(xPosition, yPosition);
    }

    public void handleEvents(ArrayList<Event> events) {}

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

}
