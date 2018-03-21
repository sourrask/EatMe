package data;

/**
 * Created by s158270 on 13-3-2018.
 */

/**
 * creates an ingredient with an amount and unit
 */
class Ingredient extends Name {
    final String category; //category to which it belongs
    double amountNeed;
    double amountHave;
    final Unit unit;

    /**
     *
     * creates an Ingredient object
     * if unit does not exist, then set to NONE
     * @param n name of the ingredient
     * @param c category of ingredient
     * @param amountNeed number presenting how much of the ingredient you have
     * @param amountHave number presenting how much of the ingredient you have
     * @param unit unit corresponding to the amount in string format
     * @pre n.isValid && c.isValid && amount => 0
     * @post n and c are in lowercase
     * @throws IllegalArgumentException if n or c have the wrong format or if c < 0
     * @throws NullPointerException if unit is null
     */
    Ingredient(String n, String c, double amountNeed, double amountHave, String unit) throws IllegalArgumentException {
        this(n, c, amountNeed, amountHave, Unit.getUnit(unit));
    }

    Ingredient(String n, String c, double amountNeed, double amountHave, Unit unit) throws IllegalArgumentException {
        super(n);
        c = c.toLowerCase();
        if (!Name.isValid(c)) {
            throw new IllegalArgumentException(
                    "category cannot contain ',' or '/' or '&'");
        }
        this.category = c;
        this.amountNeed = amountNeed;
        this.amountHave = amountHave;
        this.unit = unit;
    }

    /**
     *
     * adds a number to the amount
     * if the amount goes lower than 0, it is set to zero
     * the remaining will be added to the other amount
     * @param x amount to be added
     */
    void addAmountNeed(double x) {
        this.amountNeed += x;
        if (amountNeed < 0) {
            amountHave += (-1 * amountNeed);
            amountNeed = 0;
        }
    }

    /**
     *
     * adds a number to the amount
     * if the amount goes lower than 0, it is set to zero
     * the remaining will be added to the other amount
     * @param x amount to be added
     */
    void addAmountHave(double x) {
        this.amountHave += x;
        if (amountHave < 0) {
            amountNeed += (-1 * amountHave);
            amountHave = 0;
        }
    }
}
