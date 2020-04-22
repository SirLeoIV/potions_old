package engine.elements.styleElements;

import engine.dto.Event;
import engine.elements.StyleNode;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ProgressElement extends StyleNode {

    int steps;
    int currentStep;
    ArrayList<Image> images;

    public ProgressElement(String name, Image baseImage, int xPosition, int yPosition, int width, int height, boolean visible, int steps) {
        super(name, baseImage, xPosition, yPosition, width, height, visible);
        this.steps = steps;
        currentStep = steps - 1;

        initImages();
    }

    void initImages() {
        String path = "src/resources/images/" + name + "/" + name;
        images = new ArrayList<>();
        while (images.size() < steps) {
            images.add(getImage(path + images.size() + ".png"));
        }
        updateImage();
    }

    private Image getImage(String path) {
        try {
            return new Image(new FileInputStream(path), width, height, true, false);
        } catch (Exception e1) {
            e1.printStackTrace();
            return baseImage;
        }
    }

    void updateImage() {
        setImage(images.get(currentStep));
    }

    public void increase() {
        this.currentStep += 1;
        if (currentStep > steps - 1) currentStep = steps - 1;
        updateImage();
    }

    public void decrease() {
        this.currentStep -= 1;
        if (currentStep < 0) currentStep = 0;
        updateImage();
    }

    void updateCurrentStep(int value) {
        currentStep = value;
        if (currentStep > steps - 1) currentStep = steps - 1;
        if (currentStep < 0) currentStep = 0;
        updateImage();
    }

    @Override
    public void handleEvents(ArrayList<Event> events) {
        ArrayList<Event> usedEvents = new ArrayList<>();
        events.forEach(event -> {
            if (event.getType().getTargetName().equals(name)) {
                updateCurrentStep(event.getValue());
                usedEvents.add(event);
            }
        });
        for (Event event : usedEvents) {
            events.remove(event);
        }
    }
}
