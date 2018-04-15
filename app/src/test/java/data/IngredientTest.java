package data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by s158270 on 14-3-2018.
 */
public class IngredientTest {
    String ingName;
    String ingCat;
    double amountNeed;
    double amountHave;
    String unit;
    Ingredient i;
    @Before
    public void setUp() {
        ingName = "zoUt";
        ingCat = "smAAkMakERs";
        amountNeed = 0.25;
        amountHave = 0.12;
        unit = "GraMS";
    }

    @Test
    public void validIngredient() {
        Ingredient ing = new Ingredient(ingName, ingCat, amountNeed, amountHave, unit);
        assertEquals("zout", ing.name);
        assertEquals("smaakmakers", ing.category);
        assertEquals(0.25, ing.amountNeed, 0.000001);
        assertEquals(0.12, ing.amountHave, 0.000001);
        assertEquals(Unit.GRAMS, ing.unit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidIngredientName1() {
        String name = "appel*peer";
        Ingredient ing = new Ingredient(name, ingCat, amountNeed, amountHave, unit);
    }

    @Test (expected = IllegalArgumentException.class)
    public void invalidIngredientCategory1() {
        String category = "fruit#groente";
        Ingredient ing = new Ingredient(ingName, category, amountNeed, amountHave, unit);
    }

    @Test (expected = NullPointerException.class)
    public void invalidIngredientUnit1() {
        String unit = null;
        Ingredient ing = new Ingredient(ingName, ingCat, amountNeed, amountHave, unit);
    }

    @Test
    public void addAmountTest1() {
        i = new Ingredient(ingName, ingCat, amountNeed, amountHave, unit);
        i.addAmountNeed(2.49);
        assertEquals(2.74, i.amountNeed, 0.000001);
        i.addAmountNeed(-1.3);
        assertEquals(1.44, i.amountNeed, 0.000001);

        i.addAmountHave(6.43);
        assertEquals(6.55, i.amountHave, 0.000001);
        i.addAmountHave(-3.7);
        assertEquals(2.85, i.amountHave, 0.000001);

        i.addAmountNeed(-5);
        assertEquals(0, i.amountNeed, 0);
        assertEquals(6.41, i.amountHave, 0.000001);
        i.addAmountHave(-7.0);
        assertEquals(0, i.amountHave, 0);
        assertEquals(0.59, i.amountNeed, 0000001);
    }

}