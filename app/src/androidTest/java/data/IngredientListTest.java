package data;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s158270 on 15-3-2018.
 */
public class IngredientListTest extends TestUsingSP {

    String spName = "invshList";

    IngredientList isl;
    Ingredient i1;
    Ingredient i2;
    Ingredient i3;
    Ingredient i4;
    Ingredient i5;

    @Before
    public void setUp() {
        super.setUp();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        cleanSP(sp);

        isl = new IngredientList(sp, new TextFileReader(null));

        i1 = new Ingredient("suiker", "smaakmakers", 5, 2.1, "grams");
        i2 = new Ingredient("kerrie", "kruiden", 300, 27, "mILliLiters");
        i3 = new Ingredient("spruitjes", "groente", 20, 3, "none");
        i4 = new Ingredient("vanille", "kruiden", 2.39, 1.01, Unit.GRAMS);
        i5 = new Ingredient("aardappelen", "geen", 5, 1, Unit.NONE);

        isl.add(i1);
        isl.add(i2);
        isl.add(i3);
        isl.add(i4);
        isl.add(i5);
    }

    @Test
    public void validAdd1() {
        //first check for setUp add
        assertEquals(i3, isl.get(2));

        //checks the amount of ingredient 4
        assertEquals(2.39, ((Ingredient) isl.get(3)).amountNeed, 0);
        assertEquals(1.01, ((Ingredient) isl.get(3)).amountHave, 0);

        //add an ingredient that is already present in the list
        //the amount should be added to the current amount 2.39 + 0.45 = 2.84
        //ignores the fact that category and unit don't match
        assertFalse(isl.add(new Ingredient("vANiLle", "niks", 0.45, 0.69, "none")));
        assertEquals(2.84, ((Ingredient) isl.get(3)).amountNeed, 0.00001);
        assertEquals(1.7, ((Ingredient) isl.get(3)).amountHave, 0.00001);
        assertEquals("kruiden", ((Ingredient) isl.get(3)).category);
        assertEquals(Unit.GRAMS, ((Ingredient) isl.get(3)).unit);

        //add a new ingredient and check if it's added correctly
        assertTrue(isl.add(new Ingredient("cookies", "yum", 3, 4, "none")));
        assertEquals("cookies", isl.get(5).name);
        assertEquals("yum", ((Ingredient) isl.get(5)).category);
        assertEquals(3, ((Ingredient) isl.get(5)).amountNeed, 0);
        assertEquals(4, ((Ingredient) isl.get(5)).amountHave, 0);
        assertEquals(Unit.NONE, ((Ingredient) isl.get(5)).unit);
    }

    @Test
    public void exportTest1() {
        //export the ingredient list into the shared preferences, and retreive the data
        //put inside the SharedPreferences
        isl.exportSP();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        assertEquals(5, sp.getInt("nr", 0));
        assertEquals("#suiker#smaakmakers#5.0#2.1#GRAMS#", sp.getString("1", null));
        assertEquals("#spruitjes#groente#20.0#3.0#NONE#", sp.getString("3", null));
    }

    @Test
    public void importTest1() {
        //export the ingredients list and import it again, but in another list
        isl.exportSP();
        SharedPreferences sp = appContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
        assertEquals(5, sp.getInt("nr", 0));
        IngredientList isl2 = new IngredientList(sp, new TextFileReader(appContext)); //constructor automatically imports

        //check a random part of an ingredient present
        assertEquals("kruiden", ((Ingredient) isl2.get(1)).category);

        //check if ingredient 5 is present correctly
        assertEquals("aardappelen", isl2.get(4).name);
        assertEquals("geen", ((Ingredient) isl2.get(4)).category);
        assertEquals(5, ((Ingredient) isl2.get(4)).amountNeed, 0);
        assertEquals(1, ((Ingredient) isl2.get(4)).amountHave, 0);
        assertEquals(Unit.NONE, ((Ingredient) isl2.get(4)).unit);

        //test text file import
        assertEquals("leek", isl2.get(10).name);
    }


}