package kon.demo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class inventoryActivity extends AppCompatActivity {

    // Gets the template for the inventory activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    /*
     * ShowText ???
     *
     * @param {@code view}
     * @pre
     * @post / @returns
     * @modifies
     * @throws
     */
    public void showText(View view){
        final EditText searchText=(EditText) findViewById(R.id.searchText);

    }

    //TODO
    private void search() {
    }

    /*
     * AddIngredients adds ingredients to the inventory of the activity and displays them on the screen
     *
     * @param {@code view}
     * @pre
     * @post / @returns
     * @modifies
     * @throws
     */
    public void AddIngredient(View view) {
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
    }
}
