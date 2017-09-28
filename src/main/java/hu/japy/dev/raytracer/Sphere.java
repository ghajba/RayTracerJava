package hu.japy.dev.raytracer;

import static hu.japy.dev.raytracer.Tuple.point;

public class Sphere {
    public final Tuple origin;
    public final double radius;

    public static Sphere sphere() {
        return sphere(point(0,0,0), 1);
    }
    public static Sphere sphere(Tuple origin, double radius) {
        return new Sphere(origin, radius);
    }

    private Sphere(Tuple origin, double radius) {
        this.origin = origin;
        this.radius = radius;
    }
}
