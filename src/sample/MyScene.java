package sample;

import elements.Area;
import elements.Entity;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyScene extends Area {

    Entity hero;
    Entity enemy;
    Image imageLink;
    Image imageGanon;
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
            imageLink = new Image( new FileInputStream("src/images/link.png"), 100, 100, true, false);
            imageGanon = new Image( new FileInputStream("src/images/ganon.png"), 100, 100, true, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        entityGroup = new Group();
        hero = new Entity("Hero", imageLink);
        enemy = new Entity("Hero", imageGanon);
        entityGroup.getChildren().add(hero);
        entityGroup.getChildren().add(enemy);
        setRoot(entityGroup);
        enemy.relocate(200, 200);
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
