package hu.japy.dev.raytracer;

public class Shearing extends Matrix {
    public Shearing(double xy, double xz, double yx, double yz, double zx, double zy) {
        super(1, xy, xz, 0, yx, 1, yz, 0, zx, zy, 1, 0, 0, 0, 0, 1);
    }
}
