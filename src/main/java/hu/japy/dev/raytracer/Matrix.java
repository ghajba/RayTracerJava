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
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                int r = 0;
                for (int c = 0; c < WIDTH; c++) {
                    r += at(i, c) * other.at(c, j);
                }
                data[position(i, j)] = r;
            }
        }
        return new Matrix(data);
    }

    public Tuple multiply(Tuple other) {
        // TODO dimension check!
        double[] tupleData = new double[WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            tupleData[i] = at(i, 0) * other.x + at(i, 1) * other.y + at(i, 2) * other.z + at(i, 3) * other.w;
        }
        return new Tuple(tupleData);
    }

    public double sumOfRow(int row) {
        double result = 0;
        for(int c = 0; c < WIDTH; c++) {
            result += at(row, c);
        }
        return result;
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
