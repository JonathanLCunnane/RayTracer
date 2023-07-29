package Matrices;

import Tuples.Point;
import Tuples.Vector;

public class OrientationMatrix extends Matrix {
    private Point fromVector;
    public OrientationMatrix(Point from, Point to, Vector up)
    // Given the points where the eye is looking from and to, and the upwards direction,
    // an orientation matrix that will transform the world can be made.
    // The orientation matrix must be multiplied by a translation in the opposite direction of 'forward' to get the viewTransform matrix.
    {
        super(4, 4);
        fromVector = from;
        Vector forward = new Vector(to.minus(from)).normalised();
        Vector upNormalised = up.normalised();
        Vector left = forward.cross(upNormalised);
        Vector trueUp = left.cross(forward);
        double[] v = new double[]
                {
                        left.x, left.y, left.z, 0, trueUp.x, trueUp.y, trueUp.z, 0, -forward.x, -forward.y, -forward.z, 0, 0, 0, 0, 1
                };
        initialiseMatrix(v);
    }

    public Matrix viewTransform()
    {
        return this.times(new TranslationMatrix(-fromVector.x, -fromVector.y, -fromVector.z));
    }
}
