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
}
