package hu.japy.dev.raytracer

import spock.lang.Specification
import spock.lang.Unroll

import static hu.japy.dev.raytracer.Ray.ray
import static hu.japy.dev.raytracer.Sphere.sphere
import static hu.japy.dev.raytracer.Tuple.point
import static hu.japy.dev.raytracer.Tuple.vector


class RaySpec extends Specification {
    def "A ray has an origin and a direction."() {
        when: "origin is point(1, 2, 3)"
        def origin = point(1, 2, 3)
        and: "direction is vector(4, 5, 6)"
        def direction = vector(4, 5, 6)
        and: "r is ray(origin, direction)"
        def r = ray(origin, direction)
        then: "r.origin = origin"
        r.origin == origin
        and: "r.direction = direction"
        r.direction == direction
    }

    @Unroll
    def "A point can be computed from distance t."() {
        when: "r is ray(point(2, 3, 4), vector(1, 0, 0))"
        def r = ray(point(2, 3, 4), vector(1, 0, 0))
        then: "position(r, t) = expected"
        r.position(t) == expected
        where:
        t   | expected
        0   | point(2, 3, 4)
        1   | point(3, 3, 4)
        -1  | point(1, 3, 4)
        2.5 | point(4.5, 3, 4)
    }

    def "A ray intersects a sphere at two points."() {
        when: "r is ray(point(0, 0, -5), vector(0, 0, 1))"
        def r = ray(point(0, 0, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "t is intersect(r, s)"
        def t = r.intersect(s)

        then: "size(t) = 2"
        t.size() == 2
        and: "t1 = 4"
        t[0] == 4
        and: "t2 = 6"
        t[1] == 6
    }

    def "A ray intersects a sphere at one point."() {
        when: "r is ray(point(0, 1, -5), vector(0, 0, 1))"
        def r = ray(point(0, 1, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "t is intersect(r, s)"
        def t = r.intersect(s)

        then: "size(t) = 1"
        t.size() == 1
        and: "t1 == 5"
        t[0] == 5
    }

    def "A ray misses a sphere."() {
        when: "r is ray(point(0, 2, -5), vector(0, 0, 1))"
        def r = ray(point(0, 2, -5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "t is intersect(r, s)"
        def t = r.intersect(s)

        then: "t is empty"
        t.isEmpty()
    }

    def "A ray is inside a sphere."() {
        when: "r is ray(point(0, 0, 0), vector(0, 0, 1))"
        def r = ray(point(0, 0, 0), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "t is intersect(r, s)"
        def t = r.intersect(s)

        then: "size(t) = 2"
        t.size() == 2
        and: "t1 = -1"
        t[0] == -1
        and: "t2 = 1"
        t[1] == 1
    }

    def "A sphere is behind a ray."() {
        when: "r is ray(point(0, 0, 5), vector(0, 0, 1))"
        def r = ray(point(0, 0, 5), vector(0, 0, 1))
        and: "s is sphere()"
        def s = sphere()
        and: "t is intersect(r, s)"
        def t = r.intersect(s)

        then: "size(t) = 2"
        t.size() == 2
        and: "t1 = -6"
        t[0] == -6
        and: "t2 = -4"
        t[1] == -4
    }
}