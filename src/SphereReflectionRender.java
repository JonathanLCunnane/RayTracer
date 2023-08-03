import Display.Canvas;
import Display.Colour;
import RayTracing.Intersections;
import RayTracing.PointLight;
import RayTracing.Ray;
import RayTracing.Objects.Sphere;
import Tuples.Point;
import Tuples.Vector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SphereReflectionRender {
    public static void main(String[] args) throws IOException
    {
        Sphere s = new Sphere();
        s.material.colour = new Colour(1, 0.2, 1);
        PointLight light = new PointLight(
                new Point(-10, 10, -10),
                new Colour(1, 1,1)
        );
        Point rayOrigin = new Point(0, 0, -5);
        double wallZ = 10;
        double wallSize = 7;
        double halfWallSize = wallSize / 2;
        int canvasSize = 256;
        double pixelWidth = wallSize / canvasSize;
        Canvas c = new Canvas(canvasSize, canvasSize);
        for (int canvasY = 0; canvasY < canvasSize; canvasY++)
        {
            double rayDestinationOnWallY = halfWallSize - canvasY * pixelWidth;
            for (int canvasX = 0; canvasX < canvasSize; canvasX++)
            {
                double rayDestinationOnWallX = canvasX * pixelWidth - halfWallSize;
                Point rayDestinationOnWall = new Point(rayDestinationOnWallX, rayDestinationOnWallY, wallZ);
                Ray r = new Ray(
                        rayOrigin,
                        new Vector(rayDestinationOnWall.minus(rayOrigin))
                );
                Intersections xs = s.intersections(r);
                if (xs.hit() != null)
                {
                    Point point = new Point(r.position(xs.hit().time));
                    Colour colour = xs.hit().object.material.lightningAtPoint(
                            light,
                            point,
                            r.direction.normalised().scalarMultiply(-1),
                            xs.hit().object.normalAt(point),
                            xs.hit().object
                    );
                    c.writeToCanvas(colour, canvasX, canvasY);
                }
            }
        }

        String out = c.toPlainPPM();
        Path path = Paths.get(".\\spherereflection.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
    }
}
