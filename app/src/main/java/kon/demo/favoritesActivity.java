package kon.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 * Activity class for the favorites activity. In this activity we display the favorite recipes.
 * We also use the back button of the phone to return to the homescreen.
 */
public class favoritesActivity extends AppCompatActivity {

    // Gets the template for the favorites activity by setting the content view to the desired layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        //// TODO: 04-Mar-18 search bar as in inventory if every1 agrees (searches different data)

    }

    // Back button that returns to the homescreen
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
