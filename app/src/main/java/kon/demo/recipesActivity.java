package kon.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 * Activity class for the recipes activity. In this activity we display the recipes in the database.
 * The back button of the phone will be available to click and will make the user return to the
 * home screen.
 */
public class recipesActivity extends AppCompatActivity {

    // Gets the template for the recipes activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
    }

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
