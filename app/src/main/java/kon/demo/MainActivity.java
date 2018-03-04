package kon.demo;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //final int snackTime=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   }

    public void inventoryActivity(View view) {
        Log.d(LOG_TAG, "Inventory Loading!");
        //Snackbar.make(findViewById(R.id.mainActivity), "Loading Inventory", snackTime).show();
        Intent inventory = new Intent(this, inventoryActivity.class);
        startActivity(inventory);

    }

    public void settingsActivity(View view) {
        Log.d(LOG_TAG, "settings loading!");
        Intent settings=new Intent(this, settingsActivity.class);
        startActivity(settings);
    }

    public void RecipesActivity(View view) {
        Log.d(LOG_TAG, "recipes loading!");
        Intent recipes= new Intent(this, recipesActivity.class);
        startActivity(recipes);
    }

    public void GroceryActivity(View view) {
        Log.d(LOG_TAG, "groceries loading!");
        Intent groceries= new Intent(this, groceryActivity.class);
        startActivity(groceries);
    }

    public void FavoritesActivity(View view) {
        Log.d(LOG_TAG, "favorites loading!");
        Intent favorites= new Intent(this, favoritesActivity.class);
        startActivity(favorites);
    }
}
