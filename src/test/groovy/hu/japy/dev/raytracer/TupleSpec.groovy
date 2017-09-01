package hu.japy.dev.raytracer

import spock.lang.Specification

class TupleSpec extends Specification {

    def "A tuple with w=1 is a point."() {
        when: "t is a tuple (4, -4, 3, 1)"
        def t = new Tuple(4, -4, 3, 1)
        then: " tx is 4"
        t.x == 4
        and: " ty is -4"
        t.y == -4
        and: " tz is 3"
        t.z == 3
        and: " tw is 1"
        t.w == 1
        and: " is_point(t) is true"
        t.isPoint()
        and: " is_vector(t) is false"
        !t.isVector()
    }

    def "A tuple with w=0 is a vector."() {
        when: "t is a tuple (-4, 4, -3, 0)"
        def t = new Tuple(-4, 4, -3, 0)
        then: " tx is -4"
        t.x == -4
        and: " ty is 4"
        t.y == 4
        and: " tz is -3"
        t.z == -3
        and: " tw is 0"
        t.w == 0
        and: " is_point(t) is true"
        !t.isPoint()
        and: " is_vector(t) is false"
        t.isVector()
    }
}
