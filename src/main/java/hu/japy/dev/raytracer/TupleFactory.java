package hu.japy.dev.raytracer;

/**
 * This factory generates different types of tuples
 */
public class TupleFactory {

    /**
     * @return a not specified tuple object, currently use it only for tests
     */
    static Tuple tuple(float x, float y, float z, int w) {
        return new Tuple(x, y, z, w);
    }

    /**
     * @return a tuple representing a point
     */
    public static Tuple point(float x, float y, float z) {
        return new Tuple(x, y, z, 1);
    }

    /**
     * @return a tuple representing a vector
     */
    public static Tuple vector(float x, float y, float z) {
        return new Tuple(x, y, z, 0);
    }
}
