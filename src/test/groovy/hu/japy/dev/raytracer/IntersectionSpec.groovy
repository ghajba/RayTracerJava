package hu.japy.dev.raytracer

import spock.lang.Specification

import static hu.japy.dev.raytracer.Intersection.intersection
import static hu.japy.dev.raytracer.Sphere.sphere


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

}