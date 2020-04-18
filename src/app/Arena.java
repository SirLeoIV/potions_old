package app;

import engine.dto.ObjectStarter;
import engine.elements.Area;
import engine.elements.Creature;
import engine.elements.Object;
import engine.enums.CreatureState;
import engine.enums.ObjectOrientation;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arena extends Area {

    Hero hero;
    Creature enemy;
    ObjectStarter arrow;
    ObjectStarter ball;
    Image imageZauberer;
    Image imageGanon;
    Image imageArrow;
    Image imageBall;
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
            imageBall = new Image( new FileInputStream("src/resources/images/ball.jpg"), 50, 20, true, false);
            backgroundImage = new Image( new FileInputStream("src/resources/images/arena.png"), 1000, 600, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        hero = new Hero("Wizard", imageZauberer, 100);
        enemy = new Creature("Enemy", imageGanon, 100);
        backgroundNode = new ImageView(backgroundImage);

        // ObjectStarter:
        arrow = new ObjectStarter(new Object("Arrow", imageArrow, 2, 2, 2000), 100, 100, ObjectOrientation.RIGHT);
        ball = new ObjectStarter(new Object("Ball", imageBall, 2, 2, 2000), 200, 200, ObjectOrientation.RIGHT);

        entityGroup.getChildren().add(hero);
        entityGroup.getChildren().add(enemy);
        entityGroup.getChildren().add(backgroundNode);
        backgroundNode.toBack();
        setRoot(entityGroup);
        enemy.relocate(200, 200);

        createObject(arrow);
        createObject(ball);
    }

    @Override
    public void keyEventW(boolean input) {
        hero.moveUp(input);
    }

    @Override
    public void keyEventS(boolean input) {
        hero.moveDown(input);
    }

    @Override
    public void keyEventA(boolean input) {
        hero.moveLeft(input);
    }

    @Override
    public void keyEventD(boolean input) {
        hero.moveRight(input);
    }

    @Override
    public void keyEventSHIFT(boolean input) {
        hero.setRunning(input);
    }

    @Override
    public void keyEventQ(boolean input) {
        hero.setMoving(!input);
        if (input) {
            hero.setState(CreatureState.WAITING);
        } else {
            hero.setState(CreatureState.STANDING);
        }
    }

    @Override
    public void keyEventE(boolean input) {
        if (input) {
            hero.drink();
        }
    }

    @Override
    public void keyEventENTER(boolean input) {
        if (input && hero.lastShot + hero.shootingCooldown < System.currentTimeMillis()) {
            createObject(hero.shoot());
        }
    }
}
