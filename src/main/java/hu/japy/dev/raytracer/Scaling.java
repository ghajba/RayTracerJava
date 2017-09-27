package hu.japy.dev.raytracer;

public class Scaling extends Matrix {

    public Scaling(double x, double y, double z) {
        super(x, 0, 0, 0, 0, y, 0, 0, 0, 0, z, 0, 0, 0, 0, 1);
    }
}
