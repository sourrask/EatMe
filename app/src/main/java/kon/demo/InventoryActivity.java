package kon.demo;


import android.content.DialogInterface;
import android.content.Intent;
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
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class InventoryActivity extends CPActivity{

    //lists
    List<Ingredient> allIngredients;
    List<Ingredient> inventoryIngredients;
    String[] allIngredientsName;
    String[] inventoryIngredientsName;

    //adapters
    MyIngredientAdapter ingredientAdapter;
    MyArrayAdapter adapter;

    //view elements
    EditText searchText;
    ListView inventoryView;
    ListView ingredientsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    /**
     * update UI with ingredients on inventory
     */
    private void updateUI(){
        //UI elements
        inventoryView = (ListView) findViewById(R.id.inventoryList);
        ingredientsView = new ListView(this);
        searchText = (EditText) findViewById(R.id.searchText);

        //lists
        inventoryIngredients = cp.getInventory();
        allIngredients = cp.getAllIngredients();

        inventoryIngredientsName =
                EatMeTools.listToIngredientNameNeedHaveArray(inventoryIngredients, false);
        allIngredientsName = EatMeTools.listToIngredientNameArray(allIngredients);

        Arrays.sort(inventoryIngredientsName);
        Arrays.sort(allIngredientsName);

        //get adapter
        adapter = new MyArrayAdapter(this, inventoryIngredientsName, cp,false);
        inventoryView.setAdapter(adapter);

        //add listeners
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when text is entered
            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                InventoryActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cp.save();
    }

    /**
     * listener for the addingredient button
     * opens the dialog for adding ingredients to the inventory
     */
    public void AddIngredient(View view) {
        //via a popup window, let user add ingredient + amount
        ingredientAdapter = new MyIngredientAdapter(this, allIngredientsName, true);
        ingredientsView.setAdapter(ingredientAdapter);

        buildAlertDialog();
    }

    /**
     * builds the alert dialog for adding and removing ingredients from the inventory
     */
    public void buildAlertDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(InventoryActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                double[] amounts = ingredientAdapter.getAmounts();
                for (int j = 0; j < amounts.length; j++) {
                    ((Ingredient)cp.ings.get(allIngredientsName[j])).amountHave = amounts[j];
                }
                updateUI();
            }
        });
        builder.setTitle(R.string.addToInventory);
        builder.setView(ingredientsView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * listener for the addnewingredient button
     * opens the addredipe dialog ToDo make seperate dialog?
     */
    public void addNewIngredient(View view) {
        Intent add= new Intent (this,AddRecipe.class);
        startActivity(add);
    }
}
