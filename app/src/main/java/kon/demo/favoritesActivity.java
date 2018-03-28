package kon.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.List;

import data.ControlPanel;
import data.Recipe;


/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class favoritesActivity extends AppCompatActivity {
    // Gets the template for the favorites activity by setting the content view to the desired layout
    private Context context = this;
    private ControlPanel controlpanel  = new ControlPanel(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        List<Recipe> recipesList;
        recipesList = controlpanel.getRecommendedRecipes();
        String[] recipesNames = new String[recipesList.size()];
        int i = 0;
        for (Recipe rec : recipesList) {
            String name = rec.getName();
            recipesNames[i] = name;
            i++;
            if (i == recipesList.size()) {
                break;
            }
        }
        MyArrayAdapter MyAdapter;
        MyAdapter = new MyArrayAdapter(this, recipesNames);
        ListView favoriteList = new ListView(this);
        setContentView(favoriteList);
        favoriteList.setAdapter(MyAdapter);
    }
    /*
    public void updateList() {
        List<Recipe> recipesList;
        recipesList = controlpanel.getRecommendedRecipes();
        List<String> recipesNames = new ArrayList<String>();
        for (Recipe rec : recipesList) {
            String name = rec.getName();
            recipesNames.add(name);
        }
        adapter = new ArrayAdapter<String>(context, R.layout.activity_favorites, R.id.favoritesList, recipesNames);

        ListView favoritesView = (ListView) findViewById(R.id.favoritesList);
        setContentView(favoritesView);
        favoritesView.setAdapter(adapter);


        adapter.notifyDataSetChanged();
    }
    */

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
