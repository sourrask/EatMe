package kon.demo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Locale;

import data.ControlPanel;

/*
 * Main class that renders the homepage. and includes the links to the other activities.
 * The main activity also has the shake method, that will return a random recipe when one shakes the
 * phone.
 */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    ToggleButton toggleButton;
    Intent shake;

    ControlPanel cp;



    //creating the view of the homepage, and adding a button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCurrentLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cp = new ControlPanel(this);
        toggleButton= (ToggleButton)findViewById(R.id.shakeButton);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Snackbar.make(findViewById(R.id.mainActivity), "Shaking enabled", Snackbar.LENGTH_SHORT).show(); //todo use the onShake
                    shake = new Intent(MainActivity.this,onShake.class);
                    startService(shake);
                    new RecipeDialog(MainActivity.this, cp, cp.getRandomRecipe().name);
                    toggleButton.setChecked(false);

                } else {
                    Snackbar.make(findViewById(R.id.mainActivity), "Shaking disabled", Snackbar.LENGTH_SHORT).show();//todo set onShakeListener off
                    stopService(shake);
                }
            }
        });
        toggleButton.setChecked(false);

    }


    @Override
    protected void onPause() {
        super.onPause();
        toggleButton.setChecked(false);
    }

    public void inventoryActivity(View view) {
        Log.d(LOG_TAG, "Inventory Loading!");
        Intent inventory = new Intent(this, inventoryActivity.class);
        startActivity(inventory);

    }

    public void settingsActivity(View view) {
        Log.d(LOG_TAG, "Settings loading!");
        Intent settings = new Intent(this, settingsActivity.class);
        startActivity(settings);
    }

    public void RecipesActivity(View view) {
        Log.d(LOG_TAG, "Recipes loading!");
        Intent recipes = new Intent(this, recipesActivity.class);
        startActivity(recipes);
    }

    public void GroceryActivity(View view) {
        Log.d(LOG_TAG, "Groceries loading!");
        Intent groceries = new Intent(this, groceryActivity.class);
        startActivity(groceries);
    }

    public void FavoritesActivity(View view) {
        Log.d(LOG_TAG, "Favorites loading!");
        Intent favorites = new Intent(this, favoritesActivity.class);
        startActivity(favorites);
    }

    public void shake(final View view) {

    }

    private void getCurrentLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        String locale = sharedPreferences.getString("LANGUAGE_KEY", "");
        if (locale.equals("en")){
        } else {
            Configuration configuration = getBaseContext().getResources().getConfiguration();
            Locale myLocale = new Locale(locale);
            Locale.setDefault(myLocale);
            configuration.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        }

    }
}
