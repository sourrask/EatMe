package kon.demo;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import java.util.Locale;

/*
 * Main class that renders the homepage. and includes the links to the other activities.
 * The main activity also has the shake method, that will return a random recipe when one shakes the
 * phone.
 */
public class MainActivity extends CPActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //shake button and intent
    ToggleButton toggleButton;
    Intent shake;

    //shared preferences
    SharedPreferences sp;

    //creating the view of the homepage, and adding a button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCurrentLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the shared preferences
        sp = getSharedPreferences("ONSHAKE_PRESSED", Context.MODE_PRIVATE);

        //get toggle button
        toggleButton = (ToggleButton)findViewById(R.id.shakeButton);

        //add listener
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                setOnShake(true);
                Snackbar.make(findViewById(R.id.mainActivity), "Shaking enabled", Snackbar.LENGTH_SHORT).show(); //todo use the OnShake
                shake = new Intent(MainActivity.this,OnShake.class);
                startService(shake);

            } else {
                Snackbar.make(findViewById(R.id.mainActivity), "Shaking disabled", Snackbar.LENGTH_SHORT).show();//todo set onShakeListener off
                stopService(shake);

            }
            }
        });
        toggleButton.setChecked(false);

    }

    @Override
    protected void onResume(){
        super.onResume();
        if (getOnShake()) {
            (new RecipeDialog(MainActivity.this, cp, cp.getRandomRecipe().name)).build();
            setOnShake(false);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        toggleButton.setChecked(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        setOnShake(false);
    }

    //Below the listeners for al sub-activities accessible from the home screen
    public void inventoryActivity(View view) {
        Log.d(LOG_TAG, "Inventory Loading!");
        Intent inventory = new Intent(this, InventoryActivity.class);
        startActivity(inventory);

    }

    public void settingsActivity(View view) {
        Log.d(LOG_TAG, "Settings loading!");
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }

    public void RecipesActivity(View view) {
        Log.d(LOG_TAG, "Recipes loading!");
        Intent recipes = new Intent(this, RecipesActivity.class);
        startActivity(recipes);
    }

    public void GroceryActivity(View view) {
        Log.d(LOG_TAG, "Groceries loading!");
        Intent groceries = new Intent(this, GroceryActivity.class);
        startActivity(groceries);
    }

    public void FavoritesActivity(View view) {
        Log.d(LOG_TAG, "Favorites loading!");
        Intent favorites = new Intent(this, FavoritesActivity.class);
        startActivity(favorites);
    }

    /**
     * gets the current locale
     */
    private void getCurrentLocale() {
        SharedPreferences sharedPreferences =
                getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        String locale = sharedPreferences.getString("LANGUAGE_KEY", "");
        if (locale.equals("en")){
        } else {
            Configuration configuration = getBaseContext().getResources().getConfiguration();
            Locale myLocale = new Locale(locale);
            Locale.setDefault(myLocale);
            configuration.locale = myLocale;
            getBaseContext().getResources().updateConfiguration(
                    configuration, getBaseContext().getResources().getDisplayMetrics());
        }

    }

    /**
     * sets the onshake shared preferences boolean
     * @param shake whether waiting for a shake
     */
    private void setOnShake(boolean shake) {
        SharedPreferences sharedPreferences = getSharedPreferences("ONSHAKE_PRESSED", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("ONSHAKE", shake);
        editor.apply();
    }

    /**
     * gets the shared preference for shaking
     * @return
     */
    private boolean getOnShake() {
        return sp.getBoolean("ONSHAKE", false);
    }
}
