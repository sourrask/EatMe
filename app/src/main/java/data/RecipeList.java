package data;

import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by s158270 on 12-3-2018.
 */

/**
 * list of recipes known so far (including those from the database)
 */
public class RecipeList extends ListSPHandler {

    /**
     *
     * for creating a list of recipes
     * @param sp shared preferences used for the recipes
     */
    public RecipeList(SharedPreferences sp, TextFileReader tfr) {
        super(sp);
        this.addAll(tfr.recs);
    }

    @Override
    Recipe stringToObject(String x) {
        //split x into name and ingredients
        List<String> nameIng = splitOnChar(x, '#');
        String name = nameIng.get(0);
        String ings = nameIng.get(1);
        boolean rec = Boolean.parseBoolean(nameIng.get(2));

        //split ingredients into seperate ingredients
        List<String> ingredientList = splitOnChar(ings, '*');

        //create each seperate ingredient and add them to a list, which is then added to the recipe
        List ingredientList2 = new NoDuplicatesArrayList();
        for (String ingredient : ingredientList) {
            List<String> ing = splitOnChar(ingredient, '&');
            ingredientList2.add(new Ingredient(
                    ing.get(0),
                    ing.get(1),
                    Double.parseDouble(ing.get(2)),
                    Double.parseDouble(ing.get(3)),
                    Unit.getUnit(ing.get(4))));
        }
        Recipe r = new Recipe(name);
        if(rec) {
            r.changeRecommended();
        }
        r.ingredients.addAll(ingredientList2);
        return (r);
    }

    /**
     * representation of Recipe:
     * "#name#*ingredient1*ingredient2*ingredient3*...*#recommended#"
     * where ingredient = &name&category&amount&unit&
     */
    @Override
    String objectToString(Name n) {
        Recipe x = (Recipe) n;
        String returnString = "#" + n.name + "#";
        if (!x.ingredients.isEmpty()) {
            returnString += "*";
        }
        for (Name y : x.ingredients) {
            Ingredient ing = (Ingredient) y;
            returnString += ("&" +
                    ing.name + "&" +
                    ing.category + "&" +
                    ing.amountNeed + "&" +
                    ing.amountHave + "&" +
                    ing.unit + "&*");
        }
        returnString += "#" + x.recommended + "#";
        return returnString;
    }

}
