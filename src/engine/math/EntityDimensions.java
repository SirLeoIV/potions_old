package engine.math;

import engine.dto.Point2D;
import engine.dto.TwoPoints2D;
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

    public TwoPoints2D[] points() {
        return new TwoPoints2D[]{
                new TwoPoints2D(a, b),
                new TwoPoints2D(b, c),
                new TwoPoints2D(c, d),
                new TwoPoints2D(d, a)
        };
    }
}
