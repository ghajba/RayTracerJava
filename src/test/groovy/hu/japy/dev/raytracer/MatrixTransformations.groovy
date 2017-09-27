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

    def "Rotating a point around the x axis."() {
        when: "p is point(0,1,1)"
        def p = Tuple.point(0, 1, 1)
        and: "r1 is rotationx(π/4)"
        def r1 = Rotation.x(Math.PI / 4)
        and: "r2 is rotationx(π/2)"
        def r2 = Rotation.x(Math.PI / 2)
        then: "r1 × p = point(0,0,√2)"
        r1 * p == Tuple.point(0, 0, Math.sqrt(2))
        and: "r2 × p = point(0,-1,1)"
        r2 * p == Tuple.point(0, -1, 1)
    }

    def "Rotating a vector around the x axis."() {
        when: "v is vector(0,1,1)"
        def v = Tuple.vector(0, 1, 1)
        and: "r1 is rotationx(π/4)"
        def r1 = Rotation.x(Math.PI / 4)
        and: "r2 is rotationx(π/2)"
        def r2 = Rotation.x(Math.PI / 2)
        then: "r1 × v = vector(0,0,√2)"
        r1 * v == Tuple.vector(0, 0, Math.sqrt(2))
        and: "r2 × v = vector(0,-1,1)"
        r2 * v == Tuple.vector(0, -1, 1)
    }

    def "Inverse of x-rotation rotates in the opposite direction."() {
        when: "v is vector(0,1,1)"
        def v = Tuple.vector(0, 1, 1)
        and: "r is inverse(rotationx(π/4))"
        def r = Rotation.x(Math.PI / 4).inverse()
        then: "r × v = vector(0,√2,0)"
        r * v == Tuple.vector(0, Math.sqrt(2), 0)
    }

    def "Rotating a point around the y axis."() {
        when: "p is point(1,0,1)"
        def p = Tuple.point(1, 0, 1)
        and: "r1 is rotationy(π/4)"
        def r1 = Rotation.y(Math.PI / 4)
        and: "r2 is rotationy(π/2)"
        def r2 = Rotation.y(Math.PI / 2)

        then: "r1 × p = point(√2,0,0)"
        r1 * p == Tuple.point(Math.sqrt(2), 0, 0)
        and: "r2 × p = point(1,0,-1)"
        r2 * p == Tuple.point(1, 0, -1)
    }

    def "Rotating a vector around the y axis."() {
        when: "v is vector(1,0,1)"
        def v = Tuple.vector(1, 0, 1)
        and: "r1 is rotationy(π/4)"
        def r1 = Rotation.y(Math.PI / 4)
        and: "r2 is rotationy(π/2)"
        def r2 = Rotation.y(Math.PI / 2)
        then: "r1 × v = vector(√2,0,0)"
        r1 * v == Tuple.vector(Math.sqrt(2), 0, 0)
        and: "r2 × v = vector(1,0,-1)"
        r2 * v == Tuple.vector(1, 0, -1)
    }

    def "Inverse of y-rotation rotates in the opposite direction."() {
        when: "v is vector(1,0,1)"
        def v = Tuple.vector(1, 0, 1)
        and: "r is inverse(rotationy(π/4))"
        def r = Rotation.y(Math.PI / 4).inverse()
        then: "r × v = vector(0,0,√2)"
        r * v == Tuple.vector(0, 0, Math.sqrt(2))
    }

    def "Rotating a point around the z axis."() {
        when: "p is point(1,1,0)"
        def p = Tuple.point(1, 1, 0)
        and: "r1 is rotationz(π/4)"
        def r1 = Rotation.z((Math.PI / 4))
        and: "r2 is rotationz(π/2)"
        def r2 = Rotation.z(Math.PI / 2)

        then: "r1 × p = point(0,√2,0)"
        r1 * p == Tuple.point(0, Math.sqrt(2), 0)
        and: "r2 × p = point(-1,1,0)"
        r2 * p == Tuple.point(-1, 1, 0)
    }

    def "Rotating a vector around the z axis."() {
        when: "v is vector(1,1,0)"
        def v = Tuple.vector(1, 1, 0)
        and: "r1 is rotationz(π/4)"
        def r1 = Rotation.z(Math.PI / 4)
        and: "r2 is rotationz(π/2)"
        def r2 = Rotation.z(Math.PI / 2)

        then: "r1 × v = vector(0,√2,0)"
        r1 * v == Tuple.vector(0, Math.sqrt(2), 0)
        and: "r2 × v = vector(-1,1,0)"
        r2 * v == Tuple.vector(-1, 1, 0)
    }

    def "Inverse of z-rotation rotates in the opposite direction."() {
        when: "v is vector(1,1,0)"
        def v = Tuple.vector(1, 1, 0)
        and: "r is inverse(rotationz(π/4))"
        def r = Rotation.z(Math.PI / 4).inverse()
        then: "r × v = vector(√2,0,0)"
        r * v == Tuple.vector(Math.sqrt(2), 0, 0)
    }
}