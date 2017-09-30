package hu.japy.dev.raytracer

import spock.lang.Specification

import static hu.japy.dev.raytracer.Intersection.intersection
import static hu.japy.dev.raytracer.Ray.ray
import static hu.japy.dev.raytracer.Sphere.sphere
import static hu.japy.dev.raytracer.Tuple.point
import static hu.japy.dev.raytracer.Tuple.vector


class IntersectionSpec extends Specification {

    def "An intersection encapsulates t, object, and inside/outside."() {
        when: "s is sphere()"
        def s = sphere()
        and: "i is intersection(3.5, s, true)"
        def i = intersection(3.5, s, true)
        then: "i.t = 3.5"
        i.t == 3.5
        and: "i.object = s"
        i.object == s
        and: "i.inside is true"
        i.inside
    }

    def "Intersect computes inside/outside correctly."() {
        when: "r is ray(point(0, 0, -5), vector(0, 0, 1))"
        def r = ray(point(0, 0, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "i is intersect(r, s)"
        def i = r.intersect(s)
        then: "i1.inside = false"
        !i[0].inside
        and: "i2.inside = true"
        i[1].inside
    }

}