package data;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by s158270 on 15-3-2018.
 */
public class UnitTest {
    @Test
    public void existingStringUnit1() {
        Unit u = Unit.getUnit("GRAMS");
        assertEquals(Unit.GRAMS, u);
    }

    @Test
    public void existingStringUnit2() {
        Unit u = Unit.getUnit("miLlILIteRS");
        assertEquals(Unit.MILLILITERS, u);
    }

    @Test
    public void nonExistingStringUnit1() {
        Unit u = Unit.getUnit("hfdusls");
        assertEquals(Unit.NONE, u);
    }

    @Test (expected = NullPointerException.class)
    public void nonExistingStringUnit2() {
        Unit u = Unit.getUnit(null);
        assertEquals(Unit.NONE, u);
    }
}