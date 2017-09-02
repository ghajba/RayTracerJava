package hu.japy.dev.raytracer

import spock.lang.Specification

import static Math.sqrt

class TupleOperationsSpec extends Specification {
    def "Adding two tuples returns the sum as a tuple"() {
        when: "t1 is a tuple (3, -2, 5, 1)"
        def t1 = new Tuple(3, -2, 5, 1)
        and: "t2 is a tuple (-2, 3, 1, 0)"
        def t2 = new Tuple(-2, 3, 1, 0)
        then: "t1 + t2 is a tuple (1, 1, 6, 1)"
        t1.add(t2) == new Tuple(1, 1, 6, 1)
    }

    def "Subtracting two points returns a vector."() {
        when: "p1 is point(3, 2, 1)"
        def p1 = Tuple.point(3, 2, 1)
        and: "p2 is point(5, 6, 7)"
        def p2 = Tuple.point(5, 6, 7)
        then: "p1 - p2 is vector (-2, -4, -6)"
        def p3 = p1.sub(p2)
        p3.isVector()
        p3 == Tuple.vector(-2, -4, -6)
    }

    def "negate() negates a tuple"() {
        when: "t is a tuple (1,-2,3,-4)"
        def t = new Tuple(1, -2, 3, -4)
        then: "negate(t) is a tuple (-1, 2, -3, 4)"
        t.negate() == new Tuple(-1, 2, -3, 4)
    }

    def "Multiplying a tuple by a scalar scales the tuple"() {
        when: "t is a tuple (1, -2, 3, -4)"
        def t = new Tuple(1, -2, 3, -4)
        then: "t × 3.5 is a tuple (3.5, -7, 10.5, -14)"
        t.multiply(3.5) == new Tuple(3.5, -7, 10.5, -14)
        and: "t × 1⁄2 is a tuple (0.5, -1, 1.5, -2)"
        t.multiply(0.5) == new Tuple(0.5, -1, 1.5, -2)
    }

    def "Dividing a tuple by a scalar scales the tuple"() {
        when: "t is a tuple (1, -2, 3, -4)"
        def t = new Tuple(1, -2, 3, -4)
        then: "t ÷ 2 is a tuple (0.5, -1, 1.5, -2)"
        t.divide(2) == new Tuple(0.5, -1, 1.5, -2)
    }

    def "Dividing a tuple by zero returns the zero-tuple"() {
        when: "t is a tuple (1, 2, 3, 4)"
        def t = new Tuple(1, 2, 3, 4)
        then: "t ÷ 2 is a tuple (0, 0, 0, 0)"
        t.divide(0) == new Tuple(0, 0, 0, 0)
    }

    def "Computing the magnitude of a vector"() {
        expect:
        t.magnitude() == m
        where:
        t                        | m
        Tuple.vector(1, 0, 0)    | 1
        Tuple.vector(0, 1, 0)    | 1
        Tuple.vector(0, 0, 1)    | 1
        Tuple.vector(1, 2, 3)    | sqrt(14)
        Tuple.vector(-1, -2, -3) | sqrt(14)
    }

    def "Normalizing vectors"() {
        expect:
        t.normalize() == n
        where:
        t                     | n
        Tuple.vector(4, 0, 0) | Tuple.vector(1, 0, 0)
        Tuple.vector(1, 2, 3) | Tuple.vector(1 / sqrt(14), 2 / sqrt(14), 3 / sqrt(14))
    }

    def "Magnitude of normalized vectors"() {
        when:
        def v = Tuple.vector(1, 2, 3)
        then:
        v.normalize().magnitude() == 1
    }

    def "Dot product turns two vectors into a scalar"() {
        when: "a is vector(1,2,3)"
        def a = Tuple.vector(1, 2, 3)
        and: "b is vector(2,3,4)"
        def b = Tuple.vector(2, 3, 4)
        then: "dot(a,b) is 20"
        a.dot(b) == 20
    }

    def "Cross product of two vectors produces a vector."() {
        when: "a is vector(1,2,3)"
        def a = Tuple.vector(1, 2, 3)
        and: "b is vector(2,3,4)"
        def b = Tuple.vector(2, 3, 4)
        then: "cross(a,b) is vector(-1,2,-1)"
        a.cross(b) == Tuple.vector(-1, 2, -1)
    }
}
