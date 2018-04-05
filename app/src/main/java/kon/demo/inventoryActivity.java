package kon.demo;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.annotation.Annotation;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.IngredientList;
import data.Name;

/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class inventoryActivity extends AppCompatActivity{

    ControlPanel cp;
    MyArrayAdapter adapter;
    MyIngredientAdapter ingredientAdapter;
    EditText searchText;
    ListView listView;
    String str;
    // Gets the template for the inventory activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
        update();
    }

    //update listview with recipes
    public void update(){
        final ListView groceryView= (ListView) findViewById(R.id.inventoryList);
        List<Ingredient> inventory;
        inventory = cp.getInventory();
        String[] invIngName = new String[inventory.size()];
        int index = 0;

        for (Ingredient ing: inventory) {
            String name = ing.getName();
            invIngName[index] = name + "   " + ing.amountHave;
            index++;
        }

        Arrays.sort(invIngName);
        adapter = new MyArrayAdapter(this, invIngName,cp,false);
        groceryView.setAdapter(adapter);
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
                inventoryActivity.this.adapter.getFilter().filter(cs);
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

    public void AddIngredient(View view) {
        //via a popup window, let user add ingredient + amount
        listView=new ListView(this);
        List<Ingredient> ingredients;
        ingredients=cp.getAllIngredients();
        String[] allIngredients= new String[ingredients.size()];
        int index=0;

        for (Ingredient ing: ingredients) {
            String name = ing.getName();
            allIngredients[index] = name;
            index++;
        }

        Arrays.sort(allIngredients);
        ingredientAdapter=new MyIngredientAdapter(this, allIngredients);
        listView.setAdapter(ingredientAdapter);

        showDialogListView(view);
    }
    public void showDialogListView(View view){
        AlertDialog.Builder builder= new AlertDialog.Builder(inventoryActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.save,null);
        builder.setTitle(R.string.addToInventory);
        builder.setView(listView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    /*
     * RemoveIngredients removes ingredients to the inventory of the activity and displays them on the screen
     *
     * @param {@code view}
     * @pre
     * @post / @returns
     * @modifies
     * @throws
     */
    public void RemoveIngredient(View view) {
        //select an ingredient
        //delete it via cp.removeIngredientFromShoppingList(String)
        cp.removeIngredientFromInventory("kaas");
        update();
    }
}
