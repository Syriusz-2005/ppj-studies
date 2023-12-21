package SDF;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class ScreenDrawer implements Drawable {
    private JFrame frame;

    public ScreenDrawer(int windowX, int windowY) {
        JPanel pane = new JPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(windowX, windowY);
        frame.setVisible(true);
        frame.add(pane);
        this.frame = frame;
    }



    @Override
    public void drawAt(Point2D pos, float occlusion, float distanceFromCamera) {
        var graphics = frame.getGraphics();
        int colorValue = 0;
        if (distanceFromCamera < 40) {
            colorValue = (int) (255. * (occlusion * 0.5 + .5));
        }
        graphics.setColor(new Color(colorValue, colorValue, colorValue));
        graphics.drawRect((int) pos.getX(), (int) pos.getY(), 1, 1);
    }
}
