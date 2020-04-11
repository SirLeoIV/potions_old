package engine.elements;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;
import engine.math.EntityDimensions;
import engine.math.TwoPoints2D;
import engine.math.TwoVectors;

import java.util.ArrayList;
import java.util.List;

public abstract class Area extends Scene {

    public Area(Parent root) {
        super(root);
        init();
    }

    public Area(Parent root, double width, double height) {
        super(root, width, height);
        init();
    }

    public Area(Parent root, Paint fill) {
        super(root, fill);
        init();
    }

    public Area(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
        init();
    }

    public Area(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        init();
    }

    public Area(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
        init();
    }

    public void init() {
        keyEvents();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                checkLocations();
            }
        };
        timer.start();
    }

    private void keyEvents() {
        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    keyEventUP(true); break;
                case DOWN:  keyEventDOWN(true); break;
                case LEFT:  keyEventLEFT(true);  break;
                case RIGHT: keyEventRIGHT(true);  break;
                case SHIFT: keyEventSHIFT(true); break;
                case ENTER: keyEventENTER(true); break;
                case SPACE: keyEventSPACE(true); break;
                case A:     keyEventA(true); break;
                case B:     keyEventB(true); break;
                case C:     keyEventC(true); break;
                case D:     keyEventD(true); break;
                case E:     keyEventE(true); break;
                case F:     keyEventF(true); break;
                case G:     keyEventG(true); break;
                case H:     keyEventH(true); break;
                case I:     keyEventI(true); break;
                case J:     keyEventJ(true); break;
                case K:     keyEventK(true); break;
                case L:     keyEventL(true); break;
                case M:     keyEventM(true); break;
                case N:     keyEventN(true); break;
                case O:     keyEventO(true); break;
                case P:     keyEventP(true); break;
                case Q:     keyEventQ(true); break;
                case R:     keyEventR(true); break;
                case S:     keyEventS(true); break;
                case T:     keyEventT(true); break;
                case U:     keyEventU(true); break;
                case V:     keyEventV(true); break;
                case W:     keyEventW(true); break;
                case X:     keyEventX(true); break;
                case Y:     keyEventY(true); break;
                case Z:     keyEventZ(true); break;
            }
        });

        setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    keyEventUP(false); break;
                case DOWN:  keyEventDOWN(false); break;
                case LEFT:  keyEventLEFT(false);  break;
                case RIGHT: keyEventRIGHT(false);  break;
                case SHIFT: keyEventSHIFT(false); break;
                case ENTER: keyEventENTER(false); break;
                case SPACE: keyEventSPACE(false); break;
                case A:     keyEventA(false); break;
                case B:     keyEventB(false); break;
                case C:     keyEventC(false); break;
                case D:     keyEventD(false); break;
                case E:     keyEventE(false); break;
                case F:     keyEventF(false); break;
                case G:     keyEventG(false); break;
                case H:     keyEventH(false); break;
                case I:     keyEventI(false); break;
                case J:     keyEventJ(false); break;
                case K:     keyEventK(false); break;
                case L:     keyEventL(false); break;
                case M:     keyEventM(false); break;
                case N:     keyEventN(false); break;
                case O:     keyEventO(false); break;
                case P:     keyEventP(false); break;
                case Q:     keyEventQ(false); break;
                case R:     keyEventR(false); break;
                case S:     keyEventS(false); break;
                case T:     keyEventT(false); break;
                case U:     keyEventU(false); break;
                case V:     keyEventV(false); break;
                case W:     keyEventW(false); break;
                case X:     keyEventX(false); break;
                case Y:     keyEventY(false); break;
                case Z:     keyEventZ(false); break;

            }
        });
    }

    public void keyEventUP(boolean input) {}
    public void keyEventDOWN(boolean input) {}
    public void keyEventLEFT(boolean input) {}
    public void keyEventRIGHT(boolean input) {}
    public void keyEventSHIFT(boolean input) {}
    public void keyEventENTER(boolean input) {}
    public void keyEventSPACE(boolean input) {}
    public void keyEventA(boolean input) {}
    public void keyEventB(boolean input) {}
    public void keyEventC(boolean input) {}
    public void keyEventD(boolean input) {}
    public void keyEventE(boolean input) {}
    public void keyEventF(boolean input) {}
    public void keyEventG(boolean input) {}
    public void keyEventH(boolean input) {}
    public void keyEventI(boolean input) {}
    public void keyEventJ(boolean input) {}
    public void keyEventK(boolean input) {}
    public void keyEventL(boolean input) {}
    public void keyEventM(boolean input) {}
    public void keyEventN(boolean input) {}
    public void keyEventO(boolean input) {}
    public void keyEventP(boolean input) {}
    public void keyEventQ(boolean input) {}
    public void keyEventR(boolean input) {}
    public void keyEventS(boolean input) {}
    public void keyEventT(boolean input) {}
    public void keyEventU(boolean input) {}
    public void keyEventV(boolean input) {}
    public void keyEventW(boolean input) {}
    public void keyEventX(boolean input) {}
    public void keyEventY(boolean input) {}
    public void keyEventZ(boolean input) {}

    private void checkLocations() {
        List<Node> nodes = getRoot().getChildrenUnmodifiable();
        ArrayList<Entity> entities = new ArrayList<>();
        for (Node node : nodes) {
            if (node instanceof Entity) {
                entities.add((Entity) node);
            }
        }
        entities.forEach(entity1 -> entities.forEach(entity2 -> {
            if (entity1 != entity2) {
                EntityDimensions dimensions1 = new EntityDimensions(entity1);
                EntityDimensions dimensions2 = new EntityDimensions(entity2);
                for (TwoPoints2D twoPoints1 : dimensions1.points()) {
                    for (TwoPoints2D twoPoints2 : dimensions2.points()) {
                        TwoVectors twoVectors = new TwoVectors(twoPoints1, twoPoints2);
                        boolean collision = twoVectors.calculateCollision();
                        if (collision) System.out.println("TRUE");
                    }
                }
            }
        }));
    }
}
