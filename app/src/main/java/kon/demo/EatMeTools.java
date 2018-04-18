package kon.demo;

import java.util.List;

import data.Ingredient;
import data.Recipe;

public class EatMeTools {
    /**
     *
     * @param rl list containing recipes
     * @return array of strings containing the names of the recipes
     */
    static String[] listToRecipeNameArray(List<Recipe> rl) {
        String[] returnArray = new String[rl.size()];

        int i=0;
        for (Recipe recipe : rl) {
            String name = recipe.getName();
            returnArray[i] = name;
            i++;
        }
        return returnArray;
    }

    /**
     *
     * @param ir list containing ingredients
     * @return array of strings containing the names of the ingredient
     */
    static String[] listToIngredientNameArray(List<Ingredient> ir) {
        String[] returnArray = new String[ir.size()];

        int i=0;
        for (Ingredient ingredient : ir) {
            String name = ingredient.getName();
            returnArray[i] = name;
            i++;
        }
        return returnArray;
    }

    /**
     *
     * @param ir list containing ingredients
     * @param needAmount whether the need amount is next to the ingredient name
     * @return array of strings containing the names of the ingredient with the asked amount
     */
    static String[] listToIngredientNameNeedHaveArray(List<Ingredient> ir, boolean needAmount) {
        String[] returnArray = new String[ir.size()];

        int i=0;
        for (Ingredient ingredient : ir) {
            String name = ingredient.getName();
            if (needAmount) {
                returnArray[i] = padRight(name, 15) + ingredient.amountNeed;
            } else {
                returnArray[i] = padRight(name, 15) + ingredient.amountHave;
            }
            i++;
        }
        return returnArray;
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}
