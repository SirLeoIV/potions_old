package app;

import engine.elements.Creature;
import engine.elements.CreatureAction;
import javafx.scene.image.Image;

public class Hero extends Creature {

    CreatureAction drinking;

    public Hero(String name, Image image, int health) {
        super(name, image, health);
        drinking = new CreatureAction(name, "Action", 100, 100, 2000, image);
    }

    void drink() {
        doAction(drinking);
    }

}
