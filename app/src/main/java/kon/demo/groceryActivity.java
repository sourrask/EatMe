package kon.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
 * Activity class for the Grocery activity. In this activity we display the shopping list of an user.
 * We also use the back button of the phone to return to the homescreen. We added some functionality
 * to modify the grocery list, adding/removing ingredients or adding them to your inventory after
 * buying them.
 */
public class groceryActivity extends AppCompatActivity {

    // Gets the template for the grocery activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        //// TODO: 04-Mar-18 search bar as in inventory if every1 agrees
    }

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    // Method that adds ingredients to the shopping list.
    public void addToShoppingList(View view) {
    }

    // Method that removes the ingredients from the shopping list and adds them all to the inventory
    public void addAllToInventory(View view) {
    }

    // Method that removes ingredients from the shopping list.
    public void removeFromShoppingList(View view) {
    }

    //Method that fully deletes the shopping list
    public void deleteGroceryList(View view) {
    }
}
