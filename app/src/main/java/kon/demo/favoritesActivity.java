package kon.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Recipe;


/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class favoritesActivity extends AppCompatActivity {
    // Gets the template for the favorites activity by setting the content view to a desired layout
    ListView listView;
    private Context context;
    ControlPanel cp;
    int i=0;
    String[] favoritesName;
    MyArrayAdapter adapter;
    String[]recipes;
    List<Recipe> favoritesList;
    List<Ingredient> ingredientList;
    Double [] amount,haveAmount,needAmount;


    //search editText
    EditText searchText;
    String[] ingredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        context = getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
        update();
    }

    //update listview with recipes
    public void update() {
        final ListView favoritesView = (ListView) findViewById(R.id.favoritesList);

        favoritesList = cp.getRecommendedRecipes();
        favoritesName = new String[favoritesList.size()];
        i=0;

        for (Recipe recipe : favoritesList) {
            String name = recipe.getName();
            favoritesName[i] = name;
            i++;
        }
        Arrays.sort(favoritesName);
        adapter = new MyArrayAdapter(this, favoritesName,cp,false);
        favoritesView.setAdapter(adapter);

        favoritesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new RecipeDialog(favoritesActivity.this, cp, favoritesName[position]);
            }
        });

        cp.save();
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
        cp.save();
    }

    public void addrecipe(View view) {
        listView=new ListView(this);
        List<Recipe> recipeList;
        recipeList = cp.getAllRecipe();
        recipes = new String[recipeList.size()];
        i=0;
        for (Recipe recipe : recipeList) {
            String name = recipe.getName();
            recipes[i] = name;
            i++;
        }

        Arrays.sort(recipes);
        //adapter = new MyArrayAdapter(this, recipes,cp,true);
        //listView.setAdapter(adapter);
        searchText = (EditText) findViewById(R.id.searchText);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.addToFavorites);
        builder.setItems(recipes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateRecommended(which);
            }
        });

        builder.setPositiveButton(R.string.finish,null);
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private void updateRecommended(int which) {
        cp.changeRecommended(recipes[which]);
        update();
    }

    public void removeRecipe(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.removeFromFavorites);
        builder.setItems(favoritesName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDeleteRecommended(which);
            }
        });

        builder.setPositiveButton(R.string.finish,null);
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    private void updateDeleteRecommended(int which) {
        cp.changeRecommended(favoritesName[which]);
        update();
    }
}
