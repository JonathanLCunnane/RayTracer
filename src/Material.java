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

    public Colour lightningAtPoint(PointLight light, Point point, Vector eyeV, Vector normalV) // The vectors normalV and eyeV are pointing away from 'point', and are normalised.
    {
        Colour effectiveColour = colour.times(light.intensity);
        Vector lightV = new Vector(light.position.minus(point)).normalised();
        Colour ambientComponent = effectiveColour.scalarMultiply(ambient);
        Colour diffuseComponent;
        Colour specularComponent;
        double lightDotNormal = lightV.dot(normalV); // If this is negative, the light is behind the object, so the diffuse and specular components are zero.
        // Diffuse and specular components
        if (lightDotNormal < 0)
        {
            diffuseComponent = new Colour();
            specularComponent = new Colour();
        }
        else
        {
            diffuseComponent = effectiveColour.scalarMultiply(diffuse).scalarMultiply(lightDotNormal);
            // Specular component.
            Vector reflectionV = lightV.scalarMultiply(-1).reflect(normalV);
            double reflectionDotEye = reflectionV.dot(eyeV);
            if (reflectionDotEye < 0)
            {
                specularComponent = new Colour();
            }
            else
            {
                specularComponent = effectiveColour.scalarMultiply(specular).scalarMultiply(Math.pow(reflectionDotEye, shininess));
            }
        }
        return ambientComponent.plus(diffuseComponent).plus(specularComponent);
    }
}
