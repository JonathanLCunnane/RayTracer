package Matrices;

public class ScalingMatrix extends Matrix {
    public ScalingMatrix(double x, double y, double z)
    {
        super(4, 4);
        double[] v = new double[]
                {
                        x, 0, 0, 0, 0, y, 0, 0, 0, 0, z, 0, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }
}
