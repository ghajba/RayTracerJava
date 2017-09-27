package hu.japy.dev.raytracer;

public class Rotation extends Matrix {

    public static Rotation x(double radians) {
        return new Rotation(1, 0, 0, 0, 0, Math.cos(radians), -Math.sin(radians), 0, 0, Math.sin(radians), Math.cos(radians), 0, 0, 0, 0, 1);
    }

    public static Rotation y(double radians) {
        return new Rotation(Math.cos(radians), 0, Math.sin(radians), 0, 0, 1, 0, 0, -Math.sin(radians), 0, Math.cos(radians), 0, 0, 0, 0, 1);
    }

    public static Rotation z(double radians) {
        return new Rotation(Math.cos(radians), -Math.sin(radians), 0, 0, Math.sin(radians), Math.cos(radians), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    }

    private Rotation(double... data) {
        super(data);
    }
}
