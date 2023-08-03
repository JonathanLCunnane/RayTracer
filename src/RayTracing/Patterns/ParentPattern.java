package RayTracing.Patterns;

import Display.Colour;
import Matrices.IdentityMatrix;
import Matrices.Matrix;
import RayTracing.Objects.ParentObject;
import Tuples.Point;

public abstract class ParentPattern {
    public Matrix transform = new IdentityMatrix(4);
    public Colour colourAt(ParentObject object, Point worldPoint)
    {
        Point objectPoint = new Point(object.transform.inverse().times(worldPoint));
        Point patternPoint = new Point(transform.inverse().times(objectPoint));
        return localColourAt(patternPoint);
    }

    public abstract Colour localColourAt(Point localPoint);

}
