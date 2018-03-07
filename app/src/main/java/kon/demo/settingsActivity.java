package kon.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class settingsActivity extends AppCompatActivity implements View.OnClickListener{

    private static Button english, dutch, greek;
    private static TextView languageText;
    private static Locale myLocale;

    //shared preferences
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
        setListeners();
        loadLocale();
    }





    private void setListeners() {
        english.setOnClickListener(this);
        greek.setOnClickListener(this);
        dutch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        String lang="en";
        switch (view.getId()) {
            case R.id.english:
                lang = "en";
                break;
            case R.id.dutch:
                lang="nl";
                break;
            case R.id.greek:
                lang="el";
                break;
        }
        changeLocale(lang);
        Intent refresh = getIntent();
        finish();
        startActivity(refresh);
    }
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the configuration

    }
    //Save locale method in preferences
    public void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }

    //Get locale method in preferences
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "");
        changeLocale(language);
    }

    @SuppressLint("CommitPrefEdits")
    private void initViews() {
        sharedPreferences=getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        languageText=(TextView) findViewById(R.id.languageText);
        english=(Button) findViewById(R.id.english);
        dutch=(Button) findViewById(R.id.dutch);
        greek=(Button) findViewById(R.id.greek);
    }


}
