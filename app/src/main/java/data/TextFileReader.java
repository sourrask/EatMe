package data;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by s158270 on 19-3-2018.
 */

class TextFileReader {

    NoDuplicatesArrayList ings = new NoDuplicatesArrayList();
    NoDuplicatesArrayList recs = new NoDuplicatesArrayList();

    /**
     *
     * reads from the database (a textfile) and saves all ingredients and recipes
     * @param context current state of the app
     */
    TextFileReader(Context context) {
        BufferedReader in = null;
        Scanner s;
        String line;
        if (context != null) {
            try {
                AssetManager am = context.getAssets();
                InputStream inputStream = am.open("database.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                in = new BufferedReader(inputStreamReader);
                while ((line = in.readLine()) != null) {
                    if (line == "") {
                        //do nothing
                    } else if (line.contains("{")) {
                        String recName = "";
                        for (int i = 0; i < line.length(); i++) {
                            if (line.charAt(i) == '{') {
                                if (line.charAt(i-1) == ' ') {
                                    recName = line.substring(0, i-1);
                                } else {
                                    recName = line.substring(0, i);
                                }
                                break;
                            }
                        }
                        Recipe r = new Recipe(recName);
                        while (!(line = in.readLine()).contains("}")) {
                            s = new Scanner(line);
                            String name = s.next();
                            for (int i = 0; i < name.length(); i++) {
                                if (name.substring(i, i+1).equals("_")) {
                                    name =
                                            name.substring(0, i) +
                                            " " +
                                            name.substring(i+1, name.length());
                                }
                            }
                            String cat = s.next();
                            Double amountNeed = Double.parseDouble(s.next());
                            String unit = s.next();

                            Ingredient i = new Ingredient(name, cat, 0.0, 0.0, unit);
                            ings.add(i);
                            Ingredient ir = new Ingredient(name, cat, amountNeed, 0.0, unit);
                            r.ingredients.add(ir);

                        }
                        recs.add(r);
                    }
                }

            } catch (Exception e) {
            }
        }
    }
}
