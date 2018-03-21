package data;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s158270 on 16-3-2018.
 */
public class RecipeListTest extends TestUsingSP {

    String spName = "rl";

    RecipeList rl;
    Recipe r1;
    Recipe r2;
    Recipe r3;

    Ingredient i1;
    Ingredient i2;
    Ingredient i3;
    Ingredient i4;
    Ingredient i5;
    Ingredient i6;

    @Before
    public void setUp() {
        super.setUp();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        cleanSP(sp);

        i1 = new Ingredient("carrot", "veggie", 3, 5, "none");
        i2 = new Ingredient("apple", "fruit", 5, 7,"grams");
        i3 = new Ingredient("tOmaTo", "Veggie", 3, 1,"none");
        i4 = new Ingredient("beEf", "MEaT", 200, 500,"grams");
        i5 = new Ingredient("spaghetti", "douGH", 20.912, 1.2, "grams");
        i6 = new Ingredient("salt", "spicE", 4, 5, "milliliters");

        SharedPreferences sp2 = appContext.getSharedPreferences("unused", Context.MODE_PRIVATE);

        rl = new RecipeList(sp, new TextFileReader(null));

        //recipe with tomato, beef, spaghetti, salt
        r1 = new Recipe("spaGHetTo");
        r1.ingredients.add(i3);
        r1.ingredients.add(i4);
        r1.ingredients.add(i5);
        r1.ingredients.add(i6);

        //a recipe with no ingredients
        r2 = new Recipe("nothing");

        //a recipe with carrot and apple
        r3 = new Recipe("sTamPPoT");
        r3.ingredients.add(i1);
        r3.ingredients.add(i2);
        r3.changeRecommended();

        rl.add(r1);
        rl.add(r2);
        rl.add(r3);
    }

    @Test
    public void validGet1() {
        assertEquals(r3, rl.get("stamppot"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidGet1() {
        rl.get("GyrOs");
    }

    @Test(expected = NullPointerException.class)
    public void invalidGet2() {
        rl.get(null);
    }

    @Test
    public void exportTest1() {
        //export recipe list and test the retrieved data for two recipes
        rl.exportSP();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        assertEquals(3, sp.getInt("nr", 0));
        assertEquals("#stamppot#*&carrot&veggie&3.0&5.0&NONE&*&apple&fruit&5.0&7.0&GRAMS&*#true#", sp.getString("3", null));
        assertEquals("#nothing##false#", sp.getString("2", null));
    }

    @Test
    public void importTest1() {
        //export the ingredient list and import it in another recipe list
        rl.exportSP();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        assertEquals(3, sp.getInt("nr", 0));
        RecipeList rl2 = new RecipeList(sp, new TextFileReader(appContext)); //constructor automatically imports

        //check two recipe names
        assertEquals("nothing", rl2.get(1).name);
        assertEquals("stamppot", rl2.get(2).name);

        //check if a single ingredient of a recipe is present correctly
        Recipe stamp = ((Recipe) rl2.get(2)); //get the stamppot
        Ingredient car = (Ingredient) stamp.ingredients.get("carrot");
        //test the carrot
        assertEquals("veggie", car.category);
        assertEquals(3, car.amountNeed, 0);
        assertEquals(5, car.amountHave, 0);
        assertEquals(Unit.NONE, car.unit);

        Ingredient apple = (Ingredient) stamp.ingredients.get("apple");
        //test the apple
        assertEquals("fruit", apple.category);
        assertEquals(5, apple.amountNeed, 0);
        assertEquals(7, apple.amountHave, 0);
        assertEquals(Unit.GRAMS, apple.unit);

        //check if correct recommended
        assertFalse(((Recipe) rl2.get(0)).recommended);
        assertTrue(((Recipe) rl2.get(2)).recommended);

        //test text file import
        assertEquals("leek", ((Recipe)rl2.get(3)).ingredients.get(5).name);

    }


}