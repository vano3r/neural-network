package org.example.matrix;

import org.example.matrix.exception.MatrixUtilException;
import org.junit.Assert;
import org.junit.Test;

public class MatrixUtilsTest {
    private final Matrix A_1000 = MatrixUtil.random(-0.5, 0.5, 1000, 1000);
    private final Matrix B_1000 = MatrixUtil.random(-0.5, 0.5, 1000, 1000);

    @Test
    public void checkSum() {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
        });

        double[][] trueData = {
                {2, 4, 6},
                {2, 4, 6},
                {2, 4, 6},
        };

        Assert.assertArrayEquals(trueData, MatrixUtil.sum(a, a).getArrayData());
    }

    @Test
    public void checkSub() {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3},
        });

        double[][] trueData = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        Assert.assertArrayEquals(trueData, MatrixUtil.sub(a, a).getArrayData());
    }

    @Test
    public void checkMul() {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        double[][] trueData = {
                {76, 82},
                {184, 199},
                {292, 316},
        };

        Assert.assertArrayEquals(trueData, MatrixUtil.mul(a, b).getArrayData());
    }

    @Test
    public void checkTranspose() {
        Matrix matrix = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        double[][] trueData = {
                {10, 12, 14},
                {11, 13, 15}
        };

        Assert.assertArrayEquals(trueData, MatrixUtil.transpose(matrix).getArrayData());
    }

    @Test(expected = NullPointerException.class)
    public void checkSumWhereFirsMatrixReturnNotNullException() throws NullPointerException {
        Matrix a = null;
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sum(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkSumWhereSecondMatrixReturnNotNullException() throws NullPointerException {
        Matrix b = null;
        Matrix a = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sum(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkSubWhereFirsMatrixReturnNotNullException() throws NullPointerException {
        Matrix a = null;
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sub(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkSubWhereSecondMatrixReturnNotNullException() throws NullPointerException {
        Matrix b = null;
        Matrix a = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sub(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkMulWhereFirsMatrixReturnNotNullException() throws NullPointerException {
        Matrix a = null;
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.mul(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkMulWhereSecondMatrixReturnNotNullException() throws NullPointerException {
        Matrix b = null;
        Matrix a = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.mul(a, b);
    }

    @Test(expected = NullPointerException.class)
    public void checkTransposeWhereMatrixReturnNotNullException() throws NullPointerException {
        Matrix b = null;
        MatrixUtil.transpose(b);
    }

    @Test(expected = MatrixUtilException.class)
    public void checkSumMatrixUtilException() throws MatrixUtilException {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sum(a, b);
    }

    @Test(expected = MatrixUtilException.class)
    public void checkSubMatrixUtilException() throws MatrixUtilException {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        Matrix b = new Matrix(new double[][]{
                {10, 11},
                {12, 13},
                {14, 15},
        });

        MatrixUtil.sub(a, b);
    }

    @Test(expected = MatrixUtilException.class)
    public void checkMulMatrixUtilException() throws MatrixUtilException {
        Matrix a = new Matrix(new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        });
        Matrix b = new Matrix(new double[][]{
                {10, 11, 3},
                {12, 13, 3}
        });

        MatrixUtil.mul(a, b);
    }

    @Test(timeout = 1000)
    public void checkMulTime() {
        MatrixUtil.mul(A_1000, B_1000);
    }

    @Test(timeout = 50)
    public void checkSumTime() {
        MatrixUtil.sum(A_1000, B_1000);
    }

    @Test(timeout = 50)
    public void checkSubTime() {
        MatrixUtil.sub(A_1000, B_1000);
    }
    @Test(timeout = 50)
    public void checkScalarTime() {
        MatrixUtil.scalar(A_1000, B_1000);
    }
    @Test(timeout = 20)
    public void checkTransposeTime() {
        MatrixUtil.transpose(A_1000);
    }
}
