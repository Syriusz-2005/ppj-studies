package SDF;


import java.awt.geom.Point2D;

public interface Drawable {
    void drawAt(Point2D pos, float occlusion, float distanceFromCamera);
}
