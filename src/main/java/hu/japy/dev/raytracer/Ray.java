package hu.japy.dev.raytracer;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static hu.japy.dev.raytracer.Intersection.intersection;

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

    public List<Intersection> intersect(Sphere sphere) {
        Ray ray = transform(sphere.getTransform().inverse());

        Tuple sphereToRay = ray.origin.sub(sphere.origin);
        double a = ray.direction.dot(ray.direction);
        double b = 2 * ray.direction.dot(sphereToRay);
        double c = sphereToRay.dot(sphereToRay) - 1;

        double discriminant = b * b - 4 * a * c;

        if (Double.compare(discriminant, 0) < 0) return new ArrayList<>();

        ArrayList<Intersection> intersections = new ArrayList<>();

        if (Double.compare(discriminant, 0) == 0) {
            intersections.add(intersection(-b / (2 * a), sphere, false));
        } else {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            if(t1 < t2) {
                intersections.add(intersection(t1, sphere, false));
                intersections.add(intersection(t2, sphere, true));
            }
            else {
                intersections.add(intersection(t2, sphere, false));
                intersections.add(intersection(t1, sphere, true));
            }
        }
        return intersections;
    }

    public Ray transform(Matrix transformationMatrix) {
        return new Ray(transformationMatrix.multiply(origin), transformationMatrix.multiply(direction));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(origin).append(direction).toString();
    }
}
