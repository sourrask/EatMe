package kon.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class recipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
