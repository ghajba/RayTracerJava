package hu.japy.dev.raytracer

import spock.lang.Specification


class MatrixTransformations extends Specification {

    def "A translation matrix moves points linearly."() {
        when: "t is translation(5,-3,2)"
        def t = new Translation(5, -3, 2)
        and: "p is point(-3,4,5)"
        def p = Tuple.point(-3, 4, 5)

        then: "t × p = point(2,1,7)"
        t * p == Tuple.point(2, 1, 7)
    }

    def "Inverse of translation moves point backwards"() {
        when: "t is inverse(translation(5,-3,2))"
        def t = new Translation(5, -3, 2).inverse()
        and: "p is point(-3,4,5)"
        def p = Tuple.point(-3, 4, 5)

        then: "t × p = point(-8,7,3)"
        t * p == Tuple.point(-8, 7, 3)
    }

    def "Translation does not move vectors."() {
        when: "t is translation(5,-3,2)"
        def t = new Translation(5, -3, 2)
        and: "v is vector(-3,4,5)"
        def v = Tuple.vector(-3, 4, 5)

        then: "t × v = v"
        t * v == v
    }
}