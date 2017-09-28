package hu.japy.dev.raytracer;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Intersection {
    public final double t;
    public final Sphere object;
    public final boolean inside;

    public static Intersection intersection(double t, Sphere s, boolean inside) {
        return new Intersection(t, s, inside);
    }

    public static Intersection hit(List<Intersection> intersections) {
        return intersections.stream().filter(i -> i.t >= 0).findFirst().orElse(null);
    }

    private Intersection(double t, Sphere object, boolean inside) {
        this.t = t;
        this.object = object;
        this.inside = inside;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Intersection that = (Intersection) o;

        return new EqualsBuilder()
                .append(t, that.t)
                .append(inside, that.inside)
                .append(object, that.object)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(t)
                .append(object)
                .append(inside)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(t).append(object).append(inside).toString();
    }
}
