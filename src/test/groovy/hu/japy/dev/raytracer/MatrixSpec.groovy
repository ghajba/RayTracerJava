package hu.japy.dev.raytracer

import spock.lang.Specification

import static hu.japy.dev.raytracer.Matrix.matrix


class MatrixSpec extends Specification {
    def "A matrix accepts 16 floating point numbers."() {
        when: "m is a 4x4 matrix with rows (1, 2, 3, 4), (5.5, 6.5, 7.5, 8.5), (9, 10, 11, 12), (13.5, 14.5, 15.5, 16.5)"
        def m = matrix(
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
        def a = matrix(1, 2, 3, 4, 2, 3, 4, 5, 3, 4, 5, 6, 4, 5, 6, 7)
        and: "b is a 4x4 matrix with rows (0, 1, 2, 4), (1, 2, 4, 8), (2, 4, 8, 16), (4, 8, 16, 32)"
        def b = matrix(0, 1, 2, 4, 1, 2, 4, 8, 2, 4, 8, 16, 4, 8, 16, 32)
        then: "a × b is another 4x4 matrix with rows (24, 49, 98, 196), (31, 64, 128, 256), (38, 79, 158, 316), (45, 94, 188, 376)"
        def expected = matrix(24, 49, 98, 196, 31, 64, 128, 256, 38, 79, 158, 316, 45, 94, 188, 376)
        a * b == expected
    }

    def "A matrix multiplied by a tuple produces a tuple."() {
        when: "m is a 4x4 matrix with rows (1, 2, 3, 4), (2, 4, 4, 2), (8, 6, 4, 1), (0, 0, 0, 1)"
        def m = matrix(1, 2, 3, 4, 2, 4, 4, 2, 8, 6, 4, 1, 0, 0, 0, 1)
        and: "t is a tuple (1, 2, 3, 1)"
        def t = new Tuple(1, 2, 3, 1)
        then: "m × t is a tuple (18, 24, 33, 1)"
        def expected = new Tuple(18, 24, 33, 1)
        def result = m * t
        result == expected
    }

    def "Multiplying by the identity matrix gives you the multiplicand."() {
        when: "a is an arbitrary 4x4 matrix"
        def a = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        then: "a × identity = a"
        a * Matrix.IDENTITY == a
    }

    def "Multiplying matrices"() {
        when: "a is a 4x4 matrix with rows (1,1,1,1), (1,1,1,1), (1,1,1,1), (1,1,1,1)"
        def a = matrix(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        and: "b is a 4x4 matrix with rows (1,1,1,1), (1,1,1,1), (1,1,1,1), (1,1,1,1)"
        def b = matrix(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        then: "axb is a 4x4 matrix with rows (4,4,4,4), (4,4,4,4), (4,4,4,4), (4,4,4,4)"
        a * b == matrix(4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4)
    }

    def "transpose(a) transposes its argument."() {
        when: "a is a 4x4 matrix with rows (1, 2, 3, 4), (5, 6, 7, 8), (8, 6, 7, 5), (4, 3, 2, 1)"
        def a = matrix(1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4, 3, 2, 1)
        then: "transpose(a) is another 4x4 matrix with rows (1, 5, 8, 4), (2, 6, 7, 3), (3, 7, 6, 2), (4, 8, 5, 1)"
        a.transpose() == matrix(1, 5, 8, 4, 2, 6, 7, 3, 3, 7, 6, 2, 4, 8, 5, 1)
    }

    def "transpose(identity) produces the identity matrix"() {
        when: "i is the identity matrix"
        def i = Matrix.IDENTITY
        then: "transpose(identity) = identity"
        i.transpose() == Matrix.IDENTITY
    }

    def "Multiplying by the inverse produces original multiplicand."() {
        given: "a is a 4x4 matrix with rows (1, 2, 3, 4), (5, 6, 7, 8), (9, 8, 7, 6), (5, 4, 3, 2)"
        def a = matrix(1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2)
        and: "b is another 4x4 matrix with rows (2, 5, 8, 4), (3, 4, 2, 1), (1, 7, 6, 9), (0, 0, 0, 1)"
        def b = matrix(2, 5, 8, 4, 3, 4, 2, 1, 1, 7, 6, 9, 0, 0, 0, 1)
        when: "c = a × b"
        def c = a.multiply(b)
        and: "bi is the inverse of b"
        def bi = b.inverse()
        then: "c × inverse(b) = a"
        a * b == c
        and:
        a * b * bi == a
    }

    def "Multiplying a matrix by its inverse produces the identity matrix."() {
        when: "a is a 4x4 matrix with rows (2, 5, 8, 4), (3, 4, 2, 1), (1, 7, 6, 9), (0, 0, 0, 1)"
        def a = matrix(2, 5, 8, 4, 3, 4, 2, 1, 1, 7, 6, 9, 0, 0, 0, 1)
        then: "a × inverse(a) = identity"
        a * a.inverse() == Matrix.IDENTITY
    }

    def "The inverse of the identity matrix is the identity matrix"() {
        when: "a = inverse(identity)"
        def a = Matrix.IDENTITY.inverse()
        then: "a = identity"
        a == Matrix.IDENTITY
    }

    def "Identifying a non-invertible matrix."() {
        when: "a is a 4x4 matrix with rows (2, 0, 0, 0), (0, 0, 0, 0), (0, 0, 6, 0), (0, 0, 0, 1)"
        def a = matrix(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 1)
        then: "a is not invertible"
        !a.invertible()
    }

    def "Identifying an invertible matrix."() {
        when: "a is a 4x4 matrix with rows (2, 0, 0, 0), (0, 1, 0, 0), (0, 0, 6, 0), (0, 0, 0, 1)"
        def a = matrix(2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 6, 0, 0, 0, 0, 1)
        then: "a is invertible"
        a.invertible()
    }

    def "The determinant of the identity matrix is 1"() {
        when: "a = identity"
        def a = Matrix.IDENTITY
        then: "det(a) = 1"
        a.det() == 1
    }

    def "The determinant of a non-invertible matrix is 0."() {
        when: "a is a 4x4 matrix with rows (2, 0, 0, 0), (0, 0, 0, 0), (0, 0, 6, 0), (0, 0, 0, 1)"
        def a = matrix(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 1)
        then: "det(a) = 0"
        a.det() == 0
    }

    def "Finding the determinant of an invertible matrix."() {
        when: "a is a 4x4 matrix with rows (4, 2, 1, 4), (8, 6, 7, 5), (9, 7, 8, 6), (0, 0, 0, 1)"
        def a = matrix(4, 2, 1, 4, 8, 6, 7, 5, 9, 7, 8, 6, 0, 0, 0, 1)
        then: "det(a) = -4"
        a.det() == -4
    }

    def "Inverting a non-invertible matrix causes an error."() {
        given: "a is a 4x4 matrix with rows (2, 0, 0, 0), (0, 0, 0, 0), (0, 0, 6, 0), (0, 0, 0, 1)"
        def a = matrix(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 1)
        when: "inverse(a) is called"
        a.inverse()
        then: "inverse(a) causes an error"
        IllegalStateException e = thrown()
        e.getMessage() == "This matrix is not invertible!"
    }
}
