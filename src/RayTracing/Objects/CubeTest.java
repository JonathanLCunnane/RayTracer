package RayTracing.Objects;

import RayTracing.Intersections;
import RayTracing.Ray;
import Tuples.Point;
import Tuples.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CubeTest {
    @Test
    @DisplayName("Rays intersecting a cube")
    public void rayIntersections()
    {
        Cube c = new Cube();
        Ray[] rays = new Ray[]
                {
                        new Ray(
                                new Point(5, 0.5, 0),
                                new Vector(-1, 0, 0)
                        ),
                        new Ray(
                                new Point(-5, 0.5, 0),
                                new Vector(1, 0, 0)
                        ),
                        new Ray(
                                new Point(0.5, 5, 0),
                                new Vector(0, -1, 0)
                        ),
                        new Ray(
                                new Point(0.5, -5, 0),
                                new Vector(0, 1, 0)
                        ),
                        new Ray(
                                new Point(0.5, 0, 5),
                                new Vector(0, 0, -1)
                        ),
                        new Ray(
                                new Point(0.5, 0, -5),
                                new Vector(0, 0, 1)
                        ),
                        new Ray(
                                new Point(0, 0.5, 0),
                                new Vector(0, 0, 1)
                        )
                };
        double[][] tResults = new double[][]
                {
                        {4, 6},
                        {4, 6},
                        {4, 6},
                        {4, 6},
                        {4, 6},
                        {4, 6},
                        {-1, 1}
                };
        for (int i = 0; i < rays.length; i++)
        {
            Ray curr = rays[i];
            double[] currTs = tResults[i];
            Intersections xs = c.localIntersections(curr);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(xs.size, 2),
                    () -> Assertions.assertEquals(xs.intersections[0].time, currTs[0]),
                    () -> Assertions.assertEquals(xs.intersections[1].time, currTs[1])
            );
        }
    }

    @Test
    @DisplayName("Rays missing a cube")
    public void rayMisses() {
        Cube c = new Cube();
        Ray[] rays = new Ray[]
                {
                        new Ray(
                                new Point(-2, 0, 0),
                                new Vector(0.2673, 0.5345, 0.8018)
                        ),
                        new Ray(
                                new Point(0, -2, 0),
                                new Vector(0.8018, 0.2673, 0.5345)
                        ),
                        new Ray(
                                new Point(0, 0, -2),
                                new Vector(0.5345, 0.8018, 0.2673)
                        ),
                        new Ray(
                                new Point(2, 0, 2),
                                new Vector(0, 0, -1)
                        ),
                        new Ray(
                                new Point(0, 2, 2),
                                new Vector(0, -1, 0)
                        ),
                        new Ray(
                                new Point(2, 2, 0),
                                new Vector(-1, 0, 0)
                        )
                };
        for (Ray curr : rays) {
            Intersections xs = c.localIntersections(curr);
            Assertions.assertEquals(xs.size, 0);
        }
    }

    @Test
    @DisplayName("The normal on the surface of a cube")
    public void cubeNormals()
    {
        Cube c = new Cube();
        Point[] points = new Point[]
                {
                        new Point(1, 0.5, -0.8),
                        new Point(-1, -0.2, 0.9),
                        new Point(-0.4, 1, -0.1),
                        new Point(0.3, -1, -0.7),
                        new Point(-0.6, 0.3, 1),
                        new Point(0.4, 0.4, -1),
                        new Point(1, 1, 1),
                        new Point(-1, -1, -1)
                };
        Vector[] normals = new Vector[]
                {
                        new Vector(1, 0, 0),
                        new Vector(-1, 0, 0),
                        new Vector(0, 1, 0),
                        new Vector(0, -1, 0),
                        new Vector(0, 0, 1),
                        new Vector(0, 0, -1),
                        new Vector(1, 0, 0),
                        new Vector(-1, 0, 0)
                };
        for (int i = 0; i < points.length; i++)
        {
            Point currP = points[i];
            Vector currN = normals[i];
            Assertions.assertEquals(c.localNormalAt(currP), currN);
        }
    }
}
