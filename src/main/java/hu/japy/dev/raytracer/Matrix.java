package hu.japy.dev.raytracer;

import java.text.MessageFormat;
import java.util.Arrays;

public class Matrix {

    public static final int WIDTH = 4;
    public static final int HEIGHT = 4;

    public static final Matrix IDENTITY = new Matrix(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);

    final double[] data;

    public Matrix(double... data) {
        if (data.length != WIDTH * HEIGHT) {
            throw new IllegalArgumentException("You have to provide a 4x4 matrix");
        }
        this.data = data;
    }

    public double at(int row, int column) {
        if (row < 0 || row >= HEIGHT) {
            throw new IllegalArgumentException(MessageFormat.format("A matrix has {0} rows!", HEIGHT));
        }
        if (column < 0 || column >= WIDTH) {
            throw new IllegalArgumentException(MessageFormat.format("A matrix has {0} columns!", WIDTH));
        }
        return data[position(row, column)];
    }

    public Matrix multiply(Matrix other) {
        // TODO dimension check!
        double[] data = new double[WIDTH * HEIGHT];
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                int r = 0;
                for (int c = 0; c < WIDTH; c++) {
                    r += at(row, c) * other.at(c, col);
                }
                data[position(row, col)] = r;
            }
        }
        return new Matrix(data);
    }

    public Tuple multiply(Tuple other) {
        // TODO dimension check!
        double[] tupleData = new double[WIDTH];
        for (int row = 0; row < HEIGHT; row++) {
            tupleData[row] = at(row, 0) * other.x + at(row, 1) * other.y + at(row, 2) * other.z + at(row, 3) * other.w;
        }
        return new Tuple(tupleData);
    }

    /**
     * @param row the row num, we are interested in
     * @return the sum of the numbers in the given row of the matrix
     */
    public double sumOfRow(int row) {
        double result = 0;
        for(int col = 0; col < WIDTH; col++) {
            result += at(row, col);
        }
        return result;
    }

    public Matrix transpose() {
        // first row -> first column
        double[] newData = new double[WIDTH * HEIGHT];
        for(int row = 0; row < HEIGHT; row++) {
            for(int col = 0; col < WIDTH; col++) {
                newData[HEIGHT*col + row] = at(row, col);
            }
        }
        return new Matrix(newData);
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) o;

        return Arrays.equals(data, matrix.data);
    }

    @Override public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < HEIGHT; i++) {
            sb.append("[");
            for (int j = 0; j < WIDTH; j++) {
                sb.append(at(i, j));
                if (j != WIDTH - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    private int position(int row, int column) {
        return row * HEIGHT + column;
    }
}
