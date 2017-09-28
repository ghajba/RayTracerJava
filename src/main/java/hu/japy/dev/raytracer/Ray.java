package hu.japy.dev.raytracer;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ray {
    public final Tuple origin;
    public final Tuple direction;

    public static Ray ray(Tuple origin, Tuple direction) {
        return new Ray(origin, direction);
    }

    private Ray(Tuple origin, Tuple direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Tuple position(double t) {
        return origin.add(direction.multiply(t));
    }

    public List<Double> intersect(Sphere sphere) {
        Tuple sphereToRay = origin.sub(sphere.origin);
        double a = direction.dot(direction);
        double b = 2 * direction.dot(sphereToRay);
        double c = sphereToRay.dot(sphereToRay) - 1;

        double discriminant = b * b - 4 * a * c;

        if (Double.compare(discriminant, 0) < 0) return new ArrayList<>();

        ArrayList<Double> intersections = new ArrayList<>();

        if (Double.compare(discriminant, 0) == 0) {
            intersections.add(-b / (2 * a));
        }
        else {
            intersections.add((-b - Math.sqrt(discriminant))/(2*a));
            intersections.add((-b + Math.sqrt(discriminant))/(2*a));

        }
        intersections.sort(Comparator.naturalOrder());
        return intersections;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(origin).append(direction).toString();
    }
}
