package Vector;


import java.util.ArrayList;

public class Vector3<T extends Number> implements Vector<T> {
    T x;
    T y;
    T z;

    public Vector3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3<T> fromVec) {
        this.x = fromVec.getX();
        this.y = fromVec.getY();
        this.z = fromVec.getZ();
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public T getZ() {
        return z;
    }

//    public Vector3<T> addScalar(T scalar) {
//        // this is sooo stupid, why can't I do that?
//        return new Vector3<T>(
//                x + scalar,
//                y + scalar,
//                z + scalar
//        );
//    }

    @Override
    public ArrayList<T> toList() {
        var list = new ArrayList<T>();
        list.add(x);
        list.add(y);
        list.add(z);
        return list;
    }

    @Override
    public Vector3<T> cloneVec() {
        return new Vector3<T>(this);
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
