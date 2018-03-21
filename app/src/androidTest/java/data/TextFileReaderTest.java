package data;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by s158270 on 19-3-2018.
 */
public class TextFileReaderTest extends TestUsingSP {
    TextFileReader tfr = null;
    @Before
    public void setUpp() throws Exception {
        tfr = new TextFileReader(appContext);
    }

    @Test
    public void x() {
        Recipe r = (Recipe) tfr.recs.get(0);
        assertEquals("spaghetti", r.name);
        assertEquals("basil", r.ingredients.get(3).name);

        r = (Recipe) tfr.recs.get(2);
        assertEquals("pancakes", r.name);
        Ingredient i = (Ingredient) r.ingredients.get(2);
        assertEquals("milk", i.name);
        assertEquals("dairy", i.category);
        assertEquals(500, i.amountNeed, 0);
        assertEquals(Unit.MILLILITERS, i.unit);
    }

}