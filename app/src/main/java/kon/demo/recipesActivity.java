package kon.demo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.Recipe;
import data.RecipeList;

import android.app.ListActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/*
 * Activity class for the recipes activity. In this activity we display the recipes in the database.
 * The back button of the phone will be available to click and will make the user return to the
 * home screen.
 */
public class recipesActivity extends AppCompatActivity {
    private Context context = this;
    ControlPanel cp = new ControlPanel(context);





    // Gets the template for the recipes activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
        update();


    }
    public void update(){
        ListView recipesView= (ListView) findViewById(R.id.listRecipe);
        List<Recipe> recipeList;
        recipeList = cp.recs;
        List<String> recipesName = new ArrayList<String>();

        for (Recipe rec: recipes){
            String name = rec.getName();
            recipesName.add(name);
        }
        MyArrayAdapter adapter = new MyArrayAdapter(this, recipesName);
        recipesView.setAdapter(adapter);

    }
    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
