import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatrixTest {
    @Test
    @DisplayName("A 2x2 matrix should be representable")
    public void twoTwoMatrix()
    {
        double[] v = new double[]
                {
                        -3, 5, 1, -2
                };
        Matrix m = new Matrix(2, 2, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.valueAt(0, 0), -3),
                () -> Assertions.assertEquals(m.valueAt(0, 1), 5),
                () -> Assertions.assertEquals(m.valueAt(1, 0), 1),
                () -> Assertions.assertEquals(m.valueAt(1, 1), -2)
        );
    }

    @Test
    @DisplayName("A 3x3 matrix should be representable")
    public void threeThreeMatrix()
    {
        double[] v = new double[]
                {
                        -3, 5, 0, 1, -2, -7, 0, 1, 1
                };
        Matrix m = new Matrix(3, 3, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.valueAt(0, 0), -3),
                () -> Assertions.assertEquals(m.valueAt(1, 1), -2),
                () -> Assertions.assertEquals(m.valueAt(2, 1), 1),
                () -> Assertions.assertEquals(m.valueAt(2, 2), 1)
        );
    }

    @Test
    @DisplayName("A 4x4 matrix should be representable")
    public void fourFourMatrix()
    {
        double[] v = new double[]
                {
                        1, 2, 3, 4, 5.5, 6.5, 7.5, 8.5, 9, 10, 11, 12, 13.5, 14.5, 15.5, 16.5
                };
        Matrix m = new Matrix(4, 4, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.valueAt(0, 0), 1),
                () -> Assertions.assertEquals(m.valueAt(0, 3), 4),
                () -> Assertions.assertEquals(m.valueAt(1, 0), 5.5),
                () -> Assertions.assertEquals(m.valueAt(1, 2), 7.5),
                () -> Assertions.assertEquals(m.valueAt(2, 2), 11),
                () -> Assertions.assertEquals(m.valueAt(3, 0), 13.5),
                () -> Assertions.assertEquals(m.valueAt(3, 2), 15.5)
        );
    }

    @Test
    @DisplayName("Matrices with identical data should be equal")
    public void equalityTest()
    {
        double[] vOne = new double[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
                };
        double[] vTwo = new double[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
                };
        Matrix mOne = new Matrix(4, 4, vOne);
        Matrix mTwo = new Matrix(4, 4, vTwo);
        Assertions.assertEquals(mOne, mTwo);
    }

    @Test
    @DisplayName("Matrices with different data should not be equal")
    public void inequalityTest()
    {
        double[] vOne = new double[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
                };
        double[] vTwo = new double[]
                {
                        16, 2, 15, 4, 14, 6, 13, 8, 12, 10, 11, 1, 7, 3, 9, 5
                };
        Matrix mOne = new Matrix(4, 4, vOne);
        Matrix mTwo = new Matrix(4, 4, vTwo);
        Assertions.assertNotEquals(mOne, mTwo);
    }

    @Test
    @DisplayName("Matrices should be able to be multiplied correctly")
    public void matrixMultiplication()
    {
        double[] vOne = new double[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2
                };
        double[] vTwo = new double[]
                {
                        -2, 1, 2, 3, 3, 2, 1, -1, 4, 3, 6, 5, 1, 2, 7, 8
                };
        double[] vResult = new double[]
                {
                        20, 22, 50, 48, 44, 54, 114, 108, 40, 58, 110, 102, 16, 26, 46, 42
                };
        Matrix mOne = new Matrix(4, 4, vOne);
        Matrix mTwo = new Matrix(4, 4, vTwo);
        Tuple t = new Tuple(1, 2, 3, 1);
        Matrix mResult = new Matrix(4, 4, vResult);
        Tuple tResult = new Tuple(18, 46, 52, 24);
        Assertions.assertAll(
                () -> Assertions.assertEquals(mOne.times(mTwo), mResult), // Multiplying two matrices to get a new matrix.
                () -> Assertions.assertEquals(mOne.times(t), tResult) // Multiplying a matrix by a tuple to get a new tuple.
        );
    }

    @Test
    @DisplayName("Matrices and tuples multiplied by the identity matrix should not change")
    public void identityMultiplication()
    {
        double[] v = new double[]
                {
                        1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2
                };
        Matrix i = new IdentityMatrix(4);
        Matrix m = new Matrix(4, 4, v);
        Tuple t = new Tuple(1, 2, 3, 4);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.times(i), m),
                () -> Assertions.assertEquals(i.times(m), m),
                () -> Assertions.assertEquals(i.times(t), t)
        );
    }

    @Test
    @DisplayName("Matrices should be correctly transposed")
    public void matrixTransposition()
    {
        double[] v = new double[]
                {
                        0, 9, 3, 0, 9, 8, 0, 8, 1, 8, 5, 3, 0, 0, 5, 8
                };
        double[] r = new double[]
                {
                        0, 9, 1, 0, 9, 8, 8, 0, 3, 0, 5, 5, 0, 8, 3, 8
                };
        Matrix mInitial = new Matrix(4, 4, v);
        Matrix mResult = new Matrix(4, 4, r);
        Assertions.assertEquals(mInitial.transposed(), mResult);
    }
}
