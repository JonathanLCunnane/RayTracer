import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MaterialTest {
    @Test
    @DisplayName("The default material")
    public void defaultMaterial()
    {
        Material m = new Material();
        Assertions.assertAll(
                () -> Assertions.assertEquals(m.colour, new Colour(1, 1, 1)),
                () -> Assertions.assertEquals(m.ambient, 0.1),
                () -> Assertions.assertEquals(m.diffuse, 0.9),
                () -> Assertions.assertEquals(m.specular, 0.9),
                () -> Assertions.assertEquals(m.shininess, 200)
        );
    }
}
