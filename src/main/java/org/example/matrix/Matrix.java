package org.example.matrix;

import org.example.matrix.exception.NumberOfColumnsEqual;

import java.util.Arrays;

public class Matrix {
    double[][] arrayData;

    public Matrix(double[][] arrayData) {
        if (numberOfColumnsEqual(arrayData)) {
            this.arrayData = arrayData;
        } else {
            throw new NumberOfColumnsEqual("Количество столбцов должно быть равное");
        }

    }

    public int rowCount() {
        return arrayData.length;
    }

    public int columnCount() {
        return arrayData[0].length;
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
