package org.example.matrix;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.example.matrix.exception.MatrixUtilException;

import java.util.Random;

@UtilityClass
public class MatrixUtil {
    public Matrix sum(@NonNull Matrix firstMatrix, @NonNull Matrix secondMatrix) {
        return operator(firstMatrix, secondMatrix, Operator.SUM);
    }

    public Matrix sub(@NonNull Matrix firstMatrix, @NonNull Matrix secondMatrix) {
        return operator(firstMatrix, secondMatrix, Operator.SUB);
    }

    public Matrix sub(double reduced, @NonNull Matrix matrix) {
        Matrix result = new Matrix(matrix.rowCount(), matrix.columnCount());

        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                result.set(i, j, reduced - matrix.get(i, j));
            }
        }

        return result;
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
            throw new MatrixUtilException(
                    "Количество столбцов первой матрицы должно равняться количеству строк второй");

        return c;
    }

    public Matrix mul(double multiplier, Matrix matrix) {
        Matrix result = new Matrix(matrix.rowCount(), matrix.columnCount());

        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                result.set(i, j, multiplier * matrix.get(i, j));
            }
        }

        return result;
    }

    public Matrix scalar(Matrix firstMatrix, Matrix secondMatrix) {
        Matrix result = new Matrix(firstMatrix.rowCount(), secondMatrix.columnCount());

        for (int i = 0; i < firstMatrix.rowCount(); i++) {
            for (int j = 0; j < firstMatrix.columnCount(); j++) {
                result.set(i, j, firstMatrix.get(i, j) * secondMatrix.get(i, j));
            }
        }

        return result;
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

    //TODO реализовать рандомную функцию, для заполнения весов (Определить границы)
    public Matrix random(double start, double end, int rowCapacity, int columnCapacity) {
        Matrix result = new Matrix(rowCapacity, columnCapacity);

        Random random = new Random();

        for (int i = 0; i < result.rowCount(); i++) {
            for (int j = 0; j < result.columnCount(); j++) {
                double randomValue = start + (end - start) * random.nextDouble();
                result.set(i, j, randomValue);
            }
        }

        return result;
    }

    public int maxValue(Matrix matrix) {
        int result = 0;
        double temp = 0;

        for (int i = 0; i < matrix.rowCount(); i++) {
            for (int j = 0; j < matrix.columnCount(); j++) {
                if (matrix.get(i, j) > temp) {
                    temp = matrix.get(i, j);
                    result = i;
                }
            }
        }

        return result;
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
