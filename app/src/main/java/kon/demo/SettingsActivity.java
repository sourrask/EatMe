package kon.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private static Button english, dutch, greek; //buttons for possible languages in the app

    //shared preferences
    private static final String Locale_Preference = "LANGUAGE"; //saves the prefered language
    private static final String Locale_KeyValue = "LANGUAGE_KEY"; //save the key of the prefered language
    private static SharedPreferences sharedPreferences;

    // Gets the template for the settings activity by setting the content view to the desired layout
    // Also sets the onClickListeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setListeners();
        loadLocale();
        updateViews();
    }

    //Onclicklisteners for the buttons that change the language of the application.
    private void setListeners() {
        english.setOnClickListener(this);
        greek.setOnClickListener(this);
        dutch.setOnClickListener(this);
    }

    // change language to Prefered language in the whole application
    @Override
    public void onClick(View view) {
        String lang= "en";
        switch (view.getId()) {
            case R.id.english:
                lang = "en";
                break;
            case R.id.dutch:
                lang= "nl";
                break;
            case R.id.greek:
                lang= "el";
                break;
        }
        changeLocale(lang);
        Intent refresh = getIntent();
        finish();
        startActivity(refresh);
    }

    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase("")) {
            return;
        }
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale; //set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the configuration
    }

    //Save locale method in preferences
    public void saveLocale(String lang) {
        SharedPreferences sharedPreferences = getSharedPreferences("LANGUAGE", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Locale_KeyValue, lang);
        editor.apply();
    }

    //Get locale method in preferences
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        changeLocale(language);


    }

    @SuppressLint("CommitPrefEdits")
    private void initViews() {
        sharedPreferences=getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        english=(Button) findViewById(R.id.english);
        dutch=(Button) findViewById(R.id.dutch);
        greek=(Button) findViewById(R.id.greek);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * updates all views based on the language selected
     */
    private void updateViews() {
        SharedPreferences sharedPreferences = getSharedPreferences("LANGUAGE", Context.MODE_PRIVATE);
        String locale = sharedPreferences.getString("LANGUAGE_KEY", "");
        int color=getResources().getColor(R.color.selected);
        Drawable background=getResources().getDrawable(R.drawable.roundedblue);
        if (locale.equals("en")){
            english.setBackground(background);
            english.setTextColor(color);
        } else if (locale.equals("nl")){
            dutch.setBackground(background);
            dutch.setTextColor(color);
        }  else if (locale.equals("el")){
            greek.setBackground(background);
            greek.setTextColor(color);
        }

    }

}
