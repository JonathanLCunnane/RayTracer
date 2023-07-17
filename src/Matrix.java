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

    private void initialiseMatrix(double[] values)
    {
        for (int r = 0; r < rows; r++)
        {
            System.arraycopy(values, (r * rows), matrix[r], 0, columns);
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
}
