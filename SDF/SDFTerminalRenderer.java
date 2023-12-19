package SDF;

import Vector.FloatVec3;

import java.awt.geom.Point2D;
// Inspiration from: https://github.com/jasmcole/Blog/blob/master/CSG/src/fragment.ts
public class SDFTerminalRenderer {
    private final SDFWorld world;

    private final int screenWidth = 70;
    private final int screenHeight = 50;

    public final float aspectRatio = (float) screenWidth / (float) screenHeight;
    public final float fovX = .33F;
    public final float fovY = fovX / aspectRatio;

    public SDFTerminalRenderer(SDFWorld world) {
        this.world = world;
    }

    private FloatVec3 rayMarch(FloatVec3 start, FloatVec3 rayDir) {
        FloatVec3 pos = start;
        float minDistance = .01F;

        for (int i = 0; i < 40; i++) {
            float distance = world.getDistanceAt(pos);
            if (distance < minDistance) {
                return pos;
            }
            pos = pos.add(rayDir.multiplyScalar(distance));
        }

        return pos;
    }

    private Point2D.Float getPixel(float px, float py) {
        return new Point2D.Float(
                (px / screenWidth - .5F) * 2F,
                (py / screenHeight - .5F) * 2F
        );
    }

    private FloatVec3 calculateNormal(FloatVec3 pos, float distance) {
        float close = .01F;
        float dSDFdx = world.getDistanceAt(new FloatVec3(pos.x + close, pos.y, pos.z).subtractScalar(distance));
        float dSDFdy = world.getDistanceAt(new FloatVec3(pos.x, pos.y + close, pos.z).subtractScalar(distance));
        float dSDFdz = world.getDistanceAt(new FloatVec3(pos.x, pos.y, pos.z + close).subtractScalar(distance));

        return new FloatVec3(dSDFdx, dSDFdy, dSDFdz).normalize();
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

                FloatVec3 rayHit = rayMarch(cameraPos, rayDir);
                float distanceFromCamera = cameraPos.distanceTo(rayHit);
                if (distanceFromCamera < 40) {
                    FloatVec3 normal = calculateNormal(rayHit, world.getDistanceAt(rayHit));
                    float occlusion = normal.dot(lightDir);
                    if (occlusion < .05) {
                        System.out.print("■■■");
                    } else if (occlusion < .3) {
                        System.out.print("■□■");
                    } else if (occlusion < .7) {
                        System.out.print("□■□");
                    } else {
                        System.out.print("□□□");
                    }
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
    }
}
