package kon.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import data.ControlPanel;
import data.Ingredient;
import data.Recipe;
import data.RecipeList;

import java.util.Arrays;
import java.util.List;

/*
 * Activity class for the recipes activity. In this activity we display the recipes in the database.
 * The back button of the phone will be available to click and will make the user return to the
 * home screen.
 */
public class RecipesActivity extends CPActivity {
    //lists
    List<Recipe> recipeList;
    String[] recipesName;
    Double [] amount;

    //adapter
    MyArrayAdapter adapter;

    //UI elements
    EditText searchText;
    ListView recipesView;

    // Gets the template for the recipes activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    //update listview with recipes
    public void updateUI(){
        //UI elements
        recipesView = (ListView) findViewById(R.id.listRecipe);
        searchText= (EditText) findViewById(R.id.searchText);

        //get lists
        recipeList = cp.getAllRecipe();
        recipesName = EatMeTools.listToRecipeNameArray(recipeList);
        Arrays.sort(recipesName);

        //get adapter
        adapter = new MyArrayAdapter(this, recipesName, cp,false);
        recipesView.setAdapter(adapter);

        //add listeners
        recipesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                (new RecipeDialog(RecipesActivity.this, cp, recipesName[position])).build();
            }
        });

        //enable search
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when text is entered
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                (RecipesActivity.this).adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cp.save();
    }

    /**
     * listener for the addrecipe button
     * opens the addrecipe dialog for making a new recipe
     */
    public void addrecipe(View view) {
        Intent add = new Intent(this, AddRecipe.class);
        startActivity(add);
    }


    /**
     * listener for the deleterecipe button
     * shows the pop up dialog to delete a recipe
     */
    public void deleteRecipe(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle(R.string.removeRecipe);
        builder.setItems(recipesName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateDeleteRecommended(which);
            }
        });

        builder.setPositiveButton(R.string.finish,null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    /**
     * deleting selected recipe from the dialog
     * @param which position of the recipe
     */
    private void updateDeleteRecommended(int which) {
        cp.deleteRecipe(recipesName[which]);
        updateUI();
    }


}
