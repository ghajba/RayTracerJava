package hu.japy.dev.raytracer

import spock.lang.Specification

import static hu.japy.dev.raytracer.MatrixTransformations.scaling
import static hu.japy.dev.raytracer.MatrixTransformations.translation
import static hu.japy.dev.raytracer.Ray.ray
import static hu.japy.dev.raytracer.Sphere.sphere
import static hu.japy.dev.raytracer.Tuple.point
import static hu.japy.dev.raytracer.Tuple.vector


class RayTransformationSpec extends Specification {
    def "Translating a ray moves its origin but not its direction."() {
        when: "r is ray(point(1, 2, 3), vector(0, 1, 0))"
        def r = ray(point(1, 2, 3), vector(0, 1, 0))
        and: "m is translation(3, 4, 5)"
        def m = translation(3, 4, 5)
        and: "r2 is transform(r, m)"
        def r2 = r.transform(m)
        then: "r2.origin = point(4, 6, 8)"
        r2.origin == point(4, 6, 8)
        and: "r2.direction = vector(0, 1, 0)"
        r2.direction == vector(0, 1, 0)
    }

    def "Scaling a ray affects both its origin and direction."() {
        when: "r is ray(point(1, 2, 3), vector(0, 1, 0))"
        def r = ray(point(1, 2, 3), vector(0, 1, 0))
        and: "m is scaling(2, 3, 4)"
        def m = scaling(2, 3, 4)
        and: "r2 is transform(r, m)"
        def r2 = r.transform(m)
        then: "r2.origin = point(2, 6, 12)"
        r2.origin == point(2, 6, 12)
        and: "r2.direction = vector(0, 3, 0)"
        r2.direction == vector(0, 3, 0)
    }

    def "A sphere has the identity matrix as its default transformation."() {
        when: "s is sphere()"
        def s = sphere()
        then: "s.transform is identity"
        s.getTransform() == Matrix.IDENTITY
    }

    def "A sphere allows its transformation to be assigned."() {
        when: "s is sphere()"
        def s = sphere()
        and: "s.transform is assigned translation(2, 3, 4)"
        s.setTransform(translation(2, 3, 4))
        then: "s.transform is translation(2, 3, 4)"
        s.getTransform() == translation(2, 3, 4)
    }

    def "Intersecting a scaled sphere makes the ray intersect earlier."() {
        when: "r is ray(point(0, 0, -5), vector(0, 0, 1))"
        def r = ray(point(0, 0, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "s.transform is assigned scaling(2, 2, 2)"
        s.setTransform(scaling(2, 2, 2))
        and: "i is intersect(r, s)"
        def i = r.intersect(s)
        then: "size(i) = 2"
        i.size() == 2
        and: "i1.t = 3"
        i[0].t == 3
        and: "i2.t = 7"
        i[1].t == 7
    }

    def "Intersecting a translated sphere misses."() {
        when: "r is ray(point(0, 0, -5), vector(0, 0, 1))"
        def r = ray(point(0, 0, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "s.transform is assigned translation(5, 0, 0)"
        s.setTransform(translation(5, 0, 0))
        then: "i is intersect(r, s)"
        def i = r.intersect(s)
        and: "i is empty"
        i.isEmpty()
    }
}