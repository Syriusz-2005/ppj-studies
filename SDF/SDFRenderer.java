package SDF;

import Vector.FloatVec3;

import java.awt.geom.Point2D;
// Inspiration from: https://github.com/jasmcole/Blog/blob/master/CSG/src/fragment.ts
public class SDFRenderer {
    private final SDFWorld world;
    private final Out out;
    private final Drawable drawer;

    private final int screenWidth;
    private final int screenHeight;

    public final float aspectRatio;
    public final float fovX = .33F;
    public final float fovY;

    public SDFRenderer(SDFWorld world, int screenWidth, int screenHeight, Out out, Drawable drawer) {
        this.world = world;
        this.out = out;
        this.drawer = drawer;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.aspectRatio = (float) screenWidth / (float) screenHeight;
        this.fovY = fovX / aspectRatio;
    }


    private Point2D.Float getPixel(float px, float py) {
        return new Point2D.Float(
                (px / screenWidth - .5F) * 2F,
                (py / screenHeight - .5F) * 2F
        );
    }


    public void render() {
        var cameraPos = world.cameraPos;

        FloatVec3 lightDir = new FloatVec3(0F, 1F, -0F).normalize();

        for (int y = 0; y < screenHeight; y++) {
            for (int x = 0; x < screenWidth; x++) {
                var pixel = getPixel(x, y);
                float angleX = pixel.x * fovX;
                float angleY = pixel.y * fovY;
                FloatVec3 rayDir = new FloatVec3((float) Math.tan(angleX), (float) Math.tan(angleY), -1F).normalize();

                FloatVec3 rayHit = world.rayMarch(cameraPos, rayDir);
                float distanceFromCamera = cameraPos.distanceTo(rayHit);
                FloatVec3 normal = world.calculateNormal(rayHit, world.getDistanceAt(rayHit));
                float occlusion = normal.dot(lightDir);
                if (out != null) {
                    out.displayPixel(occlusion, distanceFromCamera);
                } else {
                    drawer.drawAt(new Point2D.Float(x, y), occlusion, distanceFromCamera);
                }
            }
            if (out != null) {
                out.nextRow();
            }
        }
    }
}
