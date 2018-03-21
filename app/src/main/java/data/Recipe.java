package data;

/**
 * Created by s158270 on 12-3-2018.
 */

/**
 * creates a recipe
 */
public class Recipe extends Name {
    NoDuplicatesArrayList ingredients;
    boolean recommended = false;

    /**
     *
     * creates a recipe
     * @param name name of the recipe
     * @pre name.isValid
     * @post name is in lowercase
     * @throws IllegalArgumentException if pre-condition not met
     */
    Recipe(String name) throws IllegalArgumentException {
        super(name);
        ingredients = new NoDuplicatesArrayList();
    }

    void changeRecommended() {
        if (recommended) {
            recommended = false;
        } else {
            recommended = true;
        }
    }

}
