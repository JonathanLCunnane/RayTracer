package Display;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColourTest {
    @Test
    @DisplayName("Adding colours")
    public void colourAdditionTest()
    {
        Colour cOne = new Colour(0.9, 0.6, 0.75);
        Colour cTwo = new Colour(0.7, 0.1, 0.25);
        Colour cResult = new Colour(1.6, 0.7, 1.0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(cOne.plus(cTwo), cResult),
                () -> Assertions.assertEquals(cTwo.plus(cOne), cResult)
        );
    }

    @Test
    @DisplayName("Subtracting colours")
    public void colourSubtractionTest()
    {
        Colour cOne = new Colour(0.9, 0.6, 0.75);
        Colour cTwo = new Colour(0.7, 0.1, 0.25);
        Colour cResult = new Colour(0.2, 0.5, 0.5);

        Assertions.assertEquals(cOne.minus(cTwo), cResult);
    }

    @Test
    @DisplayName("Multiplying a colour by a scalar")
    public void colourScalarMultiplicationTest()
    {
        Colour c = new Colour(0.2, 0.3, 0.4);
        Colour cResult = new Colour(0.4, 0.6, 0.8);

        Assertions.assertEquals(c.scalarMultiply(2), cResult);
    }

    @Test
    @DisplayName("Dividing a colour by a scalar")
    public void colourScalarDivisionTest()
    {
        Colour c = new Colour(0.2, 0.3, 0.4);
        Colour cResult = new Colour(0.1, 0.15, 0.2);

        Assertions.assertEquals(c.scalarDivide(2), cResult);
    }

    @Test
    @DisplayName("Multiplying colours by each other")
    public void colourMultiplicationTest()
    {
        Colour cOne = new Colour(1, 0.2, 0.4);
        Colour cTwo = new Colour(0.9, 1, 0.1);
        Colour cResult = new Colour(0.9, 0.2, 0.04);

        Assertions.assertAll(
            () -> Assertions.assertEquals(cTwo.times(cOne), cResult),
            () -> Assertions.assertEquals(cOne.times(cTwo), cResult)
        );
    }
}
