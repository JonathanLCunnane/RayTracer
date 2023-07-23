public class IdentityMatrix extends Matrix{
    public IdentityMatrix(int size)
    {
        super(size, size);
        for (int idx = 0; idx < size; idx++)
        {
            setAt(idx, idx, 1);
        }
    }
}
