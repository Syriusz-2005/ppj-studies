package Vector;

import java.util.ArrayList;

public class MutFVector3 implements Vector<Float> {
    private float x;
    private float y;
    private float z;

    public MutFVector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MutFVector3(MutFVector3 vec) {
        x = vec.x;
        y = vec.y;
        z = vec.z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public MutFVector3 setX(float val) {
        x = val;
        return this;
    }

    public MutFVector3 setY(float val) {
        y = val;
        return this;
    }

    public MutFVector3 setZ(float val) {
        z = val;
        return this;
    }

    public MutFVector3 addScalar(float s) {
        x += s;
        y += s;
        z += s;

        return this;
    }

    public MutFVector3 add(MutFVector3 vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    public MutFVector3 subtractScalar(float s) {
        x -= s;
        y -= s;
        z -= s;

        return this;
    }

    public MutFVector3 subtract(MutFVector3 vec) {
        x -= vec.x;
        y -= vec.y;
        z -= vec.z;

        return this;
    }

    public MutFVector3 multiplyScalar(float s) {
        x *= s;
        y *= s;
        z *= s;
        return this;
    }

    public MutFVector3 multiply(MutFVector3 vec) {
        x *= vec.x;
        y *= vec.y;
        z *= vec.z;

        return this;
    }

    public float dot(FloatVec3 vec) {
        return x * vec.x + y * vec.y + z * vec.z;
    }

    @Override
    public MutFVector3 cloneVec() {
        var vec = new MutFVector3(this);
        return vec;
    }

    @Override
    public ArrayList<Float> toList() {
        var list = new ArrayList<Float>();
        list.add(x);
        list.add(y);
        list.add(z);
        return list;
    }


}
