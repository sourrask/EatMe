package data;

import android.content.Context;
import android.content.SharedPreferences;

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
    void getShoppingList() {

    }

    /**
     * multiplies the amount of the ingredient by -1 if amount is negative
     */
    void shoppingListToInventory() {

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

    ////----------------------------RECIPE----------------------------
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
}
