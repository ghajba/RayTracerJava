package hu.japy.dev.raytracer;

/**
 * A custom type of tuple required for the project.
 */
public class Tuple {

    public final double x;
    public final double y;
    public final double z;

    final int w;

    public static Tuple point(double x, double y, double z) {
        return new Tuple(x, y, z, 1);
    }

    public static Tuple vector(double x, double y, double z) {
        return new Tuple(x, y, z, 0);
    }

    Tuple(double x, double y, double z, int w) {
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

    public Tuple add(Tuple other) {
        return new Tuple(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    public Tuple sub(Tuple other) {
        return new Tuple(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    public Tuple negate() {
        return new Tuple(-x, -y, -z, -w);
    }

    public Tuple multiply(float multiplicator) {
        return new Tuple(x * multiplicator, y * multiplicator, z * multiplicator, (int) (w * multiplicator));
    }

    public Tuple divide(float divisor) {
        if (Float.compare(divisor, 0) == 0) {
            return new Tuple(0, 0, 0, 0);
        }
        return new Tuple(x / divisor, y / divisor, z / divisor, (int) (w / divisor));
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Tuple normalize() {
        return new Tuple(x / magnitude(), y / magnitude(), z / magnitude(), w);
    }

    public double dot(Tuple other) {
        return x * other.x + y * other.y + z * other.z;
    }

    public Tuple cross(Tuple other) {
        return Tuple.vector(y * other.z - z * other.y, z * other.x - x * other.z, x * other.y - y * other.x);
    }

    @Override public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tuple tuple = (Tuple) o;

        if (Double.compare(tuple.x, x) != 0) {
            return false;
        }
        if (Double.compare(tuple.y, y) != 0) {
            return false;
        }
        if (Double.compare(tuple.z, z) != 0) {
            return false;
        }
        return w == tuple.w;
    }

    @Override public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + w;
        return result;
    }
}
