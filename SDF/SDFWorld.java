package SDF;


import Vector.FloatVec3;

public class SDFWorld {
    //TODO: make it configurable

    public FloatVec3 cameraPos = new FloatVec3(0.0F, -.2F, 5.0F);

    public float getDistanceAt(FloatVec3 point) {
        float sphere = point.subtract(new FloatVec3(0.0F, 0.0F, -6.0F)).length() - 1F; // radius

        return sphere;
    }

    public FloatVec3 rayMarch(FloatVec3 start, FloatVec3 rayDir) {
        FloatVec3 pos = start;
        float minDistance = .001F;

        for (int i = 0; i < 30; i++) {
            float distance = getDistanceAt(pos);
            if (distance < minDistance) {
                return pos;
            }
            pos = pos.add(rayDir.multiplyScalar(distance));
        }

        return pos;
    }

    public FloatVec3 calculateNormal(FloatVec3 pos, float distance) {
        float close = .000001F;
        float dSDFdx = getDistanceAt(new FloatVec3(pos.x + close, pos.y, pos.z)) - distance;
        float dSDFdy = getDistanceAt(new FloatVec3(pos.x, pos.y + close, pos.z)) - distance;
        float dSDFdz = getDistanceAt(new FloatVec3(pos.x, pos.y, pos.z + close)) - distance;

        return new FloatVec3(dSDFdx, dSDFdy, dSDFdz).normalize();
    }

}
