package hu.japy.dev.raytracer;

public class Translation extends Matrix {

    public Translation(double x, double y, double z) {
        super(1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1);
    }
}
