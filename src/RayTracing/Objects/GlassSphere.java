package RayTracing.Objects;

public class GlassSphere extends Sphere{
    public GlassSphere()
    {
        super();
        material.transparency = 1;
        material.refractiveIndex = 1.52;
    }
}
