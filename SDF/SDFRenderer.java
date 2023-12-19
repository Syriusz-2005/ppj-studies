package SDF;

import Vector.FloatVec3;

import java.awt.geom.Point2D;
// Inspiration from: https://github.com/jasmcole/Blog/blob/master/CSG/src/fragment.ts
public class SDFRenderer {
    private final SDFWorld world;
    private final Out out;

    private final int screenWidth = 70;
    private final int screenHeight = 50;

    public final float aspectRatio = (float) screenWidth / (float) screenHeight;
    public final float fovX = .33F;
    public final float fovY = fovX / aspectRatio;

    public SDFRenderer(SDFWorld world, Out out) {
        this.world = world;
        this.out = out;
    }



    private Point2D.Float getPixel(float px, float py) {
        return new Point2D.Float(
                (px / screenWidth - .5F) * 2F,
                (py / screenHeight - .5F) * 2F
        );
    }


    public void render() {
        var cameraPos = world.cameraPos;

        FloatVec3 lightDir = new FloatVec3(0F, 1F, 0).normalize();

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
                out.displayPixel(occlusion, distanceFromCamera);
            }
            out.nextRow();
        }
    }
}
