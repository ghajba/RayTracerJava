package hu.japy.dev.raytracer

import spock.lang.Specification

import static hu.japy.dev.raytracer.Intersection.hit
import static hu.japy.dev.raytracer.Intersection.intersection
import static hu.japy.dev.raytracer.Sphere.sphere


class HitSpec extends Specification {
    def "Computing the hit when all intersections have positive t."() {
        when: "s is sphere()"
        def s = sphere()
        and: "i1 is intersection(1, s, false)"
        def i1 = intersection(1, s, false)
        and: "i2 is intersection(2, s, false)"
        def i2 = intersection(2, s, false)
        and: "i is (i1, i2)"
        def i = [i1, i2]
        and: "h is hit(i)"
        def h = hit(i)
        then: "h = i1"
        h == i1
    }

    def "Computing the hit when some intersections have negative t."() {
        when: "s is sphere()"
        def s = sphere()
        and: "i1 is intersection(-1, s, false)"
        def i1 = intersection(-1, s, false)
        and: "i2 is intersection(1, s, false)"
        def i2 = intersection(1, s, false)
        and: "i is (i1, i2)"
        def i = [i1, i2]
        and: "h is hit(i)"
        def h = hit(i)
        then: "h = i2"
        h == i2
    }

    def "Computing the hit when all intersections have negative t."() {
        when: "s is sphere()"
        def s = sphere()
        and: "i1 is intersection(-2, s, false)"
        def i1 = intersection(-2, s, false)
        and: "i2 is intersection(-1, s, false)"
        def i2 = intersection(-1, s, false)
        and: "i is (i1, i2)"
        def i = [i1, i2]
        then: "hit(i) returns nothing"
        hit(i) == null
    }
}