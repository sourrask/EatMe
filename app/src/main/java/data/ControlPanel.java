package data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by s158270 on 14-3-2018.
 */

/**
 * object from which everything can be easily controlled
 */
public class ControlPanel {
    public IngredientList ings;
    public RecipeList recs;

    public ControlPanel(Context appContext) {
        int contPriv = Context.MODE_PRIVATE;
        TextFileReader tfr = new TextFileReader(appContext);
        //ingredientlist
        SharedPreferences inglistSP = appContext.getSharedPreferences("inglist", contPriv);
        //recipelist
        SharedPreferences reclistSP = appContext.getSharedPreferences("reclist", contPriv);

        ings = new IngredientList(inglistSP, tfr);
        recs = new RecipeList(reclistSP, tfr);

    }

    //----------------------------SHOPPINGLIST----------------------------
    /**
     * returns a list of ingredients where the amount < 0
     */
    public List<Ingredient> getShoppingList() {
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
    public void addIngredientToShoppingList(String name, String category, Double amount, String unit) {
        ings.add(new Ingredient(name, category, amount, 0.0, unit));
    }
    //Only use when the ingredient already exists
    public void addIngredientToShoppingList(String name, Double amount) {
        ings.add(new Ingredient(name, "x", amount, 0.0, "x"));
    }


    /**
     * removes an ingredient to the shopping list (by changing amountNeed to 0)
     * does NOT add it to the inventory
     */
    public void removeIngredientFromShoppingList(String name) {
        ((Ingredient)ings.get(name)).amountNeed = 0;
    }

    /**
     * deletes all ingredients from the shopping list and adds them to the inventory
     */

    public void removeAllIngredientFromShoppingList() {
        for (Ingredient i : getShoppingList()) {
            i.amountHave += i.amountNeed;
            i.amountNeed = 0;
        }
    }

    //----------------------------INVENTORY----------------------------
    /**
     * returns a list of ingredient where the amount > 0
     */
    public List<Ingredient> getInventory() {
        List<Ingredient> invlist = new ArrayList<>();
        for (Name i : ings) {
            Ingredient ing = (Ingredient) i;
            if (ing.amountHave > 0) {
                invlist.add(ing);
            }
        }
        return invlist;
    }

    /**
     * adds an ingredient to the shopping list
     */
    public void addIngredientToInventory(String name, String category, Double amount, String unit) {
        ings.add(new Ingredient(name, category, 0.0, amount, unit));
    }
    //Only use when the ingredient already exists
    public void addIngredientToInventory(String name, Double amount) {
        ings.add(new Ingredient(name, "x", amount, 0.0, "x"));
    }


    /**
     * removes an ingredient to the shopping list (by changing amountNeed to 0)
     * does NOT add it to the inventory
     */
    public void removeIngredientFromInventory(String name) {
        ((Ingredient)ings.get(name)).amountHave = 0;
    }

    /**
     * returns a random recipe where you have all ingredients for
     * if none exist, returns pure random recipe
     */
    public Recipe getRandomRecipe() {
        List<Recipe> rl = new ArrayList<>();
        for(Name nr : recs) {
            Recipe r = (Recipe) nr;
            boolean isValid = true;
            for(Name ni : r.ingredients) {
                Ingredient i = (Ingredient) ni;
                if(((Ingredient) ings.get(i.name)).amountHave < i.amountNeed) {
                    isValid = false;
                    break;
                }

            }
            if(isValid) {
                rl.add(r);
            }
        }
        Random r = new Random();
        if (rl.isEmpty()) {
            return (Recipe) recs.get(r.nextInt(recs.size()));
        } else {
            return rl.get(r.nextInt(rl.size()));
        }
    }





    //----------------------------ALLINGREDIENTS----------------------------
    /**
     * returns a list with all known ingredients
     * unlike the inventory and shopping list, this includes ingredients where amount == 0
     */
    public List<Ingredient> getAllIngredients() {
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
    public void addIngredient(String name, String category, double amountN, double amountH, String unit) {
        ings.add(new Ingredient(name, category, amountN, amountH, unit));
    }

    public void addIngredient(String name, String category, String unit) {
        ings.add(new Ingredient(name, category, 0.0, 0.0, unit));
    }

    public void addIngredient(Ingredient i) {
        ings.add(i);
    }

    /**
     * sets the amount of the ingredient to 0
     */
    public void removeFromShoppingListAndInventory(String name) {
        Ingredient i = (Ingredient) ings.get(name);
        i.amountNeed = 0.0;
        i.amountHave = 0.0;
    }

    //----------------------------RECIPE----------------------------

    public List<Recipe> getAllRecipe() {
        List<Recipe> lrecs = new ArrayList<>();
        for (Name r : recs) {
            Recipe rec = (Recipe) r;
            lrecs.add(rec);
        }
        return lrecs;
    }

    /**
     * adds a new recipe. Does nothing if the recipe already exists
     */
    public void addRecipe(String name) {
        recs.add(new Recipe(name));
    }

    /**
     * deletes an existing recipe.
     */
    public void deleteRecipe(String name) {
        recs.remove(recs.get(name));
    }

    /**
     * checks if the ingredient exist, and adds it to the recipe
     * if the ingredient doesn't exist, then it creates it and puts it in the ingredients list
     * (amount in ingredients list is 0)
     */
    public void addIngredientToRecipe(String recName, String name, String category, double amountN, double amountH, String unit) {
        Ingredient i = new Ingredient(name, category, 0.0, 0.0, unit);
        ings.add(i); //since amount is 0, nothing changes if ingredient already exists
        Recipe r = (Recipe) recs.get(recName);
        i.amountNeed = amountN;
        i.amountHave = amountH;
        r.ingredients.add(i);


    }

    /**
     * removes an ingredient from the recipe
     */
    public void removeIngredientFromRecipe(String recName, String ingName) {
        Recipe r = (Recipe) recs.get(recName);
        r.ingredients.remove(r.ingredients.get(ingName));
    }

    //----------------------------RECOMMENDED----------------------------

    public List<Recipe> getRecommendedRecipes() {
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
    public void changeRecommended(String name) {
        ((Recipe)recs.get(name)).changeRecommended();
    }

    /**
     * call when moving from activity to update SP
     */
    public void save() {
        ings.exportSP();
        recs.exportSP();
    }
}
