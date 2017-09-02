package hu.japy.dev.raytracer

import spock.lang.Specification

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
}
