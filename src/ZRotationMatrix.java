public class ZRotationMatrix extends Matrix{
    public ZRotationMatrix(double rad)
    {
        super(4, 4);
        double[] v = new double[]
                {
                        Math.cos(rad), -Math.sin(rad), 0, 0, Math.sin(rad), Math.cos(rad), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }
}
