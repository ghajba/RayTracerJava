package hu.japy.dev.raytracer;

import static hu.japy.dev.raytracer.Matrix.matrix;

public final class MatrixTransformations {
    private MatrixTransformations() {
    }

    public static Matrix translation(double x, double y, double z) {
        return matrix(1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1);
    }

    public static Matrix scaling(double x, double y, double z) {
        return matrix(x, 0, 0, 0, 0, y, 0, 0, 0, 0, z, 0, 0, 0, 0, 1);
    }

    public static Matrix rotationX(double radians) {
        return matrix(1, 0, 0, 0, 0, Math.cos(radians), -Math.sin(radians), 0, 0, Math.sin(radians), Math.cos(radians), 0, 0, 0, 0, 1);
    }

    public static Matrix rotationY(double radians) {
        return matrix(Math.cos(radians), 0, Math.sin(radians), 0, 0, 1, 0, 0, -Math.sin(radians), 0, Math.cos(radians), 0, 0, 0, 0, 1);
    }

    public static Matrix rotationZ(double radians) {
        return matrix(Math.cos(radians), -Math.sin(radians), 0, 0, Math.sin(radians), Math.cos(radians), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    }

    public static Matrix shearing(double xy, double xz, double yx, double yz, double zx, double zy) {
        return matrix(1, xy, xz, 0, yx, 1, yz, 0, zx, zy, 1, 0, 0, 0, 0, 1);
    }
}
