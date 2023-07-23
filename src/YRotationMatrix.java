public class YRotationMatrix extends Matrix{
    public YRotationMatrix(double rad)
    {
        super(4, 4);
        double[] v = new double[]
                {
                        Math.cos(rad), 0, Math.sin(rad), 0, 0, 1, 0, 0, -Math.sin(rad), 0, Math.cos(rad), 0, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }
}
