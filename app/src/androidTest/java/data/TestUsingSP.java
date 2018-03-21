package data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;

/**
 * Created by s158270 on 15-3-2018.
 */

class TestUsingSP {
    Context appContext;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry.getTargetContext();
    }

    //makes sure that the shared preferences is cleaned, to avoid different tests to clash
    public void cleanSP(SharedPreferences sp) {
        SharedPreferences.Editor e = sp.edit();
        e.clear();
        e.commit();
    }
}
