package data;

/**
 * Created by s158270 on 14-3-2018.
 */

public class Name {

    public final String name;

    /**
     *
     * sets the name of the object
     * @param n name to be set
     * @pre isValid(n)
     * @post name is all lowercase
     * @throws IllegalArgumentException if pre-condition not met
     */
    Name(String n) throws IllegalArgumentException {
        n = n.toLowerCase();
        if (isValid(n)) {
            this.name = n;
        } else {
            throw new IllegalArgumentException(
                    "Ingredient name cannot contain '#' or '*' or '&'");
        }
    }

    public String getName() {
        return this.name;
    }

    /**
     *
     * checks if a string is valid
     * @param x string to be checked
     * @return if x has a '#' or '*' or '&'
     */
    public static boolean isValid(String x) {
        return !x.equals("") && !x.contains("#") && !x.contains("*") && !x.contains("&");
    }
}
