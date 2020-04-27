package app;

import engine.dto.ObjectStarter;
import engine.elements.Area;
import engine.elements.Creature;
import engine.elements.Object;
import engine.elements.styleElements.ProgressElement;
import engine.enums.AttackingTarget;
import engine.enums.CreatureState;
import engine.enums.ObjectOrientation;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arena extends Area {

    Hero hero;
    Image imageZauberer;

    Creature enemy;
    Image imageGanon;

    ObjectStarter arrow;
    Image imageArrow;

    ObjectStarter fireball;
    Image imageFireball;

    ImageView backgroundNode;
    Image backgroundImage;

    ProgressElement healthBar;
    Image healthImage;

    ProgressElement manaBar;
    Image manaImage;

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
            imageFireball = new Image( new FileInputStream("src/resources/gifs/Fireball/FireballLoop.gif"), 100, 100, true, false);
            backgroundImage = new Image( new FileInputStream("src/resources/images/arena.png"), 1000, 600, false, false);
            healthImage = new Image( new FileInputStream("src/resources/images/HealthFlask/HealthFlask10.png"), 100, 100, false, false);
            manaImage = new Image( new FileInputStream("src/resources/images/ManaFlask/ManaFlask10.png"), 100, 100, false, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        healthBar = new ProgressElement("HealthFlask", healthImage, 20, 480, 100, 100, true, 11);
        manaBar = new ProgressElement("ManaFlask", manaImage, 100, 480, 100, 100, true, 11);
        hero = new Hero("Wizard", imageZauberer, 10);
        enemy = new Enemy("Enemy", imageGanon, 10);
        backgroundNode = new ImageView(backgroundImage);

        // ObjectStarter:
        arrow = new ObjectStarter(new Object("Arrow", imageArrow, 1, 2, 2000, AttackingTarget.HERO, ObjectOrientation.RIGHT), 100, 100, ObjectOrientation.RIGHT);
        fireball = new ObjectStarter(new Object("Fireball", imageFireball, 1, 2, 2000, AttackingTarget.ENEMY, ObjectOrientation.RIGHT), 200, 200, ObjectOrientation.RIGHT);

        entityGroup.getChildren().add(hero);
        entityGroup.getChildren().add(enemy);
        entityGroup.getChildren().add(healthBar);
        entityGroup.getChildren().add(manaBar);
        entityGroup.getChildren().add(backgroundNode);
        backgroundNode.toBack();
        setRoot(entityGroup);
        enemy.relocate(200, 200);

        createObject(arrow);
        createObject(fireball);
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
    public void keyEventI(boolean input) {
        if (input) {
            healthBar.increase();
        }
    }

    @Override
    public void keyEventU(boolean input) {
        if (input) {
            healthBar.decrease();
        }
    }


    @Override
    public void keyEventK(boolean input) {
        if (input) {
            manaBar.increase();
        }
    }

    @Override
    public void keyEventJ(boolean input) {
        if (input) {
            manaBar.decrease();
        }
    }

    @Override
    public void keyEventM(boolean input) {
        if (input) {
            events.forEach(System.out::println);
        }
    }

    @Override
    public void keyEventENTER(boolean input) {
        hero.setShooting(input);
    }
}
