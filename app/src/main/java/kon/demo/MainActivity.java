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
import android.widget.ToggleButton;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getCurrentLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    /**
     * hello
     * @param view
     */
    public void shake(final View view) {
        ToggleButton toggleButton= (ToggleButton)findViewById(R.id.shakeButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Snackbar.make(view, "Shaking enabled", Snackbar.LENGTH_SHORT).show(); //todo use the onShake
                    Intent shake = new Intent(MainActivity.this,onShake.class);
                    startService(shake);
                } else {
                    Snackbar.make(view, "Shaking disabled", Snackbar.LENGTH_SHORT).show();//todo set onShakeListener off
                    Intent shake = new Intent (MainActivity.this,onShake.class);

                }
            }
        });
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
