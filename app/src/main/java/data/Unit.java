package data;

/**
 * Created by s158270 on 13-3-2018.
 */

public enum Unit {
    GRAMS, MILLILITERS, NONE;

    /**
     *
     * returns a unit matching the input string
     * method is case insensitive
     * @param x string to represent unit
     * @return the matching unit, or none if no match is found
     */
    public static Unit getUnit(String x) {
        x = x.toUpperCase();
        for (Unit u : Unit.values()) {
            if(u.toString().equals(x)) {
                return u;
            }
        }
        return NONE;
    }
}
