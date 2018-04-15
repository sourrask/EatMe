package data;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by s158270 on 13-3-2018.
 */
public class RecipeTest {
    List<Ingredient> usedList = new ArrayList<>();
    @Before
    public void setUp() {
        Ingredient i1 = new Ingredient(
                "cheese", "dairy", 2.0, 0.0, Unit.NONE);
        Ingredient i2 = new Ingredient(
                "ketchup", "sauce", 100.0, 0.0, Unit.MILLILITERS);
        Ingredient i3 = new Ingredient(
                "spaghetti", "pasta", 20.0, 0.0, Unit.GRAMS);

        usedList.add(i1);
        usedList.add(i2);
        usedList.add(i3);
    }

    //testing a valid recipe
    @Test
    public void createValidRecipe1() {
        Recipe r = new Recipe("spAgHEt iTALiANo");
        r.ingredients.addAll(usedList);
        assertEquals("spaghet italiano", r.name);
        assertEquals("cheese", r.ingredients.get("cheese").name);
        assertEquals("pasta", ((Ingredient)r.ingredients.get("spaghetti")).category);
    }

    //testing invalid recipe with , or / in name
    @Test (expected = IllegalArgumentException.class)
    public void createInvalidRecipe1() {
        new Recipe("strawberry*lime cake");
    }

    @Test (expected = IllegalArgumentException.class)
    public void createInvalidRecipe2() {
        new Recipe("strawberry#lime cake");
    }

    //testing invalid recipe by using "" or null string
    @Test (expected = NullPointerException.class)
    public void createInvalidRecipe3() {
        new Recipe(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createInvalidRecipe4() {
        new Recipe("");
    }

    @Test
    public void checkRecommended() {
        Recipe r = new Recipe("spAgHEt iTALiANo");
        r.ingredients.addAll(usedList);
        assertFalse(r.recommended);
        r.changeRecommended();
        assertTrue(r.recommended);
    }

}