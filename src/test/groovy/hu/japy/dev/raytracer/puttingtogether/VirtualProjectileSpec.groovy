package hu.japy.dev.raytracer.puttingtogether

import hu.japy.dev.raytracer.Tuple
import hu.japy.dev.raytracer.puttingittogether.Projectile
import hu.japy.dev.raytracer.puttingittogether.World
import spock.lang.Specification


class VirtualProjectileSpec extends Specification {
    def "A projectile has position and velocity"() {
        when: "p is a point"
        def p = Tuple.point(0, 0, 0)
        and: "v is a vector"
        def v = Tuple.vector(1, 1, 0).normalize()
        and: "b is projectile(p, v)"
        def b = new Projectile(p, v)
        then: "b.position is p"
        b.position == p
        and: "b.velocity is v"
        b.velocity == v
    }

    def "The world has gravity and wind resistance"() {
        when: "g is a vector"
        def g = Tuple.vector(0, -0.1, 0)
        and: "r is a vector"
        def r = Tuple.vector(-0.01, 0, 0)
        and: "w is world(g,r)"
        def w = new World(g, r)

        then: "w.gravity is g"
        w.gravity == g
        and: "w.wind is r"
        w.wind == r
    }

    def "Tick produces a new projectile after one unit of time."() {
        given: "g is a gravity vector"
        def g = Tuple.vector(0, -0.1, 0)
        and: "r is a wind vector"
        def r = Tuple.vector(-0.01, 0, 0)

        when: "w is a world"
        def w = new World(g, r)
        and: "b is a projectile at the origo, with an initial velocity of (1,1,0)"
        def b = new Projectile(Tuple.point(0, 0, 0), Tuple.vector(1, 1, 0).normalize())
        and: "b2 is tick(w,b)"
        def b2 = w.tick(b)
        then: "b2.position is b.position + b.velocity"
        b2.position == b.position.add(b.velocity)
        and: "b2.velocity is b.velocity + w.gravity + w.wind"
        b2.velocity == b.velocity.add(w.gravity.add(w.wind))
    }
}
