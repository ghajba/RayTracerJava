package hu.japy.dev.raytracer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import static hu.japy.dev.raytracer.Tuple.point;

public class Sphere {
    public final Tuple origin;
    public final double radius;

    private Matrix transform = Matrix.IDENTITY;

    public static Sphere sphere() {
        return sphere(point(0, 0, 0), 1);
    }

    public static Sphere sphere(Tuple origin, double radius) {
        return new Sphere(origin, radius);
    }

    private Sphere(Tuple origin, double radius) {
        this.origin = origin;
        this.radius = radius;
    }

    public Matrix getTransform() {
        return transform;
    }

    public void setTransform(Matrix transformationMatrix) {
        this.transform = transformationMatrix;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("Origin", origin).append("radius", radius).toString();
    }
}
