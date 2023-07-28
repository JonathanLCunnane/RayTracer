package RayTracing;

import RayTracing.Objects.Sphere;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntersectionTest {
    @Test
    @DisplayName("An intersection should be properly represented")
    public void intersection()
    {
        Sphere s = new Sphere();
        Intersection i = new Intersection(s, 3.5);
        Assertions.assertAll(
                () -> Assertions.assertEquals(i.time, 3.5),
                () -> Assertions.assertEquals(i.object, s)
        );
    }
}
