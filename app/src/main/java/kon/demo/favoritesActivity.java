package kon.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.List;

import data.ControlPanel;
import data.Name;
import data.Recipe;
import data.RecipeList;


/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class favoritesActivity extends AppCompatActivity {
    // Gets the template for the favorites activity by setting the content view to a desired layout
    private Context context;
    ControlPanel cp;
    int i=0;
    String[] favoritesName;
    MyArrayAdapter adapter;

    //search editText
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        context = getApplicationContext();
        cp = new ControlPanel(context);
        update();
    }

    //update listview with recipes
    public void update() {
        final ListView favoritesView = (ListView) findViewById(R.id.favoritesList);
        List<Recipe> favoritesList;
        favoritesList = cp.getRecommendedRecipes();
        favoritesName = new String[favoritesList.size()];

        for (Recipe recipe : favoritesList) {
            String name = recipe.getName();
            favoritesName[i] = name;
            i++;
        }

        adapter = new MyArrayAdapter(this, favoritesName);
        favoritesView.setAdapter(adapter);
        searchText = (EditText) findViewById(R.id.searchText);

        //enable search
        /*
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when text is entered
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                favoritesActivity.this.adapter.getFilter();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        */

    }

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
