package data;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s158270 on 14-3-2018.
 */

/**
 * a list that can import and export from/to SharedPreferences
 */
abstract class ListSPHandler extends NoDuplicatesArrayList {
    SharedPreferences sp;

    /**
     * creates a ingredient list with a shared preferences object added
     * @param sp shared preferences to be used by the object
     */
    ListSPHandler(SharedPreferences sp) throws IllegalArgumentException {
        super();
        this.sp = sp;
        importSP();
    }

    /**
     * gets the object K from the shared preferences linked to it
     */
    void importSP() {
        int x = this.sp.getInt("nr", 0);
        for (int i = 1; i <= x; i++) {
            String s = this.sp.getString("" + i, null);
            this.add(stringToObject(s));
        }
    }

    /**
     * puts all objects K in the list into a shared preferences object
     */
    void exportSP() {
        int nr = this.size();
        SharedPreferences.Editor e = sp.edit();
        e.putInt("nr", nr);
        for (int i = 1; i <= nr; i++) {
            Name n = this.get(i-1);
            e.putString("" + i, objectToString(n));
        }
        e.apply();
    }

    /**
     *
     * transforms the string into the object within the list
     * @param x string that represents the object of type in the list
     * @return new object of type contained in the list
     */
    abstract Name stringToObject(String x);

    /**
     * makes a string from a list of ingredients
     * @param x the list object
     * @return a string representing a list
     *          it is represented as "ingredient1/ingredient2/ingredient3/..."
     */
    abstract String objectToString(Name x);

    /**
     *
     * splits a string into substrings where each substring is between two chars
     * @param x string to be split
     * @param split char that distincts substrings
     * @return list of substrings
     */
    static List<String> splitOnChar(String x, char split) {
        List<String> returnList = new ArrayList<>();
        List<Integer> splitLoc = new ArrayList<>();
        for (int i = 0; i < x.length(); i++) {
            if (x.substring(i, i+1).equals("" + split)) {
                splitLoc.add(i);
            }
        }
        for (int i = 1; i < splitLoc.size(); i++) {
            String subString = x.substring(
                    splitLoc.get(i-1) + 1, splitLoc.get(i));
            returnList.add(subString);
        }
        return returnList;
    }
}
