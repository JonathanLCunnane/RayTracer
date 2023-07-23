public class ShearingMatrix extends Matrix{
    public ShearingMatrix(double xY, double xZ, double yX, double yZ, double zX, double zY)
    // aB means the shearing factor for a with respect to b.
    {
        super(4, 4);
        double[] v = new double[]
                {
                        1, xY, xZ, 0, yX, 1, yZ, 0, zX, zY, 1, 0, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }
}
