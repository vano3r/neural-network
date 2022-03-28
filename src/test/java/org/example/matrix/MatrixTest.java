package org.example.matrix;

import org.example.matrix.exception.NumberOfColumnsEqual;
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {

    @Test
    public void newMatrixShouldBeSquare() {
        Matrix matrix = new Matrix(10);
        Assert.assertEquals(10, matrix.rowCount());
        Assert.assertEquals(10, matrix.columnCount());
    }

    @Test
    public void newMatrixNotShouldBeSquare() {
        Matrix matrix = new Matrix(10, 15);
        Assert.assertEquals(10, matrix.rowCount());
        Assert.assertEquals(15, matrix.columnCount());
    }

    @Test
    public void numberOfColumnsShouldEqual() {
        double[][] testData = {
                {1, 2, 3, 4},
                {1, 2, 3, 4},
                {1, 2, 3, 4}
        };
        Matrix matrix = new Matrix(testData);
        Assert.assertArrayEquals(testData, matrix.getArrayData());
    }

    @Test(expected = NumberOfColumnsEqual.class)
    public void numberOfColumnsNotShouldEqual() throws NumberOfColumnsEqual {
        double[][] testData = {
                {1, 2, 3, 4},
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4}
        };
        new Matrix(testData);
    }

    @Test
    public void setShouldBeTrue() {
        double[][] testData = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        double[][] trueData = {
                {0, 0, 0, 0},
                {0, 0, 99, 0},
                {0, 0, 0, 0}
        };

        Matrix matrix = new Matrix(testData);
        matrix.set(1, 2, 99);
        Assert.assertArrayEquals(trueData, matrix.getArrayData());
    }

    @Test
    public void getShouldBeTrue() {
        double[][] testData = {
                {0, 0, 0, 0},
                {0, 0, 99, 0},
                {0, 0, 0, 0}
        };
        Matrix matrix = new Matrix(testData);
        Assert.assertEquals(99, matrix.get(1, 2), 1e-9);
    }

    @Test
    public void toStringShouldBeTrue() {
        double[][] testData = {
                {0, 0, 0, 0},
                {0, 0, 99, 0},
                {0, 0, 0, 0}
        };
        String trueData
                = "|0.0 0.0 0.0 0.0|\n"
                + "|0.0 0.0 99.0 0.0|\n"
                + "|0.0 0.0 0.0 0.0|";

        Matrix matrix = new Matrix(testData);
        Assert.assertEquals(trueData, matrix.toString());
    }

}
