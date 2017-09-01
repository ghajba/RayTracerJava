package hu.japy.dev.raytracer

import spock.lang.Specification

class TupleFactorySpec extends Specification {
    def "point(x,y,z) is a function that creates points"() {
        when: "p is point(4, -4, 3)"
        def p = TupleFactory.point(4, -4, 3)
        then: "p is a tuple (4, -4, 3, 1)"
        p.isPoint() == true
        p == TupleFactory.tuple(4, -4, 3, 1)
    }

    def "vector(x,y,z) is a function that creates vectors"() {
        when: "Given v is vector(-4, 4, -3)"
        def v = TupleFactory.vector(-4, 4, -3)
        then: "v is a tuple (-4, 4, -3, 0)"
        v.isVector()
        v == TupleFactory.tuple(-4, 4, -3, 0)
    }
}
