package hu.japy.dev.raytracer.puttingtogether

import spock.lang.Specification
import spock.lang.Unroll

import static hu.japy.dev.raytracer.MatrixTransformations.rotationY
import static hu.japy.dev.raytracer.Tuple.point


class ClockHoursSpec extends Specification {

    @Unroll
    def "Points of hours of a clock are computed properly using rotations."() {
        when: "_12 is a point(0,0,1)"
        def _12 = point(0, 0, 1)
        and: "r is a rotationy(n* π/6)"
        def r = rotationY(n * Math.PI / 6)
        then: "_12 * r == expected"
        r * _12 == expected
        where:
        n  | expected
        1  | point(1 / 2, 0, Math.sqrt(3) / 2)
        2  | point(Math.sqrt(3) / 2, 0, 1 / 2)
        3  | point(1, 0, 0)
        6  | point(0, 0, -1)
        9  | point(-1, 0, 0)
        12 | point(0, 0, 1)
    }
}