package org.example.network.util;

import lombok.experimental.UtilityClass;
import org.example.matrix.Matrix;

@UtilityClass
public class ActivationFunction {
    public double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public Matrix sigmoid(Matrix matrix) {
        Matrix result = new Matrix(matrix.rowCount(), matrix.columnCount());

        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                result.set(i, j, sigmoid(matrix.get(i, j)));
            }
        }

        return result;
    }
}