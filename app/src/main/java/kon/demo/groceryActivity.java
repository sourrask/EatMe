package kon.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class groceryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        //// TODO: 04-Mar-18 search bar as in inventory if every1 agrees
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
