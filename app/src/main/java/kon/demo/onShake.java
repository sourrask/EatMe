package kon.demo;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.squareup.seismic.ShakeDetector;

/**
 * Created by sourrask on 20-Mar-18.
 */

public class onShake extends Service implements ShakeDetector.Listener {

    @Override
    public void onCreate(){
        super.onCreate();
        ShakeDetector shakeDetector = new ShakeDetector(this);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector.start(manager);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void hearShake() {
        Intent random = new Intent(this,recipesActivity.class);
        startActivity(random); // for now

    }
}
