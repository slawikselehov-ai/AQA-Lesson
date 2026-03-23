import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {

    @Test
    public void testFactorialOfZero() {
        Assert.assertEquals(Factorial.calculate(0), 1);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        Assert.assertEquals(Factorial.calculate(5), 120);
    }

    @Test
    public void testFactorialOfOne() {
        Assert.assertEquals(Factorial.calculate(1), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumber() {
        Factorial.calculate(-5);
    }
}
