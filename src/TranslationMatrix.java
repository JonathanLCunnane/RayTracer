public class TranslationMatrix extends Matrix{
    public TranslationMatrix(double x, double y, double z)
    {
        super(4, 4);
        double[] v = new double[]
                {
                        1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }
}