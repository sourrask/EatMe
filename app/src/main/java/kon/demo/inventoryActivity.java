package kon.demo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;


public class inventoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }


    public void showText(View view){
        final EditText searchText=(EditText) findViewById(R.id.searchText);

    }

    //TODO
    private void search() {
    }


    public void AddIngredient(View view) {
    }
}
