package engine.math;

import engine.dto.Point2D;
import engine.elements.Entity;
import javafx.geometry.Bounds;

public class EntityDimensions {

    public Point2D a;
    public Point2D b;
    public Point2D c;
    public Point2D d;

    public EntityDimensions(Entity entity) {
        Bounds bounds = entity.getBoundsInParent();
        this.a = new Point2D(bounds.getMinX(), bounds.getMinY());
        this.b = new Point2D(bounds.getMaxX(), bounds.getMinY());
        this.c = new Point2D(bounds.getMaxX(), bounds.getMaxY());
        this.d = new Point2D(bounds.getMinX(), bounds.getMaxY());
    }

    public EntityDimensions() {
        a = new Point2D(0, 0);
        b = new Point2D(1, 0);
        c = new Point2D(1, 1);
        d = new Point2D(0, 1);
    }

    public Point2D[] points() {
        return new Point2D[]{a, b, c, d};
    }

    public boolean calculateCollision(EntityDimensions entity) {
        boolean collision = false;
        for (Point2D point : points()) {
            if (point.getX() > entity.a.getX()
                    && point.getX() < entity.b.getX()
                    && point.getY() > entity.a.getY()
                    && point.getY() < entity.d.getY()) {
                collision = true;
                break;
            }
        }
        return collision;
    }
}
