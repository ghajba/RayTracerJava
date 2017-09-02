package hu.japy.dev.raytracer

import spock.lang.Specification

class FactorySpec extends Specification {

    def "point(x,y,z) is a function that creates points"() {
        when: "p is point(4, -4, 3)"
        def p = Tuple.point(4, -4, 3)
        then: "p is a tuple (4, -4, 3, 1)"
        p.isPoint() == true
        p == new Tuple(4, -4, 3, 1)
    }

    def "vector(x,y,z) is a function that creates vectors"() {
        when: "Given v is vector(-4, 4, -3)"
        def v = Tuple.vector(-4, 4, -3)
        then: "v is a tuple (-4, 4, -3, 0)"
        v.isVector()
        v == new Tuple(-4, 4, -3, 0)
    }
}
