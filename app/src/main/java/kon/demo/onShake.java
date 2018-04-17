package kon.demo;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.squareup.seismic.ShakeDetector;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import data.ControlPanel;
import data.Ingredient;
import data.Name;
import data.Recipe;

/**
 * Created by sourrask on 20-Mar-18.
 */

/*
 * Main class for the onShake activity. When the user presses the button, the onShake listener will
 * become active and when the user shakes the phone they will get a random recipe.
 */
public class onShake extends Service implements ShakeDetector.Listener {
    ControlPanel cp;
    ShakeDetector shakeDetector;        //variable that consists of a Shake detector
    SensorManager manager;              //variable sensor manager
    ListView listView;
    private static final String Locale_Preference = "ONSHAKE"; //saves the prefered language
    private static final String Locale_KeyValue = "ONSHAKE_PRESSED"; //save the key of the prefered language
    private static SharedPreferences sharedPreferences;


    //creating the shake detector
    @Override
    public void onCreate(){
        super.onCreate();
        shakeDetector = new ShakeDetector(this);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector.start(manager);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //If the shake is detected by the phone, it displays a random recipe
    @Override
    public void hearShake() {
        Intent random= new Intent(this,MainActivity.class);
        startActivity(random);
    }


    //Stops the onShake method
    @Override
    public void onDestroy() {
        shakeDetector.stop();
    }

}
