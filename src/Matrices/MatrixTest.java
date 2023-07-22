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

    @Test
    @DisplayName("Determinant of a 2x2 matrix")
    public void twoTwoDeterminant()
    {
        double[] v = new double[]
                {
                        1, 5, -3, 2
                };
        Matrix m = new Matrix(2, 2, v);
        Assertions.assertEquals(m.determinant(), 17);
    }

    @Test
    @DisplayName("Determinant of a 3x3 matrix")
    public void threeThreeDeterminant()
    {
        double[] v = new double[]
                {
                        1, 2, 6, -5, 8, -4, 2, 6, 4
                };
        Matrix m = new Matrix(3, 3, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.cofactor(0, 0), 56),
                () -> Assertions.assertEquals(m.cofactor(0, 1), 12),
                () -> Assertions.assertEquals(m.cofactor(0, 2), -46),
                () -> Assertions.assertEquals(m.determinant(), -196)
        );
    }

    @Test
    @DisplayName("Determinant of a 4x4 matrix")
    public void fourFourDeterminant()
    {
        double[] v = new double[]
                {
                        -2, -8, 3, 5, -3, 1, 7, 3, 1, 2, -9, 6, -6, 7, 7, -9
                };
        Matrix m = new Matrix(4, 4, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.cofactor(0, 0), 690),
                () -> Assertions.assertEquals(m.cofactor(0, 1), 447),
                () -> Assertions.assertEquals(m.cofactor(0, 2), 210),
                () -> Assertions.assertEquals(m.cofactor(0, 3), 51),
                () -> Assertions.assertEquals(m.determinant(), -4071)
        );
    }

    @Test
    @DisplayName("Submatrices of matrices")
    public void submatrices()
    {
        double[] vOne = new double[]
                {
                        1, 5, 0, -3, 2, 7, 0, 6, -3
                };
        double[] vTwo = new double[]
                {
                        -6, 1, 1, 6, -8, 5, 8, 6, -1, 0, 8, 2, -7, 1, -1, 1
                };
        double[] rOne = new double[]
                {
                        -3, 2, 0, 6
                };
        double[] rTwo = new double[]
                {
                        -6, 1, 6, -8, 8, 6, -7, -1, 1
                };
        Matrix mOne = new Matrix(3, 3, vOne);
        Matrix mTwo = new Matrix(4, 4, vTwo);
        Matrix mROne = new Matrix(2, 2, rOne);
        Matrix mRTwo = new Matrix(3, 3, rTwo);
        Assertions.assertAll(
                () -> Assertions.assertEquals(mOne.submatrix(0, 2), mROne),
                () -> Assertions.assertEquals(mTwo.submatrix(2, 1), mRTwo)
        );
    }

    @Test
    @DisplayName("The minor of a matrix")
    public void minor()
    {
        double[] v = new double[]
                {
                        3, 5, 0, 2, -1, -7, 6, -1, 5
                };
        Matrix m = new Matrix(3, 3, v);
        Matrix subM = m.submatrix(1, 0);
        Assertions.assertEquals(m.minor(1, 0), subM.determinant());
    }

    @Test
    @DisplayName("The cofactors of a matrix")
    public void cofactors()
    {
        double[] v = new double[]
                {
                        3, 5, 0, 2, -1, -7, 6, -1, 5
                };
        Matrix m = new Matrix(3, 3, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.minor(0, 0), -12),
                () -> Assertions.assertEquals(m.cofactor(0, 0), -12),
                () -> Assertions.assertEquals(m.minor(1, 0), 25),
                () -> Assertions.assertEquals(m.cofactor(1, 0), -25)
        );
    }

    @Test
    @DisplayName("An invertible matrix should be invertible")
    public void invertibleMatrix()
    {
        double[] v = new double[]
                {
                        6, 4, 4, 4, 5, 5, 7, 6, 4, -9, 3, -7, 9, 1, 7, -6
                };
        Matrix m = new Matrix(4, 4, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.determinant(), -2120),
                () -> Assertions.assertTrue(m.isInvertible())
        );
    }

    @Test
    @DisplayName("An non-invertible matrix should be non-invertible")
    public void nonInvertibleMatrix()
    {
        double[] v = new double[]
                {
                        -4, 2, -2, -3, 9, 6, 2, 6, 0, -5, 1, -5, 0, 0, 0, 0
                };
        Matrix m = new Matrix(4, 4, v);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.determinant(), 0),
                () -> Assertions.assertFalse(m.isInvertible())
        );
    }

    @Test
    @DisplayName("Inverting a matrix")
    public void inverseMatrix()
    {
        double[] v = new double[]
                {
                        -5, 2, 6, -8, 1, -5, 1, 8, 7, 7, -6, -7, 1, -3, 7, 4
                };
        double[] r = new double[]
                {
                        0.21805, 0.45113, 0.24060, -0.04511, -0.80827, -1.45677, -0.44361, 0.52068, -0.07895, -0.22368,
                        -0.05263, 0.19737, -0.52256, -0.81391, -0.30075, 0.30639
                };
        Matrix m = new Matrix(4, 4, v);
        Matrix mInv = m.inverse();
        Matrix mResult = new Matrix(4, 4, r);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.determinant(), 532),
                () -> Assertions.assertEquals(m.cofactor(2, 3), -160),
                () -> Assertions.assertEquals(m.cofactor(3, 2), 105),
                () -> Assertions.assertEquals(mInv.valueAt(3, 2), (double) -160/532),
                () -> Assertions.assertEquals(mInv.valueAt(2, 3), (double) 105/532),
                () -> Assertions.assertEquals(mInv, mResult)
        );
    }

    @Test
    @DisplayName("Translation matrices")
    public void translationMatrices()
    {
        Matrix m = new TranslationMatrix(5, -3, 2);
        Point p = new Point(-3, 4, 5);
        Vector v = new Vector(-3, 4, 5);
        Point pTransformed = new Point(2, 1, 7);
        Point pInverseTransformed = new Point(-8, 7, 3);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.times(p), pTransformed),
                () -> Assertions.assertEquals(m.inverse().times(p), pInverseTransformed),
                () -> Assertions.assertEquals(m.times(v), v) // Vectors are unaffected by translations.
        );
    }

    @Test
    @DisplayName("Scaling matrices")
    public void scalingMatrices()
    {
        Matrix m = new ScalingMatrix(2, 3, 4);
        Point p = new Point(-4, 6, 8);
        Vector v = new Vector(-4, 6, 8);
        Point pTransformed = new Point(-8, 18, 32);
        Vector vTransformed = new Vector(-8, 18, 32);
        Vector vInverseTransformed = new Vector(-2, 2, 2);
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.times(p), pTransformed),
                () -> Assertions.assertEquals(m.inverse().times(v), vInverseTransformed),
                () -> Assertions.assertEquals(m.times(v), vTransformed)
        );
    }
}
