import java.util.Objects;

public class Material {
    // Materials will be based off the Phong reflection model.
    public Colour colour;
    public double ambient; // The light reflected from other objects in the environment. Between 0 and 1.
    public double diffuse; // The light reflected from a matte surface. Between 0 and 1.
    public double specular; // The reflection of the light source itself. Between 0 and 1.
    public double shininess; // The higher the shininess the smaller and tighter the specular highlight. Above 0. Recommended to be between 10 and 200.
    public Material()
    {
        colour = new Colour(1, 1, 1);
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Double.compare(material.ambient, ambient) == 0 && Double.compare(material.diffuse, diffuse) == 0 && Double.compare(material.specular, specular) == 0 && Double.compare(material.shininess, shininess) == 0 && Objects.equals(colour, material.colour);
    }

    public Material(Colour c, double a, double d, double sP, double sH)
    {
        colour = c;
        ambient = a;
        diffuse = d;
        specular = sP;
        shininess = sH;
    }
}
