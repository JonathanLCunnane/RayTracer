import Display.Camera;
import Display.Canvas;
import Display.Colour;
import Matrices.*;
import RayTracing.Material;
import RayTracing.Objects.Cube;
import RayTracing.Objects.ParentObject;
import RayTracing.Objects.Plane;
import RayTracing.Objects.Sphere;
import RayTracing.PointLight;
import RayTracing.World;
import Tuples.Point;
import Tuples.Vector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SixthWorldRender {
    public static void main(String[] args) throws IOException // This is the cover image for 'The Ray Tracer Challenge'
    {
        Camera cam = new Camera(1920, 1920, 0.785);
        cam.transform = new OrientationMatrix(
                new Point(-6, 6, -10),
                new Point(6, 0, 6),
                new Vector(-0.45, 1, 0)
        ).viewTransform();
        PointLight light = new PointLight(
                new Point(50, 100, -50),
                new Colour(1, 1, 1)
        );
        Material whiteMaterial = new Material();
        whiteMaterial.colour = new Colour(1, 1, 1);
        whiteMaterial.diffuse = 0.7;
        whiteMaterial.ambient = 0.1;
        whiteMaterial.specular = 0;
        whiteMaterial.reflectiveness = 0.1;
        Material blueMaterial = new Material();
        blueMaterial.colour = new Colour(0.537, 0.831, 0.914);
        blueMaterial.diffuse = 0.7;
        blueMaterial.ambient = 0.1;
        blueMaterial.specular = 0;
        blueMaterial.reflectiveness = 0.1;
        Material redMaterial = new Material();
        redMaterial.colour = new Colour(0.941, 0.322, 0.388);
        redMaterial.diffuse = 0.7;
        redMaterial.ambient = 0.1;
        redMaterial.specular = 0;
        redMaterial.reflectiveness = 0.1;
        Material purpleMaterial = new Material();
        purpleMaterial.colour = new Colour(0.373, 0.404, 0.550);
        purpleMaterial.diffuse = 0.7;
        purpleMaterial.ambient = 0.1;
        purpleMaterial.specular = 0;
        purpleMaterial.reflectiveness = 0.1;

        Matrix standardTransform = new ScalingMatrix(0.5, 0.5, 0.5).times(
                new TranslationMatrix(1, -1, 1)
        );
        Matrix largeObject = new ScalingMatrix(3.5, 3.5, 3.5).times(standardTransform);
        Matrix mediumObject = new ScalingMatrix(3, 3, 3).times(standardTransform);
        Matrix smallObject = new ScalingMatrix(2, 2, 2).times(standardTransform);

        // Backdrop
        Plane background = new Plane();
        background.material = new Material();
        background.material.colour = new Colour(1, 1, 1);
        background.material.ambient = 1;
        background.material.diffuse = 0;
        background.material.specular = 0;
        background.transform = new TranslationMatrix(0, 0, 500).times(
                new XRotationMatrix(Math.PI/2)
        );

        // Other scene objects
        Sphere sphere = new Sphere();
        sphere.material.colour = new Colour(0.373, 0.404, 0.550);
        sphere.material.diffuse = 0.2;
        sphere.material.ambient = 0;
        sphere.material.specular = 1;
        sphere.material.shininess = 200;
        sphere.material.reflectiveness = 0.7;
        sphere.material.transparency = 0.7;
        sphere.material.refractiveIndex = 1.5;
        sphere.transform = largeObject;

        Cube cubeOne = new Cube();
        cubeOne.material = whiteMaterial;
        cubeOne.transform = new TranslationMatrix(4, 0, 0).times(mediumObject);

        Cube cubeTwo = new Cube();
        cubeTwo.material = blueMaterial;
        cubeTwo.transform = new TranslationMatrix(8.5, 1.5, -0.5).times(largeObject);

        Cube cubeThree = new Cube();
        cubeThree.material = redMaterial;
        cubeThree.transform = new TranslationMatrix(0, 0, 4).times(largeObject);

        Cube cubeFour = new Cube();
        cubeFour.material = whiteMaterial;
        cubeFour.transform = new TranslationMatrix(4, 0, 4).times(smallObject);

        Cube cubeFive = new Cube();
        cubeFive.material = purpleMaterial;
        cubeFive.transform = new TranslationMatrix(7.5, 0.5, 4).times(mediumObject);

        Cube cubeSix = new Cube();
        cubeSix.material = whiteMaterial;
        cubeSix.transform = new TranslationMatrix(-0.25, 0.25, 8).times(mediumObject);

        Cube cubeSeven = new Cube();
        cubeSeven.material = whiteMaterial;
        cubeSeven.transform = new TranslationMatrix(0, -8, 4).times(largeObject);

        Cube cubeEight = new Cube();
        cubeEight.material = blueMaterial;
        cubeEight.transform = new TranslationMatrix(4, 1, 7.5).times(largeObject);

        Cube cubeNine = new Cube();
        cubeNine.material = redMaterial;
        cubeNine.transform = new TranslationMatrix(10, 2, 7.5).times(mediumObject);

        Cube cubeTen = new Cube();
        cubeTen.material = whiteMaterial;
        cubeTen.transform = new TranslationMatrix(8, 2, 12).times(smallObject);

        Cube cubeEleven = new Cube();
        cubeEleven.material = whiteMaterial;
        cubeEleven.transform = new TranslationMatrix(20, 1, 9).times(smallObject);

        Cube cubeTwelve = new Cube();
        cubeTwelve.material = blueMaterial;
        cubeTwelve.transform = new TranslationMatrix(-0.5, -5, 0.25).times(largeObject);

        Cube cubeThirteen = new Cube();
        cubeThirteen.material = redMaterial;
        cubeThirteen.transform = new TranslationMatrix(4, -4, 0).times(largeObject);

        Cube cubeFourteen = new Cube();
        cubeFourteen.material = whiteMaterial;
        cubeFourteen.transform = new TranslationMatrix(8.5, -4, 0).times(largeObject);

        Cube cubeFifteen = new Cube();
        cubeFifteen.material = whiteMaterial;
        cubeFifteen.transform = new TranslationMatrix(-0.5, -8.5, 8).times(largeObject);

        Cube cubeSixteen = new Cube();
        cubeSixteen.material = whiteMaterial;
        cubeSixteen.transform = new TranslationMatrix(0, -4, 4).times(largeObject);

        Cube cubeSeventeen = new Cube();
        cubeSeventeen.material = purpleMaterial;
        cubeSeventeen.transform = new TranslationMatrix(-0.5, -4.5, 8).times(largeObject);

        World w = new World();
        w.light = light;
        w.objects = new ParentObject[]
                {
                        background,
                        sphere,
                        cubeOne,
                        cubeTwo,
                        cubeThree,
                        cubeFour,
                        cubeFive,
                        cubeSix,
                        cubeSeven,
                        cubeEight,
                        cubeNine,
                        cubeTen,
                        cubeEleven,
                        cubeTwelve,
                        cubeThirteen,
                        cubeFourteen,
                        cubeFifteen,
                        cubeSixteen,
                        cubeSeventeen
                };

        long startTime = System.currentTimeMillis();
        Canvas canvas = cam.renderCanvas(w);
        long elapsedTimeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
        System.out.printf("Took %ds to render.%n", elapsedTimeInSeconds);

        String out = canvas.toPlainPPM();
        Path path = Paths.get(".\\sixthworldrender.ppm");
        Files.writeString(path, out, StandardCharsets.UTF_8);
        System.out.println("Done.");
    }
}
