package data;

import android.content.SharedPreferences;

import java.util.List;

/**
 * Created by s158270 on 12-3-2018.
 */

/**
 * used for the shopping list and inventory
 */
public class IngredientList extends ListSPHandler {
    /**
     * creates a ingredient list representing the inventory
     * @param sp the shared preferences used for the inventory
     */
    public IngredientList(SharedPreferences sp, TextFileReader tfr) {
        super(sp);
        this.addAll(tfr.ings);
    }

    /**
     *
     * adds an ingredient to the list. If it already exists, adds the amounts to the existing one
     * @param n Name object, which should always be an ingredient
     * @pre n.object == Ingredient
     * @return whether the ingredient already existed or not
     */
    @Override
    public boolean add(Name n) {
        Ingredient x = ((Ingredient) n);
        if (!contains(x.name)) {
            return super.add(x);
        } else {
            Ingredient xx = (Ingredient) get(x.name);
            xx.addAmountNeed(x.amountNeed);
            xx.addAmountHave(x.amountHave);
            return false;
        }
    }

    @Override
    Ingredient stringToObject(String x) {
        List<String> nameCatValnValhUnit = splitOnChar(x, '#');
        String ingName = nameCatValnValhUnit.get(0);
        String ingCat = nameCatValnValhUnit.get(1);
        double ingAmountNeed = Double.parseDouble(nameCatValnValhUnit.get(2));
        double ingAmountHave = Double.parseDouble(nameCatValnValhUnit.get(3));
        Unit ingUnit = Unit.getUnit(nameCatValnValhUnit.get(4));
        return new Ingredient(ingName, ingCat, ingAmountNeed, ingAmountHave, ingUnit);
    }

    /**
     * representation for Ingredient
     * "#name#category#amount#unit#"
     */
    @Override
    String objectToString(Name n) {
        Ingredient x = (Ingredient) n;
        return "#" +
                x.name + "#" +
                x.category + "#" +
                x.amountNeed + "#" +
                x.amountHave + "#" +
                x.unit + "#";
    }
}
