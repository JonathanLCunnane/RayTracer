package RayTracing;

import Display.Colour;
import Matrices.ScalingMatrix;
import RayTracing.Objects.RayTracerObject;
import RayTracing.Objects.Sphere;
import Tuples.Point;

// The default world is one with two nested spheres.
public class DefaultWorld extends World {
    public DefaultWorld()
    {
        super(
                new RayTracerObject[] {
                        new Sphere(),
                        new Sphere()
                },
                new PointLight(
                        new Point(-10, 10, -10),
                        new Colour(1, 1, 1)
                )
        );
        setDefaultWorldObjectParameters();
    }

    private void setDefaultWorldObjectParameters()
    {
        objects[0].setMaterial(
                new Material(
                        new Colour(0.8, 1, 0.6),
                        0.1,
                        0.7,
                        0.2,
                        200
                )
        );
        objects[1].setTransform(
                new ScalingMatrix(0.5, 0.5, 0.5)
        );
    }
}
