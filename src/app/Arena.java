package app;

import engine.elements.Area;
import engine.elements.Creature;
import engine.elements.Object;
import engine.enums.CreatureState;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arena extends Area {

    Creature hero;
    Creature enemy;
    Object arrow;
    Image imageZauberer;
    Image imageGanon;
    Image imageArrow;
    Group entityGroup;
    ImageView backgroundNode;
    Image backgroundImage;

    public Arena() {
        super(new Parent() {});
        initScene();
    }

    public Arena(double width, double height) {
        super(new Parent() {}, width, height);
        initScene();
    }

    private void initScene() {
        try {
            imageZauberer = new Image( new FileInputStream("src/resources/gifs/wizard/idle/WizardIdleRight.gif"), 100, 100, false, false);
            imageGanon = new Image( new FileInputStream("src/resources/images/ganon.png"), 100, 100, true, false);
            imageArrow = new Image( new FileInputStream("src/resources/images/arrow.png"), 50, 20, true, false);
            backgroundImage = new Image( new FileInputStream("src/resources/images/arena.png"), 1000, 600, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        entityGroup = new Group();

        hero = new Creature("Wizard", imageZauberer, 100);
        enemy = new Creature("Enemy", imageGanon, 100);
        arrow = new Object("Arrow", imageArrow, 2);
        backgroundNode = new ImageView(backgroundImage);

        entityGroup.getChildren().add(hero);
        entityGroup.getChildren().add(enemy);
        entityGroup.getChildren().add(arrow);
        entityGroup.getChildren().add(backgroundNode);
        backgroundNode.toBack();
        setRoot(entityGroup);
        enemy.relocate(200, 200);
        arrow.relocate(100, 100);

    }

    @Override
    public void keyEventUP(boolean input) {
        hero.setMoveUp(input);
    }

    @Override
    public void keyEventDOWN(boolean input) {
        hero.setMoveDown(input);
    }

    @Override
    public void keyEventLEFT(boolean input) {
        hero.setMoveLeft(input);
    }

    @Override
    public void keyEventRIGHT(boolean input) {
        hero.setMoveRight(input);
    }

    @Override
    public void keyEventSHIFT(boolean input) {
        hero.setRunning(input);
    }

    @Override
    public void keyEventW(boolean input) {
        hero.setMoving(!input);
        if (input) {
            hero.setState(CreatureState.WAITING);
        } else {
            hero.setState(CreatureState.STANDING);
        }
    }

    @Override
    public void keyEventA(boolean input) {
        hero.setMoving(!input);
        if (input) {
            hero.setState(CreatureState.ACTION);
        } else {
            hero.setState(CreatureState.STANDING);
        }
    }
}
