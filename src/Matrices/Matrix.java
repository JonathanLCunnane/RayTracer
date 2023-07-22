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
        if (rows != m.rows || columns != m.columns) return false;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                if ((matrix[r][c] - m.matrix[r][c]) > 0.00001) return false;
            }
        }
        return true;
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

    public void initialiseMatrix(double[] values)
    {
        for (int r = 0; r < rows; r++)
        {
            System.arraycopy(values, (r * columns), matrix[r], 0, columns);
        }
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

    public Matrix transposed()
    {
        Matrix result = new Matrix(columns, rows);
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                result.setAt(c, r, matrix[r][c]);
            }
        }
        return result;
    }

    public double determinant()
    {
        if (columns != rows)
        {
            throw new IllegalArgumentException("Non-square matrices do not have determinants.");
        }
        if (rows == 2)
        {
            return (matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]);
        }
        double det = 0;
        for (int c = 0; c < columns; c++)
        {
            det += matrix[0][c] * cofactor(0, c);
        }
        return det;
    }

    public Matrix submatrix(int row, int column)
    {
        Matrix result = new Matrix(rows - 1, columns - 1);
        int currResultRow = 0;
        int currResultColumn;
        for (int r = 0; r < rows; r++)
        {
            currResultColumn = 0;
            if (r == row) continue;

            for (int c = 0; c < columns; c++)
            {
                if (c == column) continue;
                result.setAt(currResultRow, currResultColumn, matrix[r][c]);
                currResultColumn++;
            }
            currResultRow++;
        }
        return result;
    }

    public double minor(int row, int column)
    {
        return submatrix(row, column).determinant();
    }

    public double cofactor(int row, int column)
    {
        if ((row + column) % 2 == 0) return submatrix(row, column).determinant();
        return -submatrix(row, column).determinant();
    }

    public boolean isInvertible()
    {
        return (determinant() != 0);
    }

    public Matrix inverse()
    {
        if (columns != rows)
        {
            throw new IllegalArgumentException("Non-square matrices do not have inverses.");
        }
        Matrix result = new Matrix(rows, columns);
        double det = determinant();
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                result.setAt(r, c, cofactor(r, c) / det);
            }
        }
        return result.transposed();
    }
}
