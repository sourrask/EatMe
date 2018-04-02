package kon.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.RecipeList;

/*
 * Activity class for the Grocery activity. In this activity we display the shopping list of an user.
 * We also use the back button of the phone to return to the homescreen. We added some functionality
 * to modify the grocery list, adding/removing ingredients or adding them to your inventory after
 * buying them.
 */
public class groceryActivity extends AppCompatActivity {

    ControlPanel cp;
    MyArrayAdapter adapter;
    EditText searchText;

    // Gets the template for the grocery activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        cp = new ControlPanel(getApplicationContext());
        update();
    }

    //update listview with recipes
    public void update(){
        final ListView groceryView= (ListView) findViewById(R.id.groceryList);
        List<Ingredient> shoppingList;
        shoppingList = cp.getShoppingList();
        String[] shIngName = new String[shoppingList.size()];
        int index = 0;

        for (Ingredient ing: shoppingList) {
            String name = ing.getName();
            shIngName[index] = name + "   " + ing.amountNeed;
            index++;
        }

        Arrays.sort(shIngName);
        adapter = new MyArrayAdapter(this, shIngName);
        groceryView.setAdapter(adapter);
        searchText= (EditText) findViewById(R.id.searchGrocery);

        cp.save();

        //enable search
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when text is entered
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                groceryActivity.this.adapter.getFilter().filter(cs);
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
        cp.save();
        finish();
    }

    // Method that adds ingredients to the shopping list.
    public void addToShoppingList(View view) {
        //via popup window, let user add ingredient + amount
        cp.addIngredientToShoppingList("kaas", 2.0);
        update();
    }

    // Method that removes the ingredients from the shopping list and adds them all to the inventory
    public void addAllToInventory(View view) {
        cp.removeAllIngredientFromShoppingList();
        update();
    }

    // Method that removes ingredients from the shopping list.
    public void removeFromShoppingList(View view) {
        //select an ingredient
        //delete it via cp.removeIngredientFromShoppingList(String)
        update();
    }

    //Method that fully deletes the shopping list
    public void deleteGroceryList(View view) {
        for (Ingredient i : cp.getShoppingList()) {
            cp.removeIngredientFromShoppingList(i.name);
        }
        update();
    }
}
