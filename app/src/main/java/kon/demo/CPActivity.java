package kon.demo;

import android.support.v7.app.AppCompatActivity;

import data.ControlPanel;

/**
 * A class that automatically loads the ControlPanel on resume
 * and saves it on back pressed
 */
public class CPActivity extends AppCompatActivity {

    ControlPanel cp;

    @Override
    protected void onResume() {
        super.onResume();
        cp = new ControlPanel(getApplicationContext());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        cp.save();
        finish();
    }
}
