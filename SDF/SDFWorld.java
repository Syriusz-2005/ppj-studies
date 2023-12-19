package SDF;


import Vector.FloatVec3;

public class SDFWorld {
    //TODO: make it configurable

    public FloatVec3 cameraPos = new FloatVec3(0.0F, 0.0F, 5.0F);

    public float getDistanceAt(FloatVec3 point) {
        float sphere = point.subtract(new FloatVec3(0.0F, 0.0F, -3.0F)).length() - 1.4F; // radius

        return sphere;
    }
}
