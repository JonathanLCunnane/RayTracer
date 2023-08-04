import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.OrientationMatrix;
import Matrices.ScalingMatrix;
import Matrices.TranslationMatrix;
import Matrices.YRotationMatrix;
import RayTracing.Material;
import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Objects.Sphere;
import RayTracing.Patterns.*;
import RayTracing.PointLight;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ThirdWorldRender {
    // This render will use very squashes spheres for walls, as I have not yet implemented planes or cubes.
    public static void main(String[] args) throws IOException
    {
        World w = new World();

        // Configure the floor as a plane.
        Material wallMaterial = new Material(
                new Colour(0.95, 0.9, 0.9),
                0.1, 0.9, 0, 200, 0 // The walls will be matte hence why sP = 0
        );
        ParentPattern ringRed = new RingPattern(
                new Colour(0.91, 0.627, 0.549),
                new Colour(0.961, 0.933, 0.914)
        );
        ParentPattern ringBlue = new RingPattern(
                new Colour(0.557, 0.788, 0.929),
                new Colour(0.961, 0.933, 0.914)
        );
        ringRed.transform = new ScalingMatrix(0.1, 0.1, 0.1);
        ringBlue.transform = new ScalingMatrix(0.1, 0.1, 0.1);
        wallMaterial.pattern = new CheckersPattern(ringRed, ringBlue);
        Plane floor = new Plane();
        floor.transform = new TranslationMatrix(0, -0.001, 0); // Avoids artifacts in patterns.
        floor.material = wallMaterial;

        // Configure the 3 spheres in the image.
        Sphere left = new Sphere();
        left.transform =
                new TranslationMatrix(-2, 0.33333, -0.75).times(
                new ScalingMatrix(0.33333, 0.33333, 0.33333));
        left.material =
                new Material(
                        new Colour(0.18, 0.702, 0.722),
                        0.1, 0.7, 0.3, 200, 0
                );
        Sphere middle = new Sphere();
        middle.transform = new TranslationMatrix(-0.5, 1, 0.5);
        middle.material =
                new Material(
                        new Colour(0.8, 0.263, 0.463),
                        0.1, 0.7, 0.3, 200, 0
                );
        ParentPattern middleGradientPattern = new GradientPattern(
                new Colour(0.812, 0.208, 0.416),
                new Colour(0.333, 0.204, 0.769)
        );
        middleGradientPattern.transform = new ScalingMatrix(2, 2, 2).times(new TranslationMatrix(0.5, 0, -0.5));
        middle.material.pattern = middleGradientPattern;
        Sphere right = new Sphere();
        right.transform =
                new TranslationMatrix(1.5, 0.5, -0.5).times(
                new ScalingMatrix(1, 0.5, 1));
        right.material =
                new Material(
                        new Colour(0.141, 0.302, 0.78),
                        0.1, 0.5, 0.2, 10, 0
                );
        ParentPattern stripeOne = new StripePattern(
                new Colour(0.1, 0.1, 0.1),
                new Colour(0.9, 0.9, 0.9)
        );
        stripeOne.transform = new ScalingMatrix(0.1, 0.1, 0.1);
        ParentPattern stripeTwo = new StripePattern(
                new Colour(0.1, 0.1, 0.1),
                new Colour(0.9, 0.9, 0.9)
        );
        stripeTwo.transform = new YRotationMatrix(Math.PI/2).times(new ScalingMatrix(0.1, 0.1, 0.1));
        right.material.pattern = new BlendedPattern(stripeOne, stripeTwo, right);
        w.objects = new ParentObject[]
                {
                        floor,
                        left,
                        middle,
                        right
                };
        w.light = new PointLight(
                new Point(-10, 10, -10),
                new Colour(1, 1, 1)
        );

        // Setup camera and render image
        Camera c = new Camera(1920, 1080, Math.PI/3);
        c.transform = new OrientationMatrix(
                new Point(0, 2, -5),
                new Point(0, 1, 0),
                new Vector(0, 1, 0)
        ).viewTransform();

        long startTime = System.currentTimeMillis();
        Canvas canvas = c.renderCanvas(w);
        long elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
        System.out.printf("Took %ds to render.%n", elapsedTimeInSeconds);

        String out = canvas.toPlainPPM();
        Path path = Paths.get(".\\thirdworldrender.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
        System.out.println("Done.");
    }
}
