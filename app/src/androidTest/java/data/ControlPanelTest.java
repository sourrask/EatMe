package data;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by s158270 on 28-3-2018.
 */
public class ControlPanelTest extends TestUsingSP {

    ControlPanel cp;
    @Before
    public void setUp() {
        super.setUp();
        cp = new ControlPanel(appContext);
    }

    @Test
    public void fetchCorrect() {
        assertEquals(cp.recs.get(3).name, "kebab tosti");
        assertEquals(cp.ings.get(12).name, "pepper");
        assertEquals(cp.ings.get(13).name, "flour");
        assertEquals(((Ingredient)cp.ings.get(13)).amountNeed, 0, 0);
        assertEquals(((Ingredient)cp.ings.get(13)).amountHave, 0, 0);
        assertEquals(((Ingredient)((Recipe)cp.recs.get(3)).ingredients.get(2)).amountNeed, 2, 0);

        Log.d("XXXX", cp.getRandomRecipe().name);
        ((Ingredient)cp.ings.get("bread")).amountHave = 2;
        ((Ingredient)cp.ings.get("bacon")).amountHave = 100;
        ((Ingredient)cp.ings.get("eggs")).amountHave = 2;
        Log.d("XXXX", cp.getRandomRecipe().name);

    }
}