import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.OrientationMatrix;
import Matrices.ScalingMatrix;
import Matrices.TranslationMatrix;
import RayTracing.Objects.GlassSphere;
import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Patterns.RingPattern;
import RayTracing.PointLight;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FifthWorldRender {
    public static void main(String[] args) throws IOException
    {
        World w = new World();

        ParentObject floor = new Plane();
        floor.material.pattern = new RingPattern(new Colour(0.5, 0.1, 0.1), new Colour(1, 1, 1));
        ParentObject sphere = new GlassSphere();
        sphere.transform = new TranslationMatrix(10, 30, 0).times(new ScalingMatrix(3, 3, 3));
        sphere.material.colour = new Colour(0.1, 0.1, 0.1);

        w.objects = new ParentObject[]
                {
                        floor,
                        sphere
                };

        w.light = new PointLight(
                new Point(15, 40, 5),
                new Colour(1, 1, 1)
        );

        // Setup camera and render image.
        Camera c = new Camera(1024, 1024, Math.PI/3);
        c.transform = new OrientationMatrix(
                new Point(10, 38, 0),
                new Point(10, 0, 0),
                new Vector(1, 0, 0)
        ).viewTransform();

        long startTime = System.currentTimeMillis();
        Canvas canvas = c.renderCanvas(w);
        long elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
        System.out.printf("Took %ds to render.%n", elapsedTimeInSeconds);

        String out = canvas.toPlainPPM();
        Path path = Paths.get(".\\fifthworldrender.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
        System.out.println("Done.");
    }
}
