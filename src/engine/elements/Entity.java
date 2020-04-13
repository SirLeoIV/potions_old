package engine.elements;

import engine.enums.EntityOrientation;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {

    String name;

    boolean moving;
    boolean running;
    boolean moveUp;
    boolean moveDown;
    boolean moveRight;
    boolean moveLeft;

    EntityOrientation orientation;

    public Entity(String name, Image image) {
        this.name = name;
        setImage(image);
        moving = true;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                changeOrientation();
                if (moving) {
                    int delta = 1;
                    if (running) delta *= 3;

                    int x = 0;
                    int y = 0;

                    if (moveLeft)  x -= delta;
                    if (moveRight)  x += delta;
                    if (moveUp) y -= delta;
                    if (moveDown) y += delta;

                    // 2x to enable 'sliding' on the edge
                    moveBy(x, 0);
                    moveBy(0, y);
                }
            }
        };
        timer.start();
    }

    private void moveBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = getBoundsInLocal().getWidth()  / 2;
        final double cy = getBoundsInLocal().getHeight() / 2;

        double x = cx + getLayoutX() + dx;
        double y = cy + getLayoutY() + dy;

        moveTo(x, y);
    }

    private void moveTo(double x, double y) {
        final double cx = getBoundsInLocal().getWidth()  / 2;
        final double cy = getBoundsInLocal().getHeight() / 2;

        if (isMovementValid(x, y)) {
            relocate(x - cx, y - cy);
        }
    }

    /* Checks, when the engine.elements.Entity is moving (towards) outside the valid area,
     if the next position in closer to the edge then the current one,
     so the entity is always moving towards the valid area */
    private boolean isMovementValid(double x, double y) {
        boolean result = true;
        double minX = 0.0;
        double maxX = getScene().getWidth();
        double minY = 0.0;
        double maxY = getScene().getHeight();

        double nowMinX = getBoundsInParent().getMinX();
        double nowMaxX = getBoundsInParent().getMaxX();
        double nowMinY = getBoundsInParent().getMinY();
        double nowMaxY = getBoundsInParent().getMaxY();

        double thenMinX = x - getBoundsInLocal().getWidth()  / 2;
        double thenMaxX = x + getBoundsInLocal().getWidth()  / 2;
        double thenMinY = y - getBoundsInLocal().getHeight() / 2;
        double thenMaxY = y + getBoundsInLocal().getHeight() / 2;

        boolean outOfSceneLeft = thenMinX < minX;
        boolean outOfSceneRight = thenMaxX > maxX;
        boolean outOfSceneTop = thenMinY < minY;
        boolean outOfSceneBottom = thenMaxY > maxY;

        /*  Monitoring: */
//        System.out.println();
//        System.out.println("Now : " +
//                nowMinX + " : " + nowOutOfSceneLeft + " : " +
//                nowMaxX + " : " + nowOutOfSceneRight + " : " +
//                nowMinY + " : " + nowOutOfSceneTop + " : " +
//                nowMaxY + " : " + nowOutOfSceneBottom);
//        System.out.println("Then: " +
//                thenMinX + " : " + thenOutOfSceneLeft + " : " +
//                thenMaxX + " : " + thenOutOfSceneRight + " : " +
//                thenMinY + " : " + thenOutOfSceneTop + " : " +
//                thenMaxY + " : " + thenOutOfSceneBottom);

        if (outOfSceneLeft && thenMinX < nowMinX) result = false;
        if (outOfSceneRight && thenMaxX > nowMaxX) result = false;
        if (outOfSceneTop && thenMinY < nowMinY) result = false;
        if (outOfSceneBottom && thenMaxY > nowMaxY) result = false;

        return result;
    }

    void collide(Entity entity) {}

    void changeOrientation() {
        // standart-cases
        if (moveUp && !moveDown && !moveLeft && !moveRight && (orientation != EntityOrientation.UP)) setOrientation(EntityOrientation.UP);
        else if (!moveUp && moveDown && !moveLeft && !moveRight && (orientation != EntityOrientation.DOWN)) setOrientation(EntityOrientation.DOWN);
        else if (!moveUp && !moveDown && moveLeft && !moveRight && (orientation != EntityOrientation.LEFT)) setOrientation(EntityOrientation.LEFT);
        else if (!moveUp && !moveDown && !moveLeft && moveRight && (orientation != EntityOrientation.RIGHT)) setOrientation(EntityOrientation.RIGHT);

        // special-cases
        else if (moveUp && !moveDown && moveLeft && moveRight && (orientation != EntityOrientation.UP)) setOrientation(EntityOrientation.UP);
        else if (!moveUp && moveDown && moveLeft && moveRight && (orientation != EntityOrientation.DOWN)) setOrientation(EntityOrientation.DOWN);
        else if (moveUp && moveDown && moveLeft && !moveRight && (orientation != EntityOrientation.LEFT)) setOrientation(EntityOrientation.LEFT);
        else if (moveUp && moveDown && !moveLeft && moveRight && (orientation != EntityOrientation.RIGHT)) setOrientation(EntityOrientation.RIGHT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public EntityOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(EntityOrientation orientation) {
        System.out.println(orientation.name());
        this.orientation = orientation;
    }
}
