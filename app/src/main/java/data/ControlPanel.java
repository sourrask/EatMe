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
    List<Ingredient> getInventory() {
        return null;
    }

    /**
     * adds an ingredient to the shopping list
     */
    void addIngredientToInventory(String name, String category, Double amount, String unit) {

    }
    //Only use when the ingredient already exists
    void addIngredientToInventory(String name, Double amount) {

    }


    /**
     * removes an ingredient to the shopping list (by changing amountNeed to 0)
     * does NOT add it to the inventory
     */
    void removeIngredientFromInventory(String name) {

    }

    /**
     * deletes all ingredients from the shopping list and adds them to the inventory
     */

    Recipe getRandomRecipe() {
        return null;
    }





    //----------------------------ALLINGREDIENTS----------------------------
    /**
     * returns a list with all known ingredients
     * unlike the inventory and shopping list, this includes ingredients where amount == 0
     */
    List<Ingredient> getAllIngredients() {
        List<Ingredient> lings = new ArrayList<>();
        for (Name i : ings) {
            Ingredient ing = (Ingredient) i;
            lings.add(ing);
        }
        return lings;
    }

    /**
     * adds an ingredient to the ingredient list
     * if the ingredient already exists, then the amount of the ingredient will be added
     * it can (therefore) be used to add something to the shopping list or inventory (or both)
     */
    void addIngredient(String name, String category, double amountN, double amountH, String unit) {
        ings.add(new Ingredient(name, category, amountN, amountH, unit));
    }

    void addIngredient(String name, String category, String unit) {
        ings.add(new Ingredient(name, category, 0.0, 0.0, unit));
    }

    void addIngredient(Ingredient i) {
        ings.add(i);
    }

    /**
     * sets the amount of the ingredient to 0
     */
    void removeFromShoppingListAndInventory(String name) {
        Ingredient i = (Ingredient) ings.get(name);
        i.amountNeed = 0.0;
        i.amountHave = 0.0;
    }

    //----------------------------RECIPE----------------------------
    /**
     * adds a new recipe. Does nothing if the recipe already exists
     */
    void addRecipe() {

    }

    /**
     * deletes an existing recipe.
     */
    void deleteRecipe() {

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

    /**
     * add.remove from recommended, depending on current state
     */
    void changeRecommended(String name) {
        ((Recipe)recs.get(name)).changeRecommended();
    }
}
