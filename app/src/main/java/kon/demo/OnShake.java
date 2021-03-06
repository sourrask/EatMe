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
import data.Recipe;

/**
 * Created by sourrask on 20-Mar-18.
 */

/*
 * Main class for the OnShake activity. When the user presses the button, the OnShake listener will
 * become active and when the user shakes the phone they will get a random recipe.
 */
public class OnShake extends Service implements ShakeDetector.Listener {
    ControlPanel cp;
    ShakeDetector shakeDetector;        //variable that consists of a Shake detector
    SensorManager manager;              //variable sensor manager


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
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }


    //Stops the OnShake method
    @Override
    public void onDestroy() {
        shakeDetector.stop();
    }

}
