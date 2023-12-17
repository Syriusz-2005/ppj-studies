package Vector;

import java.util.ArrayList;

public class FloatVec3 implements Vector<Float> {
    public final float x;
    public final float y;
    public final float z;

    public FloatVec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FloatVec3(FloatVec3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    @Override
    public FloatVec3 cloneVec() {
        return new FloatVec3(
                x,
                y,
                z
        );
    }

    @Override
    public ArrayList<Float> toList() {
        var list = new ArrayList<Float>();
        list.add(x);
        list.add(y);
        list.add(z);
        return list;
    }

    public FloatVec3 negate() {
        return new FloatVec3(
            -x,
            -y,
            -z
        );
    }

    public FloatVec3 subtractScalar(float s) {
        return new FloatVec3(
            x - s,
            y - s,
            z - s
        );
    }

    public FloatVec3 subtract(FloatVec3 vec) {
        return new FloatVec3(
          x - vec.x,
          y - vec.y,
          z - vec.z
        );
    }

    public FloatVec3 addScalar(float s) {
        return subtractScalar(-s);
    }

    public FloatVec3 add(FloatVec3 vec) {
        // the performance is not my priority
        return subtract(vec.negate());
    }

    public FloatVec3 setX(float x) {
        return new FloatVec3(
                x,
                y,
                z
        );
    }

    public FloatVec3 setY(float y) {
        return new FloatVec3(
                x,
                y,
                z
        );
    }

    public FloatVec3 setZ(float z) {
        return new FloatVec3(
                x,
                y,
                z
        );
    }

    public FloatVec3 multiplyScalar(float s) {
        return new FloatVec3(
            x * s,
            y * s,
            z * s
        );
    }

    public FloatVec3 multiply(FloatVec3 vec) {
        return new FloatVec3(
                x * vec.x,
                y * vec.y,
                z * vec.z
        );
    }

    public FloatVec3 divideScalar(float s) {
        return new FloatVec3(
                x / s,
                y / s,
                z / s
        );
    }

    public FloatVec3 divide(FloatVec3 vec) {
        return new FloatVec3(
                x / vec.x,
                y / vec.y,
                z / vec.z
        );
    }

    public float distanceTo(FloatVec3 vec) {
        FloatVec3 delta = vec.subtract(this);

        return (float) Math.sqrt(Math.pow(delta.x, 2) + Math.pow(delta.y, 2) + Math.pow(delta.z, 2));
    }

    public float length() {
        // I smell some garbage collection shenanigans
        return distanceTo(new FloatVec3(0.0F, 0.0F, 0.0F));
    }

    public FloatVec3 normalize() {
        float vecLength = length();
        return divideScalar(vecLength);
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
