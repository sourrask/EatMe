package data;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s158270 on 14-3-2018.
 */
public class NameTest {

    @Test
    public void validNameSet() {
        Name n = new Name("kaas");
    }

    @Test
    public void isValidTest1() {
        assertTrue(Name.isValid("the quick brown fox jumps over the lazy dog"));
    }

    @Test
    public void isValidTest2() {
        assertFalse(Name.isValid(""));
    }

    @Test
    public void isValidTest3() {
        assertFalse(Name.isValid("the quick brown fox jumps# over the lazy dog"));
    }

    @Test
    public void isValidTest4() {
        assertFalse(Name.isValid("the quick brown fox jumps* over the lazy dog"));
    }

    @Test
    public void isValidTest5() {
        assertFalse(Name.isValid("the quick & brown fox jumps over the lazy dog"));
    }

    @Test (expected = NullPointerException.class)
    public void isInvalidTest1() {
        Name.isValid(null);
    }

}