package RayTracing.Objects;

public class GlassSphere extends Sphere{
    public GlassSphere()
    {
        super();
        material.transparency = 0.9;
        material.reflectiveness = 0.9;
        material.refractiveIndex = 1.52;
        material.diffuse = 0.1;
        material.ambient = 0.1;
        material.specular = 1;
        material.shininess = 300;
        material.castsShadow = false;
    }
}
