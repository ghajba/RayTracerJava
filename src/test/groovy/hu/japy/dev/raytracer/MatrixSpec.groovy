package hu.japy.dev.raytracer

import spock.lang.Specification


class MatrixSpec extends Specification {
    def "A new matrix accepts 16 floating point numbers."() {
        when: "m is a 4x4 matrix with rows (1, 2, 3, 4), (5.5, 6.5, 7.5, 8.5), (9, 10, 11, 12), (13.5, 14.5, 15.5, 16.5)"
        def m = new Matrix(
                1, 2, 3, 4,
                5.5, 6.5, 7.5, 8.5,
                9, 10, 11, 12,
                13.5, 14.5, 15.5, 16.5
        )
        then: "m_ij == n"
        m.at(i, j) == n

        where:
        i | j | n
        0 | 0 | 1
        0 | 1 | 2
        0 | 2 | 3
        0 | 3 | 4
        1 | 0 | 5.5
        1 | 1 | 6.5
        1 | 2 | 7.5
        1 | 3 | 8.5
        2 | 0 | 9
        2 | 1 | 10
        2 | 2 | 11
        2 | 3 | 12
        3 | 0 | 13.5
        3 | 1 | 14.5
        3 | 2 | 15.5
        3 | 3 | 16.5
    }

    def "Describe matrix multiplication."() {
        when: "a is a 4x4 matrix with rows (1, 2, 3, 4), (2, 3, 4, 5), (3, 4, 5, 6), (4, 5, 6, 7)"
        def a = new Matrix(1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 6, 4, 5, 6, 7)
        and: "b is a 4x4 matrix with rows (0, 1, 2, 4), (1, 2, 4, 8), (2, 4, 8, 16), (4, 8, 16, 32)"
        def b = new Matrix(0, 1, 2, 4, 1, 2, 4, 8, 2, 4, 8, 16, 4, 8, 16, 32)
        then: "a × b is another 4x4 matrix with rows (24, 49, 98, 196), (31, 64, 128, 256), (38, 79, 158, 316), (45, 94, 188, 376)"
        def expexted = new Matrix(24, 49, 98, 196, 31, 64, 128, 256, 38, 79, 158, 316, 45, 94, 188, 376)
        a.multiply(b) == expexted
    }

    def "A matrix multiplied by a tuple produces a tuple."() {
        when: "m is a 4x4 matrix with rows (1, 2, 3, 4), (2, 4, 4, 2), (8, 6, 4, 1), (0, 0, 0, 1)"
        def m = new Matrix(1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1)
        and: "t is a tuple (1, 2, 3, 1)"
        def t = new Tuple(1, 2, 3, 1)
        then: "m × t is a tuple (18, 24, 33, 1)"
        def expected = new Tuple(18, 24, 33, 1)
        def result = m.multiply(t)
        result == expected
    }

    def "Multiplying by the identity matrix gives you the multiplicand."() {
        when: "a is an arbitrary 4x4 matrix"
        def a = new Matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        then: "a × identity = a"
        a * Matrix.IDENTITY == a
    }

    def "transpose(a) transposes its argument."() {
        when: "a is a 4x4 matrix with rows (1, 2, 3, 4), (5, 6, 7, 8), (8, 6, 7, 5), (4, 3, 2, 1)"
        def a = new Matrix(1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1)
        then: "transpose(a) is another 4x4 matrix with rows (1, 5, 8, 4), (2, 6, 7, 3), (3, 7, 6, 2), (4, 8, 5, 1)"
        a.transpose() == new Matrix(1, 5, 8, 4, 2, 6, 7, 3, 3, 7, 6, 2, 4, 8, 5, 1)
    }

    def "transpose(identity) produces the identity matrix"() {
        when: "i is the identity matrix"
        def i = Matrix.IDENTITY
        then: "transpose(identity) = identity"
        i.transpose() == Matrix.IDENTITY
    }
}
