package hu.japy.dev.raytracer;

/**
 * A custom type of tuple required for the project.
 */
public class Tuple {

    public final float x;
    public final float y;
    public final float z;

    final int w;

    public static Tuple point(float x, float y, float z) {
        return new Tuple(x, y, z, 1);
    }

    public static Tuple vector(float x, float y, float z) {
        return new Tuple(x, y, z, 0);
    }

    Tuple(float x, float y, float z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public boolean isPoint() {
        return this.w == 1;
    }

    public boolean isVector() {
        return this.w == 0;
    }



    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tuple tuple = (Tuple) o;

        if (Float.compare(tuple.x, x) != 0) {
            return false;
        }
        if (Float.compare(tuple.y, y) != 0) {
            return false;
        }
        if (Float.compare(tuple.z, z) != 0) {
            return false;
        }
        return w == tuple.w;
    }

    @Override public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        result = 31 * result + w;
        return result;
    }
}
