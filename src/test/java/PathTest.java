import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathTest {

  private final double errorMargin = 0.001;

  @Test
  public void testPathFromPointsEquation() {
    Path path = PathUtil.createFromPoints(
        72, 36,
        130, 292,
        223, -86,
        223, 289
    );

    assertEquals("P'(0) should be roughly equal to 128 / 29", 128.0 / 29.0,
        path.getDerivative().apply(0.0), errorMargin);
    assertEquals("P'(5) should be roughly equal to -28591 / 2792", -28591.0 / 2792.0,
        path.getDerivative().apply(5.0), errorMargin);
    assertEquals("P'(10) should be roughly equal to -21046 / 2007", -21046.0 / 2007.0,
        path.getDerivative().apply(10.0), errorMargin);
  }

  @Test
  public void testPathFromPointsLength() {
    Path path = PathUtil.createFromPoints(
        72, 36,
        130, 292,
        223, -86,
        223, 289
    );

    assertEquals("len(P) should be roughly equal to 372.127", 372.127,
        path.getLength(), errorMargin);
  }
}
