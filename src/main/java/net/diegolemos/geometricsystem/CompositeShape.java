package net.diegolemos.geometricsystem;

import java.util.LinkedList;
import java.util.List;

public class CompositeShape implements Shape {
    private List<ShapeWithRelativeCoordinates> shapes = new LinkedList<>();

    void add(int topX, int topY, Shape shape) {
        shapes.add(new ShapeWithRelativeCoordinates(topX, topY, shape));
    }

    public boolean shouldTurnOn(int x, int y) {
        for (ShapeWithRelativeCoordinates component : shapes) {
            int relativeX = x - component.topX;
            int relativeY = y - component.topY;

            if (component.shape instanceof Circle) {
                if (((Circle) component.shape).isPixelActivated(relativeX, relativeY)) return true;
            }

            if (component.shape instanceof Rectangle) {
                if (((Rectangle) component.shape).isPixelOn(relativeX, relativeY)) return true;
            }

            if (component.shape instanceof CompositeShape) {
                if (((CompositeShape) component.shape).shouldTurnOn(relativeX, relativeY)) return true;
            }
        }

        return false;
    }

    private static final class ShapeWithRelativeCoordinates {
        private final int topX;
        private final int topY;
        private final Shape shape;

        private ShapeWithRelativeCoordinates(int topX, int topY, Shape shape) {
            this.topX = topX;
            this.topY = topY;
            this.shape = shape;
        }
    }
}
