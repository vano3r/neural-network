package org.example.matrix;

import lombok.Getter;
import org.example.matrix.exception.NumberOfColumnsEqual;

public class Matrix {
    @Getter
    private final double[][] arrayData;

    public Matrix(int capacity) {
        arrayData = new double[capacity][capacity];
    }

    public Matrix(int rowCapacity, int columnCapacity) {
        arrayData = new double[rowCapacity][columnCapacity];
    }

    public Matrix(double[][] arrayData) {
        if (numberOfColumnsEqual(arrayData)) {
            this.arrayData = arrayData;
        } else {
            throw new NumberOfColumnsEqual("Количество столбцов должно быть равное");
        }

    }

    public Matrix(double[] arrayData) {
        this.arrayData = new double[1][arrayData.length];
        this.arrayData[0] = arrayData;
    }

    public int rowCount() {
        return arrayData.length;
    }

    public int columnCount() {
        return arrayData[0].length;
    }

    public double get(int rowIndex, int columnIndex) {
        return arrayData[rowIndex][columnIndex];
    }

    public void set(int rowIndex, int columnIndex, double number) {
        arrayData[rowIndex][columnIndex] = number;
    }

    private boolean numberOfColumnsEqual(double[][] array) {
        int initialColumnCount = array[0].length;
        for (double[] row : array) {
            if (row.length != initialColumnCount) {
                return false;
            }
        }

        return true;
    }

    public void add(Matrix matrix) {
        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                arrayData[i][j] += matrix.get(i, j);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (double[] row : arrayData) {
            result.append("|");
            for (double number : row) {
                result.append(number).append(" ");
            }
            result.setLength(result.length() - 1);
            result.append("|\n");
        }
        result.setLength(result.length() - 1);

        return result.toString();
    }
}
