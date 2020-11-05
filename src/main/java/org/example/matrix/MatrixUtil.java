package org.example.matrix;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.example.matrix.exception.MatrixUtilException;
import org.example.matrix.exception.Operator;

@UtilityClass
public class MatrixUtil {
    public Matrix sum(@NonNull Matrix firstMatrix, @NonNull Matrix secondMatrix) {
        return operator(firstMatrix, secondMatrix, Operator.SUM);
    }

    public Matrix sub(@NonNull Matrix firstMatrix, @NonNull Matrix secondMatrix) {
        return operator(firstMatrix, secondMatrix, Operator.SUB);
    }

    public Matrix mul(@NonNull Matrix firstMatrix, @NonNull Matrix secondMatrix) {
        Matrix c;
        if (firstMatrix.columnCount() == secondMatrix.rowCount()) {
            c = new Matrix(firstMatrix.rowCount(), secondMatrix.columnCount());

            for (int i = 0; i < firstMatrix.rowCount(); i++) {
                for (int j = 0; j < secondMatrix.columnCount(); j++) {
                    for (int k = 0; k < secondMatrix.rowCount(); k++) {
                        c.set(i, j, (c.get(i, j) + firstMatrix.get(i, k) * secondMatrix.get(k, j)));
                    }
                }
            }
        } else
            throw new MatrixUtilException("Количество столбцов первой матрицы должно равняться количеству строк второй");

        return c;
    }

    public Matrix transpose(@NonNull Matrix matrix) {
        Matrix c = new Matrix(matrix.columnCount(), matrix.rowCount());

        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                c.set(j, i, matrix.get(i, j));
            }
        }

        return c;
    }

    private Matrix operator(Matrix a, Matrix b, Operator op) {
        Matrix c;
        if (equalCapacity(a, b)) {
            c = new Matrix(a.rowCount(), a.columnCount());

            for (int i = 0; i < c.rowCount(); i++) {
                for (int j = 0; j < c.columnCount(); j++) {
                    if (op.equals(Operator.SUM)) {
                        c.set(i, j, (a.get(i, j) + b.get(i, j)));
                    } else {
                        c.set(i, j, (a.get(i, j) - b.get(i, j)));
                    }
                }
            }

        } else throw new MatrixUtilException("Матрицы разного размера");

        return c;
    }

    private boolean equalCapacity(Matrix a, Matrix b) {
        return a.rowCount() == b.rowCount() && a.columnCount() == b.columnCount();
    }
}
