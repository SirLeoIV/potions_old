package app;

import engine.elements.Area;
import engine.elements.Creature;
import engine.elements.Entity;
import engine.elements.Object;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyScene extends Area {

    Entity hero;
    Entity enemy;
    Entity arrow;
    Image imageLink;
    Image imageGanon;
    Image imageArrow;
    Group entityGroup;

    public MyScene() {
        super(new Parent() {});
        initScene();
    }

    public MyScene(double width, double height) {
        super(new Parent() {}, width, height);
        initScene();
    }

    private void initScene() {
        try {
            imageLink = new Image( new FileInputStream("src/resources/images/link.png"), 100, 100, true, false);
            imageGanon = new Image( new FileInputStream("src/resources/images/ganon.png"), 100, 100, true, false);
            imageArrow = new Image( new FileInputStream("src/resources/images/arrow.png"), 50, 20, true, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entityGroup = new Group();
        hero = new Creature("Hero", imageLink, 100);
        enemy = new Creature("Enemy", imageGanon, 100);
        arrow = new Object("Arrow", imageArrow, 2);
        entityGroup.getChildren().add(hero);
        entityGroup.getChildren().add(enemy);
        entityGroup.getChildren().add(arrow);
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

}
