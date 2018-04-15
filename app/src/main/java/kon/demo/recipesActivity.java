package kon.demo;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import data.ControlPanel;
import data.Ingredient;
import data.IngredientList;
import data.Name;
import data.Recipe;
import data.RecipeList;

import android.app.ListActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

/*
 * Activity class for the recipes activity. In this activity we display the recipes in the database.
 * The back button of the phone will be available to click and will make the user return to the
 * home screen.
 */
public class recipesActivity extends AppCompatActivity {
    private Context context;
    ControlPanel cp;
    int i=0;
    String[] recipesName;
    MyArrayAdapter adapter;

    //search editText
    EditText searchText;



    // Gets the template for the recipes activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
        context = getApplicationContext();


    }

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
        update();
    }

    //update listview with recipes
    public void update(){
        final ListView recipesView= (ListView) findViewById(R.id.listRecipe);
        final ListView ingredientsView;
        RecipeList recipeList;
        recipeList = cp.recs;
        recipesName = new String[recipeList.size()];



        for (Name recipe: recipeList){
            String name = recipe.getName();
            recipesName[i]=name;
            i++;
        }
        Arrays.sort(recipesName);
        adapter = new MyArrayAdapter(this, recipesName, cp,false);
        recipesView.setAdapter(adapter);
        searchText= (EditText) findViewById(R.id.searchText);

        cp.save();





                //enable search
                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    //when text is entered
                    @Override
                    public void onTextChanged(CharSequence cs, int start, int before, int count) {
                        recipesActivity.this.adapter.getFilter().filter(cs);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


    }


    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    public void addrecipe(View view) {
        Intent add = new Intent(this, AddRecipe.class);
        startActivity(add);
    }
}
