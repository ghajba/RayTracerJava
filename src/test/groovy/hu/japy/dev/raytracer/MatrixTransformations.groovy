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

    def "A scaling matrix moves points proportionally."() {
        when: "s is scaling(2,3,4)"
        def s = new Scaling(2, 3, 4)
        and: "p is point(-4,6,8)"
        def p = Tuple.point(-4, 6, 8)
        then: "s × p = point(-8,18,32)"
        s * p == Tuple.point(-8, 18, 32)
    }

    def "A scaling matrix grows a vector proportionally."() {
        when: "s is scaling(2,3,4)"
        def s = new Scaling(2, 3, 4)
        and: "v is vector(-4,6,8)"
        def v = Tuple.vector(-4, 6, 8)
        then: "s × v = vector(-8,18,32)"
        s * v == Tuple.vector(-8, 18, 32)
    }

    def "The inverse of scaling shrinks a tuple."() {
        when: "s is inverse(scaling(2,3,4))"
        def s = new Scaling(2, 3, 4).inverse()
        and: "v is vector(-4,6,8)"
        def v = Tuple.vector(-4, 6, 8)
        then: "s × v = vector(-2,2,2)"
        s * v == Tuple.vector(-2, 2, 2)
    }

    def "Scaling by a negative value produces a reflection transformation."() {
        when: "s is scaling(-1,1,1)"
        def s = new Scaling(-1, 1, 1)
        and: "p is point(2,3,4)"
        def p = Tuple.point(2, 3, 4)
        then: "s × p = point(-2,3,4)"
        s * p == Tuple.point(-2, 3, 4)
    }
}