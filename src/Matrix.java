import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    int rows;
    int columns;
    public Matrix(int rowNum, int columnNum)
    {
        rows = rowNum;
        columns = columnNum;
        matrix = new double[rows][columns];
    }

    public Matrix(int rowNum, int columnNum, double[] values) // Matrix fills up from left to right, then top to bottom.
                                                              // i.e. row by row.
    {
        rows = rowNum;
        columns = columnNum;
        matrix = new double[rows][columns];
        initialiseMatrix(values);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix m = (Matrix) o;
        return rows == m.rows && columns == m.columns && Arrays.deepEquals(matrix, m.matrix);
    }

    private void initialiseMatrix(double[] values)
    {
        for (int r = 0; r < rows; r++)
        {
            System.arraycopy(values, (r * columns), matrix[r], 0, columns);
        }
    }

    private Tuple toTuple(Matrix m) // This method will only be used privately, so is unprotected.
    {
        return new Tuple(
                m.valueAt(0, 0),
                m.valueAt(1, 0),
                m.valueAt(2, 0),
                m.valueAt(3, 0)
        );
    }

    public double valueAt(int row, int column)
    {
        return matrix[row][column];
    }

    public void setAt(int row, int column, double value)
    {
        matrix[row][column] = value;
    }

    public Matrix times(Matrix m)
    {
        if (columns != m.rows)
        {
            throw new IllegalArgumentException("Matrices with these dimensions cannot be multiplied.");
        }
        Matrix result = new Matrix(rows, m.columns);
        double currVal;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < m.columns; c++)
            {
                currVal = 0;
                for (int evalCounter = 0; evalCounter < m.rows; evalCounter++)
                {
                    currVal += matrix[r][evalCounter] * m.matrix[evalCounter][c];
                }
                result.setAt(r, c, currVal);
            }
        }
        return result;
    }

    public Tuple times(Tuple t)
    {
        double[] v = new double[]
                {
                        t.x, t.y, t.z, t.w
                };
        return toTuple(times(new Matrix(4, 1, v)));
    }
}
