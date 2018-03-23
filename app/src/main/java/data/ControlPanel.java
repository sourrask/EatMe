package data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s158270 on 14-3-2018.
 */

/**
 * object from which everything can be easily controlled
 */
public class ControlPanel {
    private IngredientList ings;
    private RecipeList recs;

    ControlPanel(Context appContext) {
        int contPriv = Context.MODE_PRIVATE;
        TextFileReader tfr = new TextFileReader(appContext);
        //ingredientlist
        SharedPreferences inglistSP =appContext.getSharedPreferences("inglist", contPriv);
        //recipelist
        SharedPreferences reclistSP =appContext.getSharedPreferences("reclist", contPriv);

        ings = new IngredientList(inglistSP, tfr);
        recs = new RecipeList(reclistSP, tfr);

    }

    //----------------------------SHOPPINGLIST----------------------------
    /**
     * returns a list of ingredients where the amount < 0
     */
    List<Ingredient> getShoppingList() {
        List<Ingredient> sllist = new ArrayList<>();
        for (Name i : ings) {
            Ingredient ing = (Ingredient) i;
            if (ing.amountNeed > 0) {
                sllist.add(ing);
            }
        }
        return sllist;
    }

    /**
     * adds an ingredient to the shopping list
     */
    void addIngredientToShoppingList(String name, String category, Double amount, String unit) {
        ings.add(new Ingredient(name, category, amount, 0.0, unit));
    }
    //Only use when the ingredient already exists
    void addIngredientToShoppingList(String name, Double amount) {
        ings.add(new Ingredient(name, "x", amount, 0.0, "x"));
    }


    /**
     * removes an ingredient to the shopping list (by changing amountNeed to 0)
     * does NOT add it to the inventory
     */
    void removeIngredientFromShoppingList(String name) {
        ((Ingredient)ings.get(name)).amountNeed = 0;
    }

    /**
     * deletes all ingredients from the shopping list and adds them to the inventory
     */

    void removeAllIngredientFromShoppingList() {
        for (Ingredient i : getShoppingList()) {
            i.amountHave += i.amountNeed;
            i.amountNeed = 0;
        }
    }

    //----------------------------INVENTORY----------------------------
    /**
     * returns a list of ingredient where the amount > 0
     */
    void getInventory() {

    }

    //----------------------------ALLINGREDIENTS----------------------------
    /**
     * returns a list with all known ingredients
     * unlike the inventory and shopping list, this includes ingredients where amount == 0
     */
    void getAllIngredients() {

    }

    /**
     * adds an ingredient to the ingredient list
     * if the ingredient already exists, then the amount of the ingredient will be added
     */
    void addIngredient() {

    }

    /**
     * sets the amount of the ingredient to 0
     */
    void removeFromShoppingListOrInventory() {

    }

    //----------------------------RECIPE----------------------------
    /**
     * adds a new recipe. Does nothing if the recipe already exists
     */
    void addRecipe() {

    }

    /**
     * checks if the ingredient exist, and adds it to the recipe
     * if the ingredient doesn't exist, then it creates it and puts it in the ingredients list
     * (amount in ingredients list is 0)
     */
    void addIngredientToRecipe() {

    }

    /**
     * removes an ingredient from the recipe
     */
    void removeIngredientFromRecipe() {

    }

    //----------------------------RECOMMENDED----------------------------

    List<Recipe> getRecommendedRecipes() {
        List<Recipe> recrecs = new ArrayList<>();
        for (Name r : recs) {
            Recipe rec = (Recipe) r;
            if (rec.recommended) {
                recrecs.add(rec);
            }
        }
        return recrecs;
    }

    void changeRecommended(String name) {
        ((Recipe)recs.get(name)).changeRecommended();
    }
}
