package kon.demo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import data.Ingredient;

/*
 * Activity class for the Grocery activity.
 * In this activity we display the shopping list of an user.
 * We also use the back button of the phone to return to the homescreen. We added some functionality
 * to modify the grocery list, adding/removing ingredients or adding them to your inventory after
 * buying them.
 */
public class GroceryActivity extends CPActivity {

    //lists
    List<Ingredient> allIngredients;
    List<Ingredient> groceryIngredients;
    String[] allIngredientsName;
    String[] groceryIngredientsName;

    //adapters
    MyIngredientAdapter ingredientAdapter;
    MyArrayAdapter adapter;

    //view elements
    EditText searchText;
    ListView groceryView;
    ListView ingredientsView;

    // Gets the template for the grocery activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    /**
     * update UI with ingredients on grocery list
     */
    private void updateUI(){
        //UI elements
        groceryView= (ListView) findViewById(R.id.groceryList);
        ingredientsView = new ListView(this);
        searchText= (EditText) findViewById(R.id.searchGrocery);

        //lists
        groceryIngredients = cp.getShoppingList();
        allIngredients = cp.getAllIngredients();

        groceryIngredientsName =
                EatMeTools.listToIngredientNameNeedHaveArray(groceryIngredients, true);
        allIngredientsName = EatMeTools.listToIngredientNameArray(allIngredients);

        Arrays.sort(groceryIngredientsName);
        Arrays.sort(allIngredientsName);

        //get adapter
        adapter = new MyArrayAdapter(this, groceryIngredientsName,cp,false);
        groceryView.setAdapter(adapter);

        //add listeners
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when text is entered
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                GroceryActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cp.save();
    }

    /**
     * listener for the addtoshoppinglist button
     * opens UI for adding ingredients to the shopping list.
     */
    public void addToShoppingList(View view) {
        //via popup window, let user add ingredient + amount
        ingredientAdapter = new MyIngredientAdapter(this, allIngredientsName, false);
        ingredientsView.setAdapter(ingredientAdapter);

        buildAlertDialog();
    }

    /**
     * builds the alert dialog for adding and removing ingredients to the grocery list
     */
    private void buildAlertDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(GroceryActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                double[] amounts = ingredientAdapter.getAmounts();
                for (int j = 0; j < amounts.length; j++) {
                    ((Ingredient)cp.ings.get(allIngredientsName[j])).amountNeed = amounts[j];
                }
                updateUI();
            }
        });
        builder.setTitle(R.string.addToInventory);
        builder.setView(ingredientsView);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    /**
     * listener for the addalltoinventory button
     * this removes the ingredients from the shopping list and adds them all to the inventory
     */
    public void addAllToInventory(View view) {
        cp.removeAllIngredientFromShoppingList(true);
        updateUI();
    }

    /**
     * listener for the deletegrocerylist button
     * this removes the ingredients from the shopping list
     */
    public void deleteGroceryList(View view) {
        cp.removeAllIngredientFromShoppingList(false);
        updateUI();
    }
}
